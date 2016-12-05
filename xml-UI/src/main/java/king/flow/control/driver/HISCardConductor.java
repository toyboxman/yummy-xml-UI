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
public class HISCardConductor {

    public static final String HIS_CARD_PICKUP_PROMPT = "his.operation.card.pickup.prompt";
    public static final String HIS_CARD_PICKUP_ERROR_PROMPT = "his.operation.card.pickup.error.prompt";

    public native String getCard(String comport);

    public native String ejectCard(String comport);
    
    public native String withdrawCard(String comport);
}
