/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import king.flow.action.DefaultBaseAction;
import king.flow.common.CommonUtil;

/**
 *
 * @author liujin
 */
public class Eject2In1CardAction extends DefaultBaseAction{

    @Override
    protected void installButtonAction() {
        JButton button = (JButton) owner;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Eject2In1CardTask().execute();
            }
        });
    }
    
    private class Eject2In1CardTask extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() throws Exception {
            CommonUtil.eject2In1Card();
            return null;
        }
        
    }
}
