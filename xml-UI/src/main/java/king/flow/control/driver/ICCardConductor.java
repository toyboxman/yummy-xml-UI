package king.flow.control.driver;

/**
 *
 * @author LiuJin
 */
public class ICCardConductor {

    /**
     * 
     * @return
     */
    public native String readCard(String comPort, String errMsg);
}
