/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.driver;

/**
 *
 * @author liujin
 */
public class TwoInOneCardConductor {

    /**
     * this method is targeting 2in1 type card-reading device, as caller doesn't
     * know card type, he should call this to make sure correct state
     *
     * @param comPort
     * @param errMsg
     * @return result of card-checking,
     * <br> -1 means no card inserting or invalid card
     * <br> 2 means magnet card
     * <br> 3 means IC card
     */
    public native int checkCard(String comPort, String errMsg);

    /**
     * this method is blocking caller until timeout happens when it's called.
     * That means this method will return either empty string or valid result
     *
     * @param comPort
     * @param card type, 2 means magnet card, 3 means IC card
     * @param errMsg
     * @return card information
     * <br> valid string of card information
     * <br> empty string meaning timeout of driver reading
     */
    public native String readCard(String comPort, int type, String errMsg);
}
