/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.deamon;

import javax.management.MXBean;

/**
 *
 * @author king
 */
@MXBean
public interface CheckNumen {

    String VERSOPM_ATTRIBUTE = "Version";
    String KILL_DEAMON_NAME = "killDeamon";

    String getVersion();

    void killDeamon();
}
