package com.canary.poc.vm;

import com.vmware.content.LibraryModel;
import com.vmware.content.LibraryTypes;
import com.vmware.content.library.ItemModel;
import com.vmware.content.library.ItemTypes;
import com.vmware.content.library.StorageBacking;
import com.vmware.vapi.bindings.StubConfiguration;
import com.vmware.vapi.protocol.HttpConfiguration;
import com.vmware.vcenter.ovf.DiskProvisioningType;
import com.vmware.vcenter.ovf.LibraryItemTypes;
import com.vmware.vim25.*;
import vmware.samples.common.SslUtil;
import vmware.samples.common.authentication.VapiAuthenticationHelper;
import vmware.samples.common.authentication.VimAuthenticationHelper;
import vmware.samples.common.vim.helpers.VimUtil;
import vmware.samples.common.vim.helpers.VmVappPowerOps;
import vmware.samples.contentlibrary.client.ClsApiClient;
import vmware.samples.contentlibrary.helpers.ItemUploadHelper;
import vmware.samples.vcenter.helpers.DatastoreHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CanaryFlow implements WorkAction {

    static CanaryFlow instance = new CanaryFlow();

    private static void run() {
        try {
            proxyObject.login();
            instance.client =
                    new ClsApiClient(instance.vapiAuthHelper.getStubFactory(),
                            instance.sessionStubConfig);
            proxyObject.retrieveImage();
            proxyObject.createClib();
            proxyObject.upload();
            proxyObject.deploy();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    VapiAuthenticationHelper vapiAuthHelper = null;
    VimAuthenticationHelper vimAuthHelper = null;
    StubConfiguration sessionStubConfig;

    public void login() {
        this.vapiAuthHelper = new VapiAuthenticationHelper();
        this.vimAuthHelper = new VimAuthenticationHelper();
        SslUtil.trustAllHttpsCertificates();
        HttpConfiguration.SslConfiguration sslConfig = new HttpConfiguration.SslConfiguration.Builder()
                .disableCertificateValidation()
                .disableHostnameVerification()
                .getConfig();
        HttpConfiguration httpConfig =
                new HttpConfiguration.Builder()
                        .setSslConfiguration(sslConfig)
                        .getConfig();

        String server = "10.243.26.174";
        String username = "administrator@vsphere.local";
        String password = "SSPWo+1fjEl8_Ucg";
        try {
            this.sessionStubConfig =
                    vapiAuthHelper.loginByUsernameAndPassword(server, username, password, httpConfig);
            this.vimAuthHelper.loginByUsernameAndPassword(
                    server,
                    username,
                    password);
            log("login VC successfully!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    boolean skipServerVerification = false;
    private String truststorePath = "";
    private String truststorePassword = "";

    protected HttpConfiguration.SslConfiguration buildSslConfiguration() throws Exception {
        HttpConfiguration.SslConfiguration sslConfig;

        if (this.skipServerVerification) {
            /*
             * Below method enables all VIM API connections to the server
             * without validating the server certificates.
             *
             * Note: Below code is to be used ONLY IN DEVELOPMENT ENVIRONMENTS.
             * Circumventing SSL trust is unsafe and should not be used in
             * production software.
             */
            SslUtil.trustAllHttpsCertificates();

            /*
             * Below code enables all vAPI connections to the server
             * without validating the server certificates..
             *
             * Note: Below code is to be used ONLY IN DEVELOPMENT ENVIRONMENTS.
             * Circumventing SSL trust is unsafe and should not be used in
             * production software.
             */
            sslConfig = new HttpConfiguration.SslConfiguration.Builder()
                    .disableCertificateValidation()
                    .disableHostnameVerification()
                    .getConfig();
        } else {
            /*
             * Set the system property "javax.net.ssl.trustStore" to
             * the truststorePath
             */
            System.setProperty("javax.net.ssl.trustStore", this.truststorePath);
            KeyStore trustStore =
                    SslUtil.loadTrustStore(this.truststorePath,
                            this.truststorePassword);
            HttpConfiguration.KeyStoreConfig keyStoreConfig =
                    new HttpConfiguration.KeyStoreConfig("", this.truststorePassword);
            sslConfig =
                    new HttpConfiguration.SslConfiguration.Builder()
                            .setKeyStore(trustStore)
                            .setKeyStoreConfig(keyStoreConfig)
                            .getConfig();
        }

        return sslConfig;
    }

    private ClsApiClient client;
    private ItemModel libItem;
    String libName, dsName;

    public void createClib() {
        LibraryModel libSpec = new LibraryModel();
        libName = "canary-" + getDefaultTimeSuffix();
        dsName = "vdnetSharedStorage";
        libSpec.setName(libName);
        libSpec.setType(LibraryModel.LibraryType.LOCAL);
        //Build the storage backing for the libraries to be created
        StorageBacking storageBacking = DatastoreHelper.createStorageBacking(
                this.vapiAuthHelper, this.sessionStubConfig, this.dsName);
        libSpec.setStorageBackings(Arrays.asList(storageBacking));
        log("By [%s] prepare content library", libSpec);
        this.client.localLibraryService().create(getClientToken(), libSpec);
        log("[%s] created content library successfully!", libSpec, true);
    }

    public void upload() {
        // Find the content library id by name
        LibraryTypes.FindSpec findSpec = new LibraryTypes.FindSpec();
        findSpec.setName(libName);
        List<String> list = this.client.libraryService().list();
        List<String> libraryIds = this.client.libraryService().find(findSpec);
        assert !libraryIds.isEmpty() : "Unable to find a library with name: "
                + libName;
        log("By [%s] found library : %s", findSpec, libraryIds, true);
        String libraryId = libraryIds.get(0);
        log("[%s] library : %s", libraryId, this.client.libraryService().get(libraryId));

        // Build the specification for the library item to be created
        ItemModel createSpec = new ItemModel();
        String formattedTime = getDefaultTimeSuffix();
        createSpec.setName("cvm-" + formattedTime);
        createSpec.setLibraryId(libraryId);
        createSpec.setType("ovf");
        log("Prepare lib item [%s] to upload", createSpec, true);

        // Create a new library item in the content library for uploading the
        // files
        String clientToken = getClientToken();
        String libItemId =
                this.client.itemService().create(clientToken, createSpec);
        this.libItem = this.client.itemService().get(libItemId);
        log("Library item created : %s", this.libItem.getId());

        // Upload the files in the OVF package into the library item
        String ovaFile = ova.getAbsolutePath();
        System.out.println("Uploading OVA Path : " + ovaFile);
        ItemUploadHelper.performUpload(this.client.updateSession(),
                this.client.updateSessionFileService(),
                this.client.itemService(),
                this.libItem.getId(),
                Arrays.asList(ovaFile));
        log("Uploaded files : "
                + this.client.storageService().list(this.libItem.getId()));

        // Download the template files from the library item into a folder
//        File downloadDir = ItemUploadHelper.createTempDir(libFolderName);
//        ItemDownloadHelper.performDownload(
//                this.client.downloadSessionService(),
//                this.client.downloadSessionFileService(),
//                this.client.itemService(),
//                this.libItem.getId(),
//                downloadDir);
//        System.out.println("Downloaded files to directory : " + downloadDir);
    }

    String clusterName = "cluster-1";
    String vmName = null;
    private VmVappPowerOps vmPowerOps = null;
    private ManagedObjectReference vmMoRef;

    public void deploy() {
        // Find the MoRef of the VC cluster using VIM APIs
        ManagedObjectReference clusterMoRef = null;
        try {
            clusterMoRef = VimUtil.getCluster(this.vimAuthHelper.getVimPort(),
                    this.vimAuthHelper.getServiceContent(),
                    this.clusterName);
        } catch (InvalidPropertyFaultMsg | NotFoundFaultMsg | RuntimeFaultFaultMsg e) {
            throw new RuntimeException(e);
        }
        assert clusterMoRef != null : "Cluster by name " + this.clusterName
                + " must exist";
        log("Cluster MoRef : " + clusterMoRef.getType() + " : "
                + clusterMoRef.getValue(), true);

        // Find the cluster's root resource pool
        List<DynamicProperty> dynamicProps = null;
        try {
            dynamicProps = VimUtil.getProperties(this.vimAuthHelper.getVimPort(),
                    this.vimAuthHelper.getServiceContent(),
                    clusterMoRef,
                    clusterMoRef.getType(),
                    Arrays.asList("resourcePool"));
        } catch (RuntimeFaultFaultMsg | InvalidPropertyFaultMsg e) {
            throw new RuntimeException(e);
        }
        assert dynamicProps != null && dynamicProps.size() > 0;
        ManagedObjectReference rootResPoolMoRef =
                (ManagedObjectReference) dynamicProps.get(0).getVal();
        log("Resource pool MoRef : " + rootResPoolMoRef.getType()
                + " : " + rootResPoolMoRef.getValue(), true);

        // Find the library item by name
        ItemTypes.FindSpec findSpec = new ItemTypes.FindSpec();
        findSpec.setName(this.libItem.getName());
        List<String> itemIds = this.client.itemService().find(findSpec);
        assert !itemIds.isEmpty() : "Unable to find a library item with name: "
                + this.libItem.getName();
        String itemId = itemIds.get(0);
        log("Library item ID : " + itemId);

        // Deploy a VM from the library item on the given cluster
        this.vmName = "canary-vm-" + getTimeSuffix("dd-HH-mm");
        log("Deploying Vm : " + this.vmName);
        String vmId =
                deployVMFromOvfItem(rootResPoolMoRef, this.vmName, itemId);
        assert vmId != null;
        log("Vm created : " + vmId);

        // Power on the VM and wait for the power on operation to complete
        this.vmMoRef = new ManagedObjectReference();
        this.vmMoRef.setType("VirtualMachine");
        this.vmMoRef.setValue(vmId);
        vmPowerOps = new VmVappPowerOps(this.vimAuthHelper.getVimPort(),
                this.vimAuthHelper.getServiceContent());
        this.vmPowerOps.powerOnVM(this.vmName, this.vmMoRef);
    }

    private String deployVMFromOvfItem(ManagedObjectReference rootResPoolMoRef,
                                       String vmName, String libItemId) {
        // Creating the deployment.
        LibraryItemTypes.DeploymentTarget deploymentTarget = new LibraryItemTypes.DeploymentTarget();
        // Setting the target resource pool.
        deploymentTarget.setResourcePoolId(rootResPoolMoRef.getValue());
        // Creating and setting the resource pool deployment spec.
        LibraryItemTypes.ResourcePoolDeploymentSpec deploymentSpec =
                new LibraryItemTypes.ResourcePoolDeploymentSpec();
        deploymentSpec.setName(vmName);
        deploymentSpec.setAcceptAllEULA(true);
//        deploymentSpec.setStorageMappings();
        deploymentSpec.setStorageProvisioning(DiskProvisioningType.thin);
        // Retrieve the library items OVF information and use it for populating
        // deployment spec.
        LibraryItemTypes.OvfSummary ovfSummary = this.client.ovfLibraryItemService()
                .filter(libItemId, deploymentTarget);
        // Setting the annotation retrieved from the OVF summary.
        deploymentSpec.setAnnotation(ovfSummary.getAnnotation());
        // Calling the deploy and getting the deployment result.
        LibraryItemTypes.DeploymentResult deploymentResult = this.client.ovfLibraryItemService()
                .deploy(UUID.randomUUID().toString(),
                        libItemId,
                        deploymentTarget,
                        deploymentSpec);
        if (deploymentResult.getSucceeded()) {
            return deploymentResult.getResourceId().getId();
        } else {
            throw new RuntimeException(deploymentResult.getError().toString());
        }
    }

    private static void log(String fmt, Object... param) {
        if (param.length > 1 && Boolean.parseBoolean(param[param.length - 1].toString())) {
            System.out.println();
        }

        System.out.println(String.format(fmt, param));
    }

    private static String getClientToken() {
        String clientToken = UUID.randomUUID().toString();
        return clientToken;
    }

    private static String getDefaultTimeSuffix() {
        return getTimeSuffix("dd-HH:mm");
    }

    private static String getTimeSuffix(String fmt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fmt);
        LocalDateTime now = LocalDateTime.now();
        String formattedTime = now.format(formatter);
        return formattedTime;
    }

    String photon = "photon-5.0.ova";
    File ova = Paths.get("./photon5/" + photon).toFile();

    public void retrieveImage() {
        String photonImgUrl = "https://packages.vmware.com/photon/5.0/GA/ova/photon-hw15-5.0-dde71ec57.x86_64.ova";
        Path fpath = Paths.get(".", "photon5");
        if (Files.exists(fpath)) {
            log("File exists, pls remove it!");
            try {
                Files.list(fpath).forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                        log("%s was deleted successfully!", path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                Files.delete(fpath);
                log("%s folder was removed successfully!", fpath);
                Files.createDirectory(fpath);
                log("%s folder was recreated successfully!", fpath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        fpath = Paths.get(".", "photon5", photon);
        try {
            ova = Files.createFile(fpath).toFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (InputStream in = new URL(photonImgUrl).openStream();
             FileOutputStream out = new FileOutputStream(ova)) {

            /* 4k read cache / take more than 70 thousands times
             *  compare to large size cache, mean network throughput is more than 4k
             * */
//            byte[] buffer = new byte[1024 * 4];
            /**
             * 1M or 10M read cache / take more than 20 thousands times
             * mean that network throughput reaches the limit.
             */
            byte[] buffer = new byte[1024 * 1024];
            int bytesRead;
            int piece = 0;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                piece++;
                log("%s File piece %s is downloading......", ova, piece);
            }

            log("%s File downloaded successfully!", ova);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static WorkAction proxyObject;

    public static void main(String[] args) {
        TimeInvocationHandler invocationHandler = new TimeInvocationHandler(instance);
        // Create a dynamic proxy
        proxyObject = (WorkAction) Proxy.newProxyInstance(
                WorkAction.class.getClassLoader(),
                new Class<?>[]{WorkAction.class},
                invocationHandler
        );
        CanaryFlow.run();
        log(invocationHandler.showExecTime(), true);
    }

    static class TimeInvocationHandler implements InvocationHandler {
        private Object target;
        private StringBuilder sb;

        TimeInvocationHandler(Object target) {
            this.target = target;
            this.sb = new StringBuilder();
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long now = System.currentTimeMillis();
            Object result = method.invoke(target, args);
            long execTime = System.currentTimeMillis() - now;
            sb.append(String.format("%s exec roughly %s seconds", method.getName(), TimeUnit.MILLISECONDS.toSeconds(execTime)));
            sb.append('\n');
            return result;
        }

        public String showExecTime() {
            return sb.toString();
        }
    }
}
