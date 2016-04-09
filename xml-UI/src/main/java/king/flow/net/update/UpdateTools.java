/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.net.update;

import com.github.jsonj.JsonObject;
import java.net.MalformedURLException;
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
    
    public HttpUpdateTool createHttpUpdateTool(JsonObject updateProperties) throws MalformedURLException {
        return new HttpUpdateTool(updateProperties);
    }
}
