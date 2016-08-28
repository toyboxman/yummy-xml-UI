/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.agent;

import javax.management.MXBean;

/**
 *
 * @author Administrator
 */
@MXBean
public interface OpenCLI {

    void showApp();

    void hideApp();

    String getVersion();
}
