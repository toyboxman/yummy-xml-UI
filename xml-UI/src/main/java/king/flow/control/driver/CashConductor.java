/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.driver;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author liujin
 */
public class CashConductor {

    public static String CASH_INSERT_PROMPT = "cash.operation.insert.prompt";
    public static String CASH_DEPOSITE_ERROR_PROMPT = "cash.operation.deposite.error.prompt";
    
    public static final AtomicBoolean CONTINUE_CASH_DEPOSITION = new AtomicBoolean(true);
    
    public native String open(String comport, String cardId);

    public native String readCash(String comport, String cardId);

    public native String close(String comport, String cardId);
}
