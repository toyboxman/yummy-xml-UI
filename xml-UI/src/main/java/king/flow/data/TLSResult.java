/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.data;

import static king.flow.data.ObjectFactory._Cargo_QNAME;
import static king.flow.data.ObjectFactory._Errmsg_QNAME;
import static king.flow.data.ObjectFactory._Okmsg_QNAME;
import static king.flow.data.ObjectFactory._Retcode_QNAME;

/**
 *
 * @author LiuJin
 */
public class TLSResult {

    public final static String OK_MSG = _Okmsg_QNAME.getLocalPart();
    public final static String ERR_MSG = _Errmsg_QNAME.getLocalPart();
    public final static String RET_CODE = _Retcode_QNAME.getLocalPart();
    public final static String CARGO = _Cargo_QNAME.getLocalPart();
    public final static String PRT_MSG = "prtmsg";

    private int retCode;
    private String okMsg;
    private String errMsg;
    private String prtMsg;

    public TLSResult(int retCode, String okMsg, String errMsg, String prtMsg) {
        this.retCode = retCode;
        this.okMsg = okMsg;
        this.errMsg = errMsg;
        this.prtMsg = prtMsg;
    }

    public TLSResult() {
    }

    public String getPrtMsg() {
        return prtMsg;
    }

    public void setPrtMsg(String prtMsg) {
        this.prtMsg = prtMsg;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getOkMsg() {
        return okMsg;
    }

    public void setOkMsg(String okMsg) {
        this.okMsg = okMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("TLSResult{").append("retCode=").append(retCode)
                .append(", okMsg=").append(okMsg).append(", errMsg=").append(errMsg)
                .append(", prtMsg=").append(prtMsg).append("}").toString();
    }
}
