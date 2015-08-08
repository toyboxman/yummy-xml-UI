/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.customization;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import king.flow.action.DefaultBaseAction;
import static king.flow.common.CommonConstants.DEFAULT_TABLE_ROW_COUNT;
import static king.flow.common.CommonUtil.getLogger;
import king.flow.common.CommonUtil;

/**
 *
 * @author Administrator
 */
public class PrintPassbookAction extends DefaultBaseAction {

    private final int passbookId;

    public PrintPassbookAction(int passbookTableId) {
        this.passbookId = passbookTableId;
    }

    @Override
    protected void installButtonAction() {
        JButton btn = (JButton) owner;
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JTable transactionRecords = (JTable) components.get(passbookId);
                DefaultTableModel model = (DefaultTableModel) transactionRecords.getModel();
                if (model.getRowCount() == DEFAULT_TABLE_ROW_COUNT) {
                    boolean fakeRow = false;
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        String value = (String) model.getValueAt(0, i);
                        fakeRow |= (value == null);
                    }
                    if (fakeRow) {
                        CommonUtil.showMsg(owner.getTopLevelAncestor(),
                                CommonUtil.getResourceMsg("printer.passbook.no.record"));
                        return;
                    }
                }
                
                StringBuilder content = new StringBuilder();
                for (Iterator it = model.getDataVector().iterator(); it.hasNext();) {
                    Vector<String> row = (Vector<String>) it.next();
                    for (Iterator<String> rit = row.iterator(); rit.hasNext();) {
                        String field = rit.next();
                        content.append(field);
                        if (rit.hasNext()) {
                            content.append('/');
                        }
                    }
                    if (it.hasNext()) {
                        content.append(',');
                    }
                }
                
                String value = content.toString();
                getLogger(PrintPassbookAction.class.getName()).log(Level.INFO, "print passbook with content\n {0}", value);
                new PrintPassbookTask(value).execute();
            }
        });
    }

//    static int times = 0;
//
//    private int print() {
//        try {
//            ++times;
//            System.out.println("print page " + times);
//            Thread.sleep(TimeUnit.SECONDS.toMillis(5));
//            return times <= 5 ? 1 : 0;
//        } catch (InterruptedException ex) {
//            Logger.getLogger(PrintPassbookAction.class.getName()).log(Level.SEVERE, null, ex);
//            return 0;
//        }
//    }

    private class PrintPassbookTask extends SwingWorker<Integer, Integer> {

        private final JLabel ban;
        private JDialog promptDlg;
        private final String content;

        public PrintPassbookTask(String content) {
            ban = new JLabel(CommonUtil.getResourceMsg("printer.passbook.wait"));
            ban.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
            ban.setFont(UIManager.getFont("OptionPane.messageFont"));
            this.content = content;
        }

        private JDialog createPrompt() {
            JDialog prompt = new JDialog(CommonUtil.getCurrentView());
            prompt.setModal(true);
            prompt.setTitle(CommonUtil.getResourceMsg("bank.app.info.dialog.title"));
            final JLabel topPad = new JLabel();
            topPad.setPreferredSize(new Dimension(700, 30));
            final JLabel downPad = new JLabel();
            downPad.setPreferredSize(new Dimension(700, 70));
            final JLabel leftPad = new JLabel();
            leftPad.setPreferredSize(new Dimension(20, 30));
            prompt.getContentPane().add(ban, BorderLayout.CENTER);
            prompt.getContentPane().add(topPad, BorderLayout.NORTH);
            prompt.getContentPane().add(downPad, BorderLayout.SOUTH);
            prompt.getContentPane().add(leftPad, BorderLayout.WEST);
            prompt.pack();
            prompt.setLocationRelativeTo(null);
            prompt.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            return prompt;
        }

        @Override
        protected Integer doInBackground() throws Exception {
            promptDlg = createPrompt();
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    promptDlg.setVisible(true);
                }
            });
//            while (print() == 1) {
//                ban.setText(CommonUtil.getResourceMsg("printer.passbook.continue"));
//            }
            while (CommonUtil.printPassbook(content) == 1) {
                ban.setText(CommonUtil.getResourceMsg("printer.passbook.continue"));
            }
            return 0;
        }

        @Override
        protected void done() {
            try {
                get();
                Logger.getLogger(PrintPassbookAction.class.getName()).log(Level.INFO, "succeed in printing all tranction records");
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(PrintPassbookAction.class.getName()).log(Level.SEVERE, null, ex);
                CommonUtil.showErrorMsg(owner.getTopLevelAncestor(), CommonUtil.getResourceMsg("printer.passbook.error"));
            } finally {
                cleanRecords();
                promptDlg.dispose();
            }
        }

        private void cleanRecords() {
            JTable table = getBlock(passbookId, JTable.class);
            ((DefaultTableModel) table.getModel()).setRowCount(0);
            ((DefaultTableModel) table.getModel()).setRowCount(DEFAULT_TABLE_ROW_COUNT);
        }
    }
}
