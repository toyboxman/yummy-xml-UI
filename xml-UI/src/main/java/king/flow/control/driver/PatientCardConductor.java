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
public class PatientCardConductor {

    public static final String HIS_CARD_INSERT_PROMPT = "his.operation.card.insert.prompt";
    public static final String HIS_CARD_READ_ERROR_PROMPT = "his.operation.card.read.error.prompt";

    public native String readCard(String comPort, String errMsg);
}
