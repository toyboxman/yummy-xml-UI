/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.swing;

import com.github.jsonj.JsonArray;
import com.github.jsonj.JsonObject;
import com.github.jsonj.tools.JsonParser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
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

    private int row, column, hgap, vgap;
    private final List<JXPanel> centralPanels;
    private final JXButton nextBtn;
    private final JXButton previousBtn;
    private JXPanel currentPage;
    private int cursor;
    private final AtomicReference<GridElement> choosenElement;

    public JXGridPanel() {
        super(new BorderLayout());
        super.setOpaque(false);
        this.row = 2;
        this.column = 2;
        this.hgap = 10;
        this.vgap = 10;
        this.centralPanels = new ArrayList<>();
        choosenElement = new AtomicReference<>();

        final JXPanel previousPanel = new JXPanel();
        super.add(previousPanel, BorderLayout.WEST);
        previousBtn = new JXButton("<html><h1>&lt;&lt;</html>");
        nextBtn = new JXButton("<html><h1>&gt;&gt;</html>");
        previousPanel.setLayout(null);
        previousPanel.add(previousBtn);
        previousPanel.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        previousPanel.setOpaque(false);
        previousBtn.addActionListener((ActionEvent e) -> {
            fadeOut();
        });

        final JXPanel nextPanel = new JXPanel();
        super.add(nextPanel, BorderLayout.EAST);

        nextPanel.setLayout(null);
        nextPanel.add(nextBtn);
        nextPanel.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        nextPanel.setOpaque(false);
        nextBtn.addActionListener((ActionEvent e) -> {
            fadeIn();
        });
    }
    private static final int BUTTON_HEIGHT = 60;
    private static final int BUTTON_WIDTH = 60;

    public JXGridPanel(int row, int column, int hgap, int vgap, int width, int height) {
        super(new BorderLayout());
        super.setOpaque(false);
        this.row = row;
        this.column = column;
        this.hgap = hgap;
        this.vgap = vgap;
        this.centralPanels = new ArrayList<>();
        choosenElement = new AtomicReference<>();

        final JXPanel previousPanel = new JXPanel();
        super.add(previousPanel, BorderLayout.WEST);
        previousBtn = new JXButton("<html><h3>&lt;&lt;</html>");
        nextBtn = new JXButton("<html><h3>&gt;&gt;</html>");
        previousPanel.setLayout(null);
        previousPanel.add(previousBtn);
        previousPanel.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        previousBtn.setBounds(0, (height - BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        previousBtn.addActionListener((ActionEvent e) -> {
            fadeOut();
        });

        final JXPanel nextPanel = new JXPanel();
        super.add(nextPanel, BorderLayout.EAST);

        nextPanel.setLayout(null);
        nextPanel.add(nextBtn);
        nextPanel.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        nextBtn.setBounds(0, (height - BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        nextBtn.addActionListener((ActionEvent e) -> {
            fadeIn();
        });

        super.setBounds(new Rectangle(width, height));
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        previousBtn.setBounds(0, (height - BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        nextBtn.setBounds(0, (height - BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    public void setGridLayout(int row, int column, int hgap, int vgap) {
        this.row = row;
        this.column = column;
        this.hgap = hgap;
        this.vgap = vgap;
    }

    public void setDataModel(JsonArray dataModel) {
        if (dataModel == null) {
            CommonUtil.getLogger(JXGridPanel.class.getName()).log(Level.WARNING,
                    "Invalid gridPanel data model {0}", dataModel);
            return;
        }

        for (JXPanel panel : centralPanels) {
            if (this.isAncestorOf(panel)) {
                this.remove(panel);
            }
        }
        centralPanels.clear();

        int maxDataCount = row * column;
        int divide = dataModel.size() / maxDataCount;
        int surplus = dataModel.size() % maxDataCount;
        int pageNum = divide + (surplus == 0 ? 0 : 1);
        for (int i = 0; i < pageNum; i++) {
            JXPanel centralPanel = new JXPanel(new GridLayout(row, column, hgap, vgap));
            centralPanel.setOpaque(false);
            centralPanels.add(centralPanel);
            for (int j = i * maxDataCount; j < ((i + 1) * maxDataCount < dataModel.size() ? (i + 1) * maxDataCount : dataModel.size()); j++) {
                JsonObject cell = dataModel.get(j).asObject();
                final JXLabel cellElement = new JXLabel(cell.getString(DISPLAY_KEY));
                cellElement.setOpaque(false);
                GridElement<JXLabel> gridElement = new GridElement<>(cell, cellElement);
                gridElement.insert(centralPanel);
            }
            int emptyCount = (i + 1) * maxDataCount - dataModel.size();
            if (emptyCount > 0) {
                final JXLabel vacantCell = new JXLabel();
                vacantCell.setOpaque(false);
                centralPanel.add(vacantCell);
            }
        }
        cursor = -1;
        fadeIn();
    }

    private static final String ID_KEY = "id";
    private static final String DISPLAY_KEY = "display";

    public String getValue() {
        if (choosenElement.get() != null) {
            return choosenElement.get().getData().getString(ID_KEY);
        }
        return "";
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
        String jsonData = "[{\"id\":\"131\", \"display\":\"AA\"},{\"id\":\"157\", \"display\":\"BB\"},{\"id\":\"171\", \"display\":\"CC\"}]";
        JsonParser jsonParser = new JsonParser();
        JsonArray array = null;
        array = jsonParser.parse(jsonData).asArray();

        JDialog jDialog = new JDialog();
        final JXGridPanel jxGridPanel = new JXGridPanel(2, 2, 10, 10, 800, 600);
        jxGridPanel.setDataModel(array);
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

    private class GridElement<E extends JComponent> {

        E component;
        JsonObject data;

        public GridElement(JsonObject json, E e) {
            this.component = e;
            this.data = json;
            component.setBorder(new LineBorder(Color.BLACK, 1));
            this.component.addMouseListener(new GridElementMouseAdapter(component));
        }

        public void insert(JPanel panel) {
            panel.add(component);
        }

        public JsonObject getData() {
            return data;
        }

        private class GridElementMouseAdapter extends MouseAdapter {

            private final E component;

            public GridElementMouseAdapter(E component) {
                this.component = component;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (choosenElement.get() != null) {
                    GridElement old = choosenElement.getAndSet(GridElement.this);
                    old.component.setBorder(new LineBorder(UNSELECTED_COLOR, UNSELECTED_LINE_THICKNESS, true));
                } else {
                    choosenElement.set(GridElement.this);
                }
                component.setBorder(new LineBorder(SELECTED_COLOR, SELECTED_LINE_THICKNESS, true));
            }
        }
    }
    private static final Color SELECTED_COLOR = Color.RED;
    private static final int SELECTED_LINE_THICKNESS = 2;
    private static final Color UNSELECTED_COLOR = Color.BLACK;
    private static final int UNSELECTED_LINE_THICKNESS = 1;
}
