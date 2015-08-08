/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.net.update;

import java.net.URL;
import king.flow.net.update.http.HttpUpdateTool;

/**
 *
 * @author liujin
 */
public class UpdateTools {

    private UpdateTools() {
    }

    private static UpdateTools tool = new UpdateTools();

    public static UpdateTools getUpdateTool() {
        if (tool != null) {
            return tool;
        } else {
            return tool = new UpdateTools();
        }
    }
    
    public HttpUpdateTool createHttpUpdateTool(String upgradeVersion,
            URL upgradeUrl, String md5Value) {
        return new HttpUpdateTool(upgradeVersion, upgradeUrl, md5Value);
    }
}
