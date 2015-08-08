/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package king.flow.action.customization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import king.flow.action.DefaultAction;

/**
 *
 * @author LiuJin
 */
public class ButtonExitAction extends DefaultAction<JButton>{

    @Override
    public void setupListener() {
        owner.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
}
