/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.agent;

import com.google.common.collect.ImmutableSet;
import javax.management.MXBean;

/**
 *
 * @author Administrator
 */
@MXBean
public interface OpenCLI {

    String VERSOPM_ATTRIBUTE = "Version";

    String SHOW_APP_NAME = "showApp";

    String HIDE_APP_NAME = "hideApp";

    String SHOW_APP_INFO_NAME = "showAppInfo";

    String LAUNCH_ACTION_NAME = "launchAction";

    ImmutableSet<String> COMMAND_SET = ImmutableSet.of(HIDE_APP_NAME, SHOW_APP_NAME,
            VERSOPM_ATTRIBUTE, SHOW_APP_INFO_NAME, LAUNCH_ACTION_NAME);

    void showApp(String jsonData);

    void hideApp();

    String getVersion();

    String showAppInfo();

    void launchAction(String jsonData);
}
