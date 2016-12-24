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
public class MedicareCardConductor {

    public static String MEDICARE_CARD_INSERT_PROMPT = "medicare.operation.insert.prompt";
    public static String MEDICARE_CARD_OPERATION_ERROR_PROMPT = "medicare.operation.error.prompt";

    public native String runCmd(String jsonParameters);
}
