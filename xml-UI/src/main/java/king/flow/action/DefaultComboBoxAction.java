package king.flow.action;

import java.awt.Component;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import king.flow.common.CommonUtil;
import king.flow.lookandfeel.BankComboBoxPainter;

/**
 *
 * @author Administrator
 */
public class DefaultComboBoxAction extends DefaultAction<JComboBox> {

    private DefaultComboBoxModel model = null;
    private Map<String, String> kv = null;

    public DefaultComboBoxAction(List<String> items) {
        this.model = new DefaultComboBoxModel();
        this.kv = new HashMap<>();
        for (String v : items) {
            if (v.equals("/")) {
                this.model.addElement("");
            } else {
                String[] pv = v.split("/");
                kv.put(pv[0], pv[1]);
                this.model.addElement(pv[0]);
            }
        }
    }

    @Override
    public void setupListener() {
    }

    @Override
    public void initializeData() {
        owner.setModel(this.model);
        owner.setRenderer(new CommonListCellRenderer(this.kv));
        /**
         * change one component ui configuration
         * http://docs.oracle.com/javase/7/docs/api/javax/swing/plaf/nimbus/package-summary.html
         * http://www.jasperpotts.com/blog/2008/08/skinning-a-slider-with-nimbus/
         *
         */
//        UIDefaults ui_conf = new UIDefaults();
//        owner.putClientProperty("Nimbus.Overrides", ui_conf);
////        owner.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
//        try {
//            String key = "ComboBox[Enabled].backgroundPainter";
//            Object painter = UIManager.get(key);
//            CommonUtil.getLogger(DefaultComboBoxAction.class.getName()).log(Level.INFO,
//                    "painter is {0}", painter.getClass().getName());
//            Field ctx = painter.getClass().getDeclaredField("ctx");
//            ctx.setAccessible(true);
//            Object v = ctx.get(painter);
//            BankComboBoxPainter p
//                    = new BankComboBoxPainter(v, BankComboBoxPainter.BACKGROUND_ENABLED);
////            UIManager.put(key, p);
//            ui_conf.put(key, p);
//        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
//            Logger.getLogger(DefaultComboBoxAction.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private class CommonListCellRenderer extends DefaultListCellRenderer {

        Map<String, String> kv = null;

        public CommonListCellRenderer(Map<String, String> map) {
            this.kv = map;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            JLabel cell = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (cell.getText().length() > 0) {
                cell.setText(this.kv.get(cell.getText()));
            }
//            cell.setBorder(new LineBorder(Color.red, 2));
            return cell;
        }
    }
}
