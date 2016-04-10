/*
 * 
 */
package king.flow.net.update.http;

import com.github.jsonj.JsonObject;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import king.flow.common.CommonConstants;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.data.RegistryTLSResult.APP_UPDATE_MD5;
import static king.flow.data.RegistryTLSResult.APP_UPDATE_PATH;
import static king.flow.data.RegistryTLSResult.APP_UPDATE_START;
import static king.flow.data.RegistryTLSResult.APP_UPDATE_VER;
import king.flow.net.update.Update;
import static king.flow.net.update.Update.Protocol.HTTP;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * http://stackoverflow.com/questions/304268/getting-a-files-md5-checksum-in-java
 * http://stackoverflow.com/questions/9324933/what-is-a-good-java-library-to-zip-unzip-files
 * maybe zip4j is another choice
 *
 * @author Administrator
 */
public class HttpUpdateTool implements Update {

    private static final String APP_UPDATE_FOLDER_PREFIX = "../app_";
    private static final String STARTUP_EXE = "bank.exe";
    private static final String UPDATE_FILE = "update.zip";
    private final String version;
    private final URL updateUrl;
    private final String md5;
    private final String updateFolderPath;
    private final String startupCommand;

    public HttpUpdateTool(JsonObject updateProperties) throws MalformedURLException {
        String upgradeVersion = updateProperties.getString(APP_UPDATE_VER);
        this.version = CommonConstants.VERSION.equals(upgradeVersion)
                ? (upgradeVersion + Math.random()) : upgradeVersion;
        this.updateUrl = new URL(updateProperties.getString(APP_UPDATE_PATH));
        this.md5 = updateProperties.getString(APP_UPDATE_MD5);
        String appStartName = updateProperties.getString(APP_UPDATE_START);
        this.updateFolderPath = APP_UPDATE_FOLDER_PREFIX + this.version;
        this.startupCommand = this.updateFolderPath + File.separator
                + (appStartName == null ? STARTUP_EXE : appStartName);
    }

    public String getVersion() {
        return version;
    }

    public URL getUpdateUrl() {
        return updateUrl;
    }

    public String getMd5() {
        return md5;
    }

    @Override
    public Protocol getProtocolType() {
        return HTTP;
    }

    @Override
    public File mkDir4NewBankApp() {
        File updateFolder = new File(this.updateFolderPath);
        try {
            if (updateFolder.exists()) {
                FileUtils.forceDelete(updateFolder);
            }
            FileUtils.forceMkdir(updateFolder);
        } catch (IOException ex) {
            Logger.getLogger(HttpUpdateTool.class.getName()).log(Level.SEVERE,
                    "mkDir operation[{0}] for NewVersionApp failed due to: \n{1}",
                    new Object[]{this.updateFolderPath, ex});
        }
        return updateFolder;
    }

    @Override
    public File downloadFile(URL updateUrl) {
        File updateFile = null;
        try {
            File updateFolder = mkDir4NewBankApp();
            if (updateFolder.exists()) {
                updateFile = new File(updateFolder, UPDATE_FILE);
                FileUtils.copyURLToFile(updateUrl, updateFile);
            }
        } catch (IOException ex) {
            getLogger(HttpUpdateTool.class.getName()).log(Level.SEVERE,
                    "fail to download upgraded file due to : \n{0}", ex);
        }
        return updateFile;
    }

    @Override
    public boolean checkSumFile(File updateFile, String md5sum) {
        if (updateFile.exists()) {
            try {
                HashCode hash = Files.hash(updateFile, Hashing.md5());
                String checkSumMd5 = hash.toString();
                if (checkSumMd5.equals(md5sum)) {
                    return true;
                } else {
                    getLogger(HttpUpdateTool.class.getName()).log(Level.INFO,
                            "md5 checksum is not equal to original value, downloaded file is corrupted");
                    return false;
                }
            } catch (IOException ex) {
                Logger.getLogger(HttpUpdateTool.class.getName()).log(Level.SEVERE,
                        "fail to caculate md5 of update package due to : \n{0}", ex);
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean updateApp(ZipFile zipFile) {
        try {
            final File workingFolder = new File(this.updateFolderPath);
            //1.unzip upgraded file
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                File entryDestination = new File(workingFolder, entry.getName());
                if (entry.isDirectory()) {
                    entryDestination.mkdirs();
                } else {
                    InputStream in = zipFile.getInputStream(entry);
                    OutputStream out = new FileOutputStream(entryDestination);
                    IOUtils.copy(in, out);
                    IOUtils.closeQuietly(in);
                    IOUtils.closeQuietly(out);
                }
            }
            zipFile.close();
            String makeShortcut = "cmd /C " + workingFolder.getCanonicalPath() + File.separator
                    + "mkshortcut /target:\"" + workingFolder.getCanonicalPath() + "\"";
//            makeShortcut = "cscript " + workingFolder.getCanonicalPath() + File.separator
//                    + "mkshortcut.vbs /target:\"" + workingFolder.getCanonicalPath() + "\"";
            Runtime.getRuntime().exec(makeShortcut, null, workingFolder);
            getLogger(HttpUpdateTool.class.getName()).log(Level.INFO,
                    "create bank app shortcut on desktop, running {0}", makeShortcut);

            //2.start bankApp
            Runtime.getRuntime().exec(this.startupCommand);
            getLogger(HttpUpdateTool.class.getName()).log(Level.INFO,
                    "new version bankapp has been started");

            //3.change folder name
            /*FileUtils.moveDirectory(workingFolder, new File("../bankapp"));
             java.nio.file.Files.move(workingFolder.toPath(),
             new File("../bankapp").toPath(),
             StandardCopyOption.ATOMIC_MOVE);
             getLogger(HttpUpdateTool.class.getName()).log(Level.INFO,
             "new bankapp directory has been created");*/
        } catch (IOException exception) {
            getLogger(HttpUpdateTool.class.getName()).log(Level.SEVERE,
                    "fail to upgrade app due to : \n{0}", exception);
            return false;
        }

        return true;
    }

    public static void main(String[] args) throws MalformedURLException, IOException {
        /*URL url = new URL("http://10.117.5.10/bankApp-upgrade.zip");
        final String md5 = "81adb2fc08a7f3e40ea805a37ed24ede";
        final String ver = "2.1";
        HttpUpdateTool httpUpdateTool = new HttpUpdateTool(ver, url, md5);

        getLogger(HttpUpdateTool.class.getName()).log(Level.INFO, "start to download upgraded files");
        File downloadedFile = httpUpdateTool.downloadFile(url);
        getLogger(HttpUpdateTool.class.getName()).log(Level.INFO, "download is over");

        boolean result = httpUpdateTool.checkSumFile(downloadedFile, md5);
        if (result) {
            getLogger(HttpUpdateTool.class.getName()).log(Level.INFO,
                    "updated file verifies successfully");
            ZipFile zipFile = new ZipFile(downloadedFile);
            httpUpdateTool.updateApp(zipFile);
        } else {
            getLogger(HttpUpdateTool.class.getName()).log(Level.INFO,
                    "updated file verifies and get erroneous result");
        }*/
    }

}
