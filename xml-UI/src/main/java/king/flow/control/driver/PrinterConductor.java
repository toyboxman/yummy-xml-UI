package king.flow.control.driver;

/**
 *
 * @author LiuJin
 */
public class PrinterConductor {

    public native void print(String comPort, String header, String content, String tail, String errMsg);

    public native int printState(String comPort, String errMsg);
    
    public native int printPassBook(String comPort, String records, String errMsg);
}
