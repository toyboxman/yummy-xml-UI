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
public class GzCardConductor {

    public static final String CARD_NO = "cardno";
    public static final String CARD_SPARE = "cardspare";
    public static final String CARD_FACTORY = "cardfactory";
    public static final String CARD_GAS_COUNT = "gascount";
    public static final String WRITE_CARD_CODE = "writecardcode";
    public static final String CARD_2_TYPE = "2";
    public static final String CARD_3_TYPE = "3";
    public static final String UNSUPPORT_CARD_TYPE = "4";

    public native String readCard(String comport);

    public native int writeCard(String comport, int cardfactory, String writeinfo);
}
