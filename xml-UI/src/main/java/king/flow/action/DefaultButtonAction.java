package king.flow.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import static king.flow.common.CommonUtil.getLogger;
import king.flow.view.Component;
import static king.flow.view.ComponentEnum.BUTTON;
import static king.flow.view.ComponentEnum.COMBO_BOX;

/**
 *
 * @author LiuJin
 */
public class DefaultButtonAction extends DefaultAction<JButton> {

    private final int next_panel_id;
    private final Integer next_cursor;
    private final Integer trigger;

    public DefaultButtonAction(int nextPanel) {
        this.next_panel_id = nextPanel;
        this.next_cursor = null;
        this.trigger = null;
    }

    public DefaultButtonAction(int nextPanel, int nextCusor) {
        this.next_panel_id = nextPanel;
        this.next_cursor = nextCusor;
        this.trigger = null;
    }

    public DefaultButtonAction(Integer... config) {
        switch (config.length) {
            case 1:
                this.next_panel_id = config[0];
                this.next_cursor = null;
                this.trigger = null;
                break;
            case 2:
                this.next_panel_id = config[0];
                this.next_cursor = config[1];
                this.trigger = null;
                break;
            default:
                this.next_panel_id = config[0];
                this.next_cursor = config[1];
                this.trigger = config[2];
        }
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
            if (trigger != null && getBlockMeta(trigger) != null) {
                final Component blockMeta = (Component) getBlockMeta(trigger);
                switch (blockMeta.getType()) {
                    case COMBO_BOX:
                        JComboBox comboBlock = getBlock(trigger, JComboBox.class);
                        ItemListener[] itemListeners = comboBlock.getItemListeners();
                        ItemEvent itemEvent = new ItemEvent(comboBlock,
                                ItemEvent.ITEM_STATE_CHANGED,
                                comboBlock.getItemAt(comboBlock.getItemCount() - 1).toString(),
                                ItemEvent.SELECTED);
                        for (ItemListener itemListener : itemListeners) {
                            itemListener.itemStateChanged(itemEvent);//wait and hang on util progress dialog gets to dispose
                        }
                        break;
                    case BUTTON:
                        JButton btnBlock = getBlock(trigger, JButton.class);
                        btnBlock.doClick();
                        break;
                    default:
                        getLogger(DefaultButtonAction.class.getName()).log(Level.WARNING,
                                "Invalid trigger component[{0}] as unsupported type[{1}]",
                                new Object[]{trigger, blockMeta.getType()});
                        break;
                }
            }
        }
    }

}
