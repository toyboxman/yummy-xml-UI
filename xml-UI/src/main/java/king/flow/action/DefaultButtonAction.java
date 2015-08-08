package king.flow.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author LiuJin
 */
public class DefaultButtonAction extends DefaultAction<JButton> {

    private int next_panel_id = 0;

    public DefaultButtonAction(int nextPanel) {
        this.next_panel_id = nextPanel;
    }

    @Override
    public void setupListener() {
        this.owner.addActionListener(new DefaultButtonActionListenerImpl(this.next_panel_id));
    }
    
    private class DefaultButtonActionListenerImpl implements ActionListener {

        private int next_id = 0;

        public DefaultButtonActionListenerImpl(int panel_id) {
            this.next_id = panel_id;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            panelJump(this.next_id);
        }
    }

}
