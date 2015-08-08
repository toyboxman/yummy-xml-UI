/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package king.flow.test.common;

import king.flow.common.CommonUtil;

/**
 *
 * @author LiuJin
 */
public class TestCommonUtil {
    private static final String TLSMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><TLS><prscode>queryAcount</prscode><N_2000206>111</N_2000206><N_2000204>111</N_2000204><retcode>9999</retcode><errmsg>9999</errmsg></TLS>";
    
    public static void main(String ... args) {
        CommonUtil.parseTLSMessage(TLSMessage);
        String registryTLSMessage = CommonUtil.createRegistryTLSMessage("login", "0001", "token:sdioiljfdiiw");
        System.out.println("registry message : " + registryTLSMessage);
    }
    
}
