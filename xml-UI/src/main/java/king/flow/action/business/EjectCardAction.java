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
public class EjectCardAction extends DefaultBaseAction {

    private final DeviceEnum type;

    public EjectCardAction(DeviceEnum type) {
        this.type = type;
    }

    @Override
    protected void installButtonAction() {
        JButton button = (JButton) owner;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EjectCardTask().execute();
            }
        });
    }

    private class EjectCardTask extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() throws Exception {
            switch (type) {
                case TWO_IN_ONE_CARD:
                    CommonUtil.eject2In1Card();
                    break;
                case HIS_CARD:
                    CommonUtil.ejectHISCard();
                    break;
                case CASH_SAVER:
                    CommonUtil.closeCashSaver();
                    break;
                default:
                    getLogger(EjectCardTask.class.getName()).log(Level.WARNING,
                        "Unsupported ejected IC card type : {0}", type.value());
            }
            
            return null;
        }

    }
}
