package king.flow.control.driver;

/**
 *
 * @author LiuJin
 */
public class KeyBoardDriver {

    public native boolean downloadSecretKey(String comPort, String maKey, String masterKey, String workSecretKey);

    public native void openPin(String comPort, String errMsg);

    public native char readPin();

    public native String closePin();

    public native String getPin(String comPort, String input, String errMsg);

    //public native String macCaculate();
    //public native String encryptData();
}
