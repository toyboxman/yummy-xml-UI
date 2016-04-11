package king.flow.control.driver;

/**
 *
 * @author LiuJin
 */
public class KeyBoardDriver {

    public native boolean downloadSecretKey(String comPort, String mainkey, String macworkkey, String pinworkkey);

    public native void openPin(String comPort, String errMsg);

    public native char readPin();

    public native String closePin();

    public native String getPin(String comPort, String input, String errMsg);

    //public native String macCaculate();
    //public native String encryptData();
    
    //add for keyboard encrption mode
    public native int OpenComm(String comport, String errstring);
    public native String ScanKeyPress(String errstring);
    public native String GetPinblock(String cardno, String cardflag, String errstring);
    public native int CloseComm(String comport, String errstring);
}
