package king.flow.action;

import java.awt.Component;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static king.flow.common.CommonConstants.DEFAULT_TABLE_ROW_COUNT;

/**
 *
 * @author LiuJin
 */
public class DefaultTableAction extends DefaultAction<JTable> {

    private DefaultTableModel model = null;

    public DefaultTableAction(List<String> columnNames) {
        this.model = new DefaultTableModel(columnNames.toArray(), DEFAULT_TABLE_ROW_COUNT);
    }

    @Override
    public void setupListener() {

    }

    public void setOwner(JTable owner) {
        this.owner = owner;
    }

    @Override
    public void initializeData() {
        owner.setModel(this.model);
        CommonTableCellRenderer defaultCellRenderer = new CommonTableCellRenderer();
        defaultCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
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
}
