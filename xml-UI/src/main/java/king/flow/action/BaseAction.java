/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Window;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static king.flow.common.CommonUtil.getLogger;

/**
 *
 * @author Administrator
 */
public abstract class BaseAction implements Action {

    protected Map<Integer, Object> components = null;
    protected Map<Integer, Object> components_meta = null;
    protected int id = 0;

    @Override
    public void holdComponents(int owner_id, Map<Integer, Object> components, Map<Integer, Object> components_meta) {
        this.components = components;
        this.components_meta = components_meta;
        this.id = owner_id;
    }

    protected void panelJump(int next_id) {
        JPanel next_panel = getBlock(next_id, JPanel.class);
        Window window = getBlock(Integer.MAX_VALUE, Window.class);
        switch (window.getClass().getSimpleName()) {
            case "JFrame":
                JFrame frame = (JFrame) window;
                frame.getContentPane().removeAll();
                frame.getContentPane().add(next_panel, BorderLayout.CENTER);
                refreshUI(frame);
                break;
            case "JDialog":
                JDialog dialog = (JDialog) window;
                dialog.getContentPane().removeAll();
                dialog.getContentPane().add(next_panel, BorderLayout.CENTER);
                refreshUI(dialog);
                break;
            default:
                getLogger(BaseAction.class.getName()).log(Level.WARNING,
                        "Uneffective container type : {0}", window.getClass().getSimpleName());
        }
    }

    protected <T extends Component> void refreshUI(final T frame) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    protected <T> T getBlock(int key, Class<T> c) {
        return (T) this.components.get(key);
    }

    protected Object getBlockMeta(int key) {
        return this.components_meta.get(key);
    }

}
