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
public class PIDCardConductor {

    public static final String PID_CARD_READ_ERROR_PROMPT = "pid.operation.card.read.error.prompt";
    public static final String PID_CARD_INSERT_PROMPT = "pid.operation.card.insert.prompt";

    public native String readCard(String comport);
}
