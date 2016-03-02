/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.ui.design;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import static king.flow.common.CommonUtil.getLogger;
import king.flow.control.BankAppStarter;
import static king.flow.test.devops.AutomaticTestBankApp.checkStartup;
import king.flow.view.BasicAttribute;
import king.flow.view.Panel;

/**
 *
 * @author liujin
 */
public class DesignBankAppGUI {

    public static void setup() {
        bankAppStarter = new BankAppStarter();
    }

    public static BankAppStarter bankAppStarter;

    public static void main(String[] args) {
        setup();

        java.awt.EventQueue.invokeLater(bankAppStarter::start);

        checkStartup();

        Set<Map.Entry<Panel, String>> pages = bankAppStarter.retrievePages();
        for (Map.Entry<Panel, String> page : pages) {
            List<king.flow.view.Component> componentList = page.getKey().getComponent();
            for (king.flow.view.Component component : componentList) {
                switch (component.getType()) {
                    case LABEL:
                    case TEXT_FIELD:
                    case PASSWORD_FIELD:
                    case BUTTON:
                    case COMBO_BOX:
                        JComponent target = bankAppStarter.retrieveComponent(component.getId(), JComponent.class);
                        final MouseListenerImpl mouseListenerImpl = new MouseListenerImpl(target, page, component);
                        target.addMouseListener(mouseListenerImpl);
                        target.addMouseMotionListener(mouseListenerImpl);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static class MouseListenerImpl implements MouseListener, MouseMotionListener {
        
        Map.Entry<Panel, String> panel;
        king.flow.view.Component componentMeta;
        JComponent srcComp;
        final JPopupMenu popupMenu;
        final JMenuItem saveItem;
        int startX, startY;
        int endX, endY;

        public MouseListenerImpl(JComponent source, Map.Entry<Panel, String> page, king.flow.view.Component component) {
            this.srcComp = source;
            this.panel = page;
            this.componentMeta = component;
            this.popupMenu = new JPopupMenu();
            this.saveItem = new JMenuItem("Save");
            this.popupMenu.add(saveItem);
            this.saveItem.addActionListener((ActionEvent e) -> {
                BasicAttribute attribute = componentMeta.getAttribute();
                king.flow.view.Bound rect = attribute.getRect();
                rect.setX(srcComp.getBounds().x);
                rect.setY(srcComp.getBounds().y);
            });
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() != MouseEvent.BUTTON3) {
                return;
            }
            popupMenu.show(srcComp, e.getX(), e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            startX = e.getXOnScreen();
            startY = e.getYOnScreen();
            getLogger(DesignBankAppGUI.class.getName()).log(Level.CONFIG,
                    "start x coordination[{0}] and y coordination[{1}]", new Object[]{startX, startY});
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            endX = e.getXOnScreen();
            endY = e.getYOnScreen();
            getLogger(DesignBankAppGUI.class.getName()).log(Level.CONFIG,
                    "itermediate x coordination[{0}] and y coordination[{1}]", new Object[]{endX, endY});
            final int movedX = srcComp.getBounds().x + endX - startX;
            final int movedY = srcComp.getBounds().y + endY - startY;
            srcComp.setLocation(movedX, movedY);
            getLogger(DesignBankAppGUI.class.getName()).log(Level.CONFIG,
                    "moved x coordination[{0}] and y coordination[{1}]", new Object[]{movedX, movedY});
            startX = endX;
            startY = endY;
            srcComp.revalidate();
            srcComp.repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }
}
