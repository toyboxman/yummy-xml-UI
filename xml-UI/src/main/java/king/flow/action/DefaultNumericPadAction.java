/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import javax.swing.JComponent;
import javax.swing.JTextField;
import king.flow.swing.JXNumericPad;

/**
 *
 * @author liujin
 */
public class DefaultNumericPadAction extends DefaultAction<JComponent> {
    
    private final int targetId;
    
    public DefaultNumericPadAction(int targetId) {
        this.targetId = targetId;
    }
    
    private void init() {
        JTextField input = getBlock(this.targetId, JTextField.class);
        JXNumericPad pad = (JXNumericPad) owner;
        pad.setTarget(input);
    }
    
    @Override
    public void setupListener() {
        init();
    }
    
}
