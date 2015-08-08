/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.net;

import static king.flow.common.CommonUtil.createUnauthorizationTLSMessage;
import static king.flow.common.CommonUtil.getResourceMsg;

/**
 *
 * @author LiuJin
 */
public class FakeTunnel implements Tunnel {

    @Override
    public String connect() {
        return createUnauthorizationTLSMessage(Integer.MIN_VALUE,
                getResourceMsg("terminal.unauthorized.prompt"));
    }

    @Override
    public String connect(int commmand, String message) {
        return connect();
    }

}
