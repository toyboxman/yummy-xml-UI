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
    public static final String CARD2_TYPE = "2";
    public static final String CARD3_TYPE = "3";
    public static final String CARD4_TYPE = "4";
    public static final String UNSUPPORT_CARD_TYPE = "0";
    public static final String UNREGISTRY_CARD_TYPE = "134";
    public static final String ZERO_GAS_SURPLUS = "0";
    public static final String GUOZHEN_CARD_INSERT_PROMPT = "guozhen.operation.card.insert.prompt";
    public static final String GUOZHEN_CARD_OPERATION_PROMPT = "guozhen.operation.card.type.prompt";
    public static final String GUOZHEN_CARD_BUY_PROMPT = "guozhen.operation.card.surplus.prompt";
    public static final String GUOZHEN_CARD_PERIOD_PROMPT = "guozhen.operation.card.period.prompt";
    public static final String GUOZHEN_CARD_INVALID_PROMPT = "guozhen.operation.card.invalid.prompt";
    public static final String GUOZHEN_CARD_UNREGISTRY_PROMPT = "guozhen.operation.card.unregistry.prompt";

    public native String readCard(String comport);

    public native int writeCard(String comport, int cardfactory, String writeinfo);
}
