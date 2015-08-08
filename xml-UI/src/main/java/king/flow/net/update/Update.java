/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.net.update;

import java.io.File;
import java.net.URL;
import java.util.zip.ZipFile;

/**
 *
 * @author Administrator
 */
public interface Update {

    public static enum Protocol {

        HTTP, FTP
    }

    Protocol getProtocolType();
    
    File mkDir4NewBankApp();
    
    File downloadFile(URL updateUrl);
    
    boolean checkSumFile(File file, String md5sum);
    
    boolean updateApp(ZipFile upgradPackage);
}
