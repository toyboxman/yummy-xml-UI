/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.customization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JMenu;
import king.flow.action.DefaultBaseAction;
import king.flow.view.Menuoption;

/**
 *
 * @author Liujin
 */
public class SignoffAction extends DefaultBaseAction {

    @Override
    protected void installButtonAction() {
        JButton btn = (JButton) owner;
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Set<Map.Entry<Integer, Object>> entrySet = components.entrySet();
                for (Map.Entry<Integer, Object> entry : entrySet) {
                    Object v = entry.getValue();
                    if (v instanceof JMenu) {
                        Menuoption meta = (Menuoption) getBlockMeta(entry.getKey());
                        ((JMenu) v).setEnabled(meta.isEnable() == null ? false : meta.isEnable());
                    }
                }
            }
        });
    }

}
