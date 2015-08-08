/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import king.flow.common.CommonUtil;

/**
 *
 * @author Administrator
 */
public class DefaultFileUploadAction extends DefaultBaseAction {

    private final String filter;
    private final String uploadPath;

    public DefaultFileUploadAction(String filter, String uploadPath) {
        this.filter = filter;
        this.uploadPath = uploadPath;
    }

    @Override
    protected void installButtonAction() {
        JButton button = getBlock(id, JButton.class);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (CommonUtil.usbDeviceConnected()) {
                    JFileChooser jfc = new JFileChooser(CommonUtil.getUsbDevice());
                    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    jfc.setMultiSelectionEnabled(false);
                    jfc.setAcceptAllFileFilterUsed(false);
                    jfc.setFileFilter(new FileFilter() {

                        @Override
                        public boolean accept(File f) {
                            return f.getName().endsWith(filter);
                        }

                        @Override
                        public String getDescription() {
                            return filter;
                        }
                    });
                    int result = jfc.showDialog(CommonUtil.getCurrentView(),
                            CommonUtil.getResourceMsg("button.text.import"));
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = jfc.getSelectedFile();
                        String uploadUrl = null;
                        //http://www.codejava.net/java-se/networking/ftp/upload-files-to-ftp-server-using-urlconnection-class
                        if (uploadPath.endsWith("/")) {
                            uploadUrl = uploadPath + selectedFile.getName();
                        } else {
                            uploadUrl = uploadPath + '/' + selectedFile.getName();
                        }
                        CommonUtil.getLogger(DefaultFileUploadAction.class.getName()).log(Level.INFO, "upload file to {0}", uploadUrl);

                        try {
                            URL url = new URL(uploadUrl);
                            URLConnection conn = url.openConnection();
                            OutputStream outputStream = conn.getOutputStream();
                            FileInputStream inputStream = new FileInputStream(selectedFile);

                            byte[] buffer = new byte[4096];
                            int bytesRead = -1;
                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }

                            inputStream.close();
                            outputStream.close();

                            CommonUtil.getLogger(DefaultFileUploadAction.class.getName()).log(Level.INFO, "upload file to {0}", uploadUrl);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        CommonUtil.getLogger(DefaultFileUploadAction.class.getName()).log(Level.INFO,
                                "Give up importing file type {0}", filter);
                    }
                } else {
                    CommonUtil.showMsg(CommonUtil.getCurrentView(),
                            CommonUtil.getResourceMsg("device.usb.disconnected.error"));
                }
            }
        });
    }
}
