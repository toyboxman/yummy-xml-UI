/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.test.driver;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import king.flow.common.CommonUtil;
import king.flow.control.driver.KeyBoardDriver;
import king.flow.control.driver.MagnetCardConductor;

/**
 *
 * @author LiuJin
 */
public class TestKeyBoardDriver {

    public static void main(String[] args) {
//        CommonUtil.getLogger(TestKeyBoardDriver.class.getName()).log(Level.INFO, "System.loadLibrary(\"emptydll\")");
//        CommonUtil.getLogger(TestKeyBoardDriver.class.getName()).log(Level.INFO, 
//                "System.getProperty(\"java.library.path\") : " + System.getProperty("java.library.path"));
//        System.load("driverdll.lib");
//        System.load("emptydll.dll");
//        long start1 = System.currentTimeMillis();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(TestKeyBoardDriver.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        long now1 = System.currentTimeMillis();
//        long duration1 = TimeUnit.MILLISECONDS.toSeconds(now1 - start1);
//        System.out.println(duration1 + "///////////99999999999");

        try {
            System.loadLibrary("emptydll");

            try {
                KeyBoardDriver keyBoardDriver = new KeyBoardDriver();
                keyBoardDriver.downloadSecretKey("com3", "1234567890ABCD00", "1234567890ABCD00", "1234567890ABCDEF");
//                keyBoardDriver.openPin("com3", "");
//                long start = System.currentTimeMillis();
//                while (true) {
//                    long now = System.currentTimeMillis();
//                    long duration = TimeUnit.MILLISECONDS.toSeconds(now - start);
//                    if (duration > 15) {
//                        CommonUtil.getLogger(TestKeyBoardDriver.class.getName()).log(Level.INFO, "read pin time out");
//                        break;
//                    }
//                    char ch = keyBoardDriver.readPin();
//                    if (ch == 0) {
//                        continue;
//                    }
//                    CommonUtil.getLogger(TestKeyBoardDriver.class.getName()).log(Level.INFO, "read pin result {0}",
//                            Integer.toHexString(ch));
//                    if (ch == 0x0d || ch == 0xaa) {
//                        CommonUtil.getLogger(TestKeyBoardDriver.class.getName()).log(Level.INFO, "read pin ends up with {0}", ch);
//                        String closePin = keyBoardDriver.closePin();
//                        CommonUtil.getLogger(TestKeyBoardDriver.class.getName()).log(Level.INFO, "final reuslt {0}", closePin);
//                        break;
//                    }
//                }
            } catch (Exception e) {
                CommonUtil.getLogger(TestKeyBoardDriver.class.getName()).log(Level.INFO, "exception quit", e);
            }
                //        System.loadLibrary("driverdll");
//        KeyBoardDriver driver = new KeyBoardDriver();
//        driver.getPin((char)6, (char)4, (char)15);
                //test magnet card
//            MagnetCardConductor magnetCardConductor = new MagnetCardConductor();
//            String err = "";
//            String card = null;
//            try {
//                card = magnetCardConductor.readCard("com3", err);
//            } catch (Error e) {
//                CommonUtil.getLogger(TestKeyBoardDriver.class.getName()).log(Level.INFO, "fail to call method", e);
//            }
//            CommonUtil.getLogger(TestKeyBoardDriver.class.getName()).log(Level.INFO, "card information : {0}", card);
            } catch (Error e) {
                CommonUtil.getLogger(TestKeyBoardDriver.class.getName()).log(Level.INFO, "fail to load dll", e);
            }

        }
    }
