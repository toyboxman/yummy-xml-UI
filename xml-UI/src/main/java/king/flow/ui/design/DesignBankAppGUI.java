/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.ui.design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import static king.flow.common.CommonConstants.TABLE_ROW_HEIGHT;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.getLogger;
import king.flow.control.BankAppStarter;
import king.flow.design.FlowProcessor;
import static king.flow.test.devops.AutomaticTestBankApp.checkStartup;
import king.flow.view.BasicAttribute;
import king.flow.view.Panel;

/**
 *
 * @author liujin
 */
public class DesignBankAppGUI {

    private static final Font MENU_FONT = new Font("Times New Roman", Font.BOLD, 20);
    private static final Font ID_FONT = MENU_FONT.deriveFont(Font.ITALIC | Font.BOLD, 12f);

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

    private enum EditMenu {
        SHOW_BOUND("Show Bound"), HIDE_BOUND("Hide Bound"),
        PROPERTIES("Edit Properties"), UNDO("Undo"), SAVE("Save"),
        EXIT("Exit");

        private final String menuName;

        private EditMenu(String name) {
            this.menuName = name;
        }

        public String getMenuName() {
            return menuName;
        }
    }

    private static class MouseListenerImpl implements MouseListener, MouseMotionListener {

        Map.Entry<Panel, String> panel;
        king.flow.view.Component componentMeta;
        JComponent srcComp;
        final JPopupMenu popupMenu;
        int startX, startY;
        int endX, endY;

        public MouseListenerImpl(JComponent source, Map.Entry<Panel, String> page, king.flow.view.Component component) {
            this.srcComp = source;
            this.panel = page;
            this.componentMeta = component;
            this.popupMenu = new JPopupMenu();
            initMenuItem();
        }

        private void initMenuItem() {
            for (EditMenu menu : EditMenu.values()) {
                final JMenuItem actionMenu = new JMenuItem(menu.getMenuName());
                actionMenu.setFont(MENU_FONT);
                this.popupMenu.add(actionMenu);
                switch (menu) {
                    case SHOW_BOUND:
                        actionMenu.addActionListener((ActionEvent e) -> {
                            srcComp.setBorder(new TitledBorder(
                                    new LineBorder(Color.MAGENTA, 2, true),
                                    String.valueOf(componentMeta.getId()),
                                    TitledBorder.LEADING, TitledBorder.TOP,
                                    ID_FONT
                            ));
                        });
                        break;
                    case HIDE_BOUND:
                        this.popupMenu.addSeparator();
                        actionMenu.addActionListener((ActionEvent e) -> {
                            srcComp.setBorder(null);
                        });
                        break;
                    case UNDO:
                        actionMenu.addActionListener((ActionEvent e) -> {
                            BasicAttribute attribute = componentMeta.getAttribute();
                            king.flow.view.Bound rect = attribute.getRect();
                            srcComp.setBounds(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeigh());
                            refreshComponent();
                        });
                        break;
                    case PROPERTIES:
                        this.popupMenu.addSeparator();
                        actionMenu.addActionListener((ActionEvent e) -> {
                            JDialog propertiesDialog = new JDialog((Window) srcComp.getTopLevelAncestor(), "Properties");
                            propertiesDialog.setModal(true);
                            propertiesDialog.getContentPane().setLayout(new BorderLayout());
                            final JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
                            final JButton okButton = new JButton("OK");
                            okButton.addActionListener((ActionEvent okEvent) -> {
                                propertiesDialog.setVisible(false);
                            });
                            controlPanel.add(okButton);
                            final JButton cancelButton = new JButton("Cancel");
                            cancelButton.addActionListener((ActionEvent cancelEvent) -> {
                                propertiesDialog.setVisible(false);
                            });
                            controlPanel.add(cancelButton);
                            propertiesDialog.getContentPane().add(controlPanel, BorderLayout.SOUTH);
                            DefaultTableModel defaultTableModel = new DefaultTableModel(new String[]{"Name", "Value"}, 10);
                            final JTable valueTable = new JTable(defaultTableModel);
                            valueTable.getTableHeader().setResizingAllowed(false);
                            valueTable.getTableHeader().setReorderingAllowed(false);
                            valueTable.setRowHeight(TABLE_ROW_HEIGHT);
                            valueTable.getTableHeader().setFont(new Font(valueTable.getFont().getName(),
                                    valueTable.getFont().getStyle(),
                                    16));
                            final JScrollPane viewPanel = new JScrollPane(valueTable);
                            viewPanel.setPreferredSize(new Dimension(150, 300));
                            propertiesDialog.getContentPane().add(viewPanel, BorderLayout.CENTER);
                            propertiesDialog.pack();
                            propertiesDialog.setLocationRelativeTo(null);
                            propertiesDialog.setVisible(true);
                        });
                        break;
                    case SAVE:
                        this.popupMenu.addSeparator();
                        actionMenu.addActionListener((ActionEvent e) -> {
                            BasicAttribute attribute = componentMeta.getAttribute();
                            king.flow.view.Bound rect = attribute.getRect();
                            rect.setX(srcComp.getBounds().x);
                            rect.setY(srcComp.getBounds().y);
                            new SaveFileTask(panel.getValue(), panel.getKey()).execute();
                        });
                        break;
                    case EXIT:
                        actionMenu.addActionListener((ActionEvent e) -> {
                            System.exit(0);
                        });
                        break;
                    default:
                        throw new AssertionError();
                }
            }
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
            refreshComponent();
        }

        protected void refreshComponent() {
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

    private static class SaveFileTask extends SwingWorker<Boolean, Integer> {

        private final String fileUrl;
        private final Panel panelNode;

        public SaveFileTask(String fileUrl, Panel panelNode) {
            this.fileUrl = fileUrl;
            this.panelNode = panelNode;
        }

        @Override
        protected Boolean doInBackground() throws Exception {
            new FlowProcessor(fileUrl).writeOut(panelNode);
            return true;
        }

        @Override
        protected void done() {
            try {
                get();
                Logger.getLogger(DesignBankAppGUI.class.getName()).log(Level.INFO,
                        "Succeed in saving component edited in {0}", fileUrl);
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(DesignBankAppGUI.class.getName()).log(Level.SEVERE,
                        "Fail to save component edited due to error : \n{0}", ex);
                CommonUtil.showErrorMsg(CommonUtil.getCurrentView(), CommonUtil.shapeErrPrompt(ex));
            }
        }
    }

}
