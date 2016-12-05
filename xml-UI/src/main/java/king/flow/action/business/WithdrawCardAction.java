/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import king.flow.action.DefaultBaseAction;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.getLogger;
import king.flow.view.DeviceEnum;

/**
 *
 * @author liujin
 */
public class WithdrawCardAction extends DefaultBaseAction {

    private final DeviceEnum type;

    public WithdrawCardAction(DeviceEnum type) {
        this.type = type;
    }

    @Override
    protected void installButtonAction() {
        JButton button = (JButton) owner;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WithdrawCardTask().execute();
            }
        });
    }

    private class WithdrawCardTask extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() throws Exception {
            switch (type) {
                case HIS_CARD:
                    CommonUtil.withdrawHISCard();
                    break;
                default:
                    getLogger(WithdrawCardTask.class.getName()).log(Level.WARNING,
                            "Unsupported withdraw IC card type : {0}", type.value());
            }

            return null;
        }

    }
}
