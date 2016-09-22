/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import king.flow.common.CommonUtil;

/**
 *
 * @author liujin
 */
public class DefaultHideAction extends DefaultBaseAction{

    @Override
    protected void installButtonAction() {
        JButton button = (JButton) this.owner;
        button.addActionListener((ActionEvent e) -> {
            CommonUtil.getCurrentView().setVisible(false);
        });
    }
    
}
