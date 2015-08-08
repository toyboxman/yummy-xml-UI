/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 *
 * @author Administrator
 */
public class DefaultMenuAction extends DefaultMenuBaseAction<JMenuItem> {

    private final int next_panel_id;

    public DefaultMenuAction(int next_panel_id) {
        this.next_panel_id = next_panel_id;
    }

    @Override
    protected void intallActionListener() {
        JMenuItem item = this.getBlock(id, JMenuItem.class);
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panelJump(next_panel_id);
            }
        });
    }

}
