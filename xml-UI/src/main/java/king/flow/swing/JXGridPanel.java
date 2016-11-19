/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import king.flow.common.CommonUtil;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;

/**
 *
 * @author liujin
 */
public class JXGridPanel extends JXPanel {

    private final int row, column, hgap, vgap;
    private final List<JXPanel> centralPanels;
    private final JXButton nextBtn;
    private final JXButton previousBtn;
    private JXPanel currentPage;
    private int cursor;

    public JXGridPanel(int row, int column, int hgap, int vgap, int width, int height) {
        super(new BorderLayout());
        super.setBounds(new Rectangle(width, height));
        ((BorderLayout) super.getLayout()).setHgap(5);
        this.row = row;
        this.column = column;
        this.hgap = hgap;
        this.vgap = vgap;
        this.centralPanels = new ArrayList<>();

        final JXPanel previousPanel = new JXPanel();
        super.add(previousPanel, BorderLayout.WEST);
        previousBtn = new JXButton("<html><h3>&lt;&lt;</html>");
        nextBtn = new JXButton("<html><h3>&gt;&gt;</html>");
        previousPanel.setLayout(null);
        previousPanel.add(previousBtn);
        previousPanel.setPreferredSize(new Dimension(50, 50));
        previousBtn.setBounds(0, (height - 50) / 2, 50, 50);
        previousBtn.addActionListener((ActionEvent e) -> {
            fadeOut();
        });

        final JXPanel nextPanel = new JXPanel();
        super.add(nextPanel, BorderLayout.EAST);

        nextPanel.setLayout(null);
        nextPanel.add(nextBtn);
        nextPanel.setPreferredSize(new Dimension(50, 50));
        nextBtn.setBounds(0, (height - 50) / 2, 50, 50);
        nextBtn.addActionListener((ActionEvent e) -> {
            fadeIn();
        });
    }

    public void setDataModel(String[] dataModel) {
        if (dataModel == null) {
            CommonUtil.getLogger(JXGridPanel.class.getName()).log(Level.WARNING,
                    "Invalid gridPanel data model {0}", dataModel);
            return;
        }
        int maxDataCount = row * column;
        int divide = dataModel.length / maxDataCount;
        int surplus = dataModel.length % maxDataCount;
        int pageNum = divide + (surplus == 0 ? 0 : 1);
        for (int i = 0; i < pageNum; i++) {
            JXPanel centralPanel = new JXPanel(new GridLayout(row, column, hgap, vgap));
            centralPanels.add(centralPanel);
            for (int j = i * maxDataCount; j < ((i + 1) * maxDataCount < dataModel.length ? (i + 1) * maxDataCount : dataModel.length); j++) {
                JXLabel gridElement = new JXLabel(dataModel[j]);
                gridElement.setBorder(new LineBorder(Color.BLACK, 1));
                centralPanel.add(gridElement);
            }
        }
        cursor = -1;
        fadeIn();
    }

    private void fadeIn() {
        if (currentPage != null) {
            this.remove(currentPage);
        }
        cursor++;
        currentPage = centralPanels.get(cursor);
        this.add(currentPage, BorderLayout.CENTER);
        previousBtn.setEnabled(cursor > 0);
        nextBtn.setEnabled(cursor < centralPanels.size() - 1);
        this.validate();
        this.updateUI();
    }

    private void fadeOut() {
        if (currentPage != null) {
            this.remove(currentPage);
        }
        cursor--;
        currentPage = centralPanels.get(cursor);
        this.add(currentPage, BorderLayout.CENTER);
        previousBtn.setEnabled(cursor > 0);
        nextBtn.setEnabled(cursor < centralPanels.size() - 1);
        this.validate();
        this.updateUI();
    }

    public static void main(String[] args) {
        String[] data = new String[]{"AAA20", "BBB20", "CCC10", "DDD10"};
        JDialog jDialog = new JDialog();
        final JXGridPanel jxGridPanel = new JXGridPanel(1, 2, 10, 10, 800, 600);
        jxGridPanel.setDataModel(data);
//        jDialog.getRootPane().getContentPane().setLayout(null);
        jDialog.getRootPane().getContentPane().add(jxGridPanel, BorderLayout.CENTER);
//        jxGridPanel.setBounds(0, 0, 800, 600);
        jDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        SwingUtilities.invokeLater(() -> {
            jDialog.setSize(800, 600);
            jDialog.setLocationRelativeTo(null);
            jDialog.setVisible(true);
        });
    }
}
