package king.flow.action;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static king.flow.common.CommonConstants.DEFAULT_TABLE_ROW_COUNT;
import static king.flow.common.CommonUtil.getLogger;
import king.flow.view.Action.ShowTableAction.Sum;
import org.jdesktop.swingx.table.TableRowHeightController;

/**
 *
 * @author LiuJin
 */
public class DefaultTableAction extends DefaultAction<JTable> {

    private final CommonTableModel model;
    private final TableRowHeightController tableRowHeightController;
    private final List<Sum> sumFunctions;

    public DefaultTableAction(List<String> columnNames, List<Sum> sumFunctions) {
        this.model = new CommonTableModel(columnNames);
        this.tableRowHeightController = new TableRowHeightController();
        this.sumFunctions = sumFunctions;
    }

    private void initFunctions(List<Sum> sumFunctions) {
        ArrayList<SumFunction> functionList = new ArrayList<>();
        for (Sum sum : sumFunctions) {
            final String columnName = sum.getColumnName();
            final int columnId = this.model.findColumn(columnName);
            if (columnId == -1) {
                getLogger(CommonTableModel.class.getName()).log(Level.WARNING,
                        "Invalid column name [{0}] is designated to sum function for Table [{1}]",
                        new Object[]{columnName, id});
                continue;
            }
            functionList.add(new SumFunction(columnId,
                    this.getBlock(sum.getDisplay(), JComponent.class)));
        }
        this.model.init(functionList);
    }

    @Override
    public void setupListener() {

    }

    public void setOwner(JTable owner) {
        this.owner = owner;
    }

    @Override
    public void initializeData() {
        tableRowHeightController.install(owner);
        owner.setModel(this.model);
        initFunctions(sumFunctions);
        CommonTableCellRenderer defaultCellRenderer = new CommonTableCellRenderer();
        defaultCellRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        owner.getTableHeader().setDefaultRenderer(defaultCellRenderer);
        owner.setDefaultRenderer(Object.class, defaultCellRenderer);
        for (int c = 0; c < owner.getColumnCount(); c++) {
            Class<?> col_class = owner.getColumnClass(c);
            owner.setDefaultEditor(col_class, null);        // remove editor
        }
    }

    private class CommonTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel cellRenderer = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            return cellRenderer;
        }

    }

    private static class SumFunction {

        private final int columnIndex;
        private final JComponent display;

        public SumFunction(int columnIndex, JComponent display) {
            this.columnIndex = columnIndex;
            this.display = display;
        }

        public void sumColumn(CommonTableModel model, int boolColumn) {
            float sum = 0.0f;
            for (int i = 0; i < model.getRowCount(); i++) {
                try {
                    if (Boolean.valueOf(model.getValueAt(i, boolColumn).toString())) {
                        float value = Float.parseFloat(model.getValueAt(i, columnIndex).toString());
                        sum = Float.sum(sum, value);
                    }
                } catch (NumberFormatException e) {
                    getLogger(CommonTableModel.class.getName()).log(Level.WARNING,
                            "Error hits when parsing table data for sum column due to :\n{0}",
                            e.getMessage());
                }
            }

            switch (display.getClass().getSimpleName()) {
                case "JTextField":
                case "JXTextField":
                    ((JTextField) display).setText(String.valueOf(sum));
                    break;
                case "JLabel":
                case "JXLabel":
                    ((JLabel) display).setText(String.valueOf(sum));
                    break;
                default:
                    getLogger(CommonTableModel.class.getName()).log(Level.WARNING,
                            "Invalid display type [{0}] is designated to sum function for Table",
                            display.getClass().getSimpleName());
            }
        }
    }

    static class CommonTableModel extends DefaultTableModel {

        private int boolColumnId = -1;

        public CommonTableModel(List<String> columnNames) {
            super(columnNames.toArray(), DEFAULT_TABLE_ROW_COUNT);
        }

        void init(List<SumFunction> sumFunctions) {
            addTableModelListener((TableModelEvent e) -> {
//                if (e.getType() != TableModelEvent.UPDATE) {
//                    return;
//                }
                int firstRow = e.getFirstRow();
                if (firstRow == TableModelEvent.HEADER_ROW) {
                    return;
                }

                if (boolColumnId != -1) {
                    for (SumFunction sumFunction : sumFunctions) {
                        sumFunction.sumColumn(this, boolColumnId);
                    }
                }
            });
        }

        public void setBoolColumnId(int boolColumnId) {
            this.boolColumnId = boolColumnId;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (this.boolColumnId == columnIndex) {
                return Boolean.class;
            }
            return super.getColumnClass(columnIndex);
        }

    }
}
