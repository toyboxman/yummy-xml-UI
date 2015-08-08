/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.test.common;

import java.io.IOException;

/**
 * http://stackoverflow.com/questions/4948420/open-the-windows-virtual-keyboard-in-a-java-program
 * http://stackoverflow.com/questions/9281315/java-invoke-on-screen-keyboard
 * <br>
 * https://kenai.com/projects/jvirtualkeyboard
 *
 * @author LiuJin
 */
public class TestVirtualKeyBoard {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        String sysroot = System.getenv("SystemRoot");
        Process proc = Runtime.getRuntime().exec("cmd /c " + sysroot + "/system32/osk.exe");
//        Thread.sleep(1000 * 5);
        proc.destroy();
    }
}
