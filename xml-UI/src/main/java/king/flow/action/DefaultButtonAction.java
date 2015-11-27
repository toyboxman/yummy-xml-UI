package king.flow.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;

/**
 *
 * @author LiuJin
 */
public class DefaultButtonAction extends DefaultAction<JButton> {

    private final int next_panel_id;
    private final Integer next_cursor;

    public DefaultButtonAction(int nextPanel) {
        this.next_panel_id = nextPanel;
        this.next_cursor = null;
    }

    public DefaultButtonAction(int nextPanel, int nextCusor) {
        this.next_panel_id = nextPanel;
        this.next_cursor = nextCusor;
    }

    @Override
    public void setupListener() {
        this.owner.addActionListener(new DefaultButtonActionListenerImpl());
    }

    private class DefaultButtonActionListenerImpl implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelJump(next_panel_id);
            if (next_cursor != null && getBlockMeta(next_cursor) != null) {
                JComponent block = getBlock(next_cursor, JComponent.class);
                block.requestFocusInWindow();
            }
        }
    }

}
