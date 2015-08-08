package king.flow.control.driver;

/**
 *
 * @author LiuJin
 */
public class MagnetCardConductor {

    /**
     * retrieve account details
     *
     * @return account information contained in magnet card
     */
    public native String readCard(String comPort, String errMsg);
}
