/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.customization;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import javax.swing.JComponent;
import king.flow.action.DefaultBaseAction;
import king.flow.common.CommonUtil;

/**
 *
 * @author Administrator
 */
public class CursorMoveAction extends DefaultBaseAction {

    private final int previous;
    private final int next;

    public CursorMoveAction(int previous, int next) {
        this.previous = previous;
        this.next = next;
    }

    @Override
    protected void installComboboxAction() {
        trackArrowKey();
    }

    @Override
    protected void installTextFieldAction() {
        trackArrowKey();
    }

    @Override
    protected void installButtonAction() {
        trackArrowKey();
    }

    void trackArrowKey() {
        owner.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    CommonUtil.getLogger(CursorMoveAction.class.getName()).log(Level.INFO, "jump to next component");
                    JComponent nextComponent = (JComponent) components.get(next);
                    nextComponent.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    CommonUtil.getLogger(CursorMoveAction.class.getName()).log(Level.INFO, "jump to previous component");
                    JComponent preComponent = (JComponent) components.get(previous);
                    preComponent.requestFocus();
                }
            }
        });
    }

}
