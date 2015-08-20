package king.flow.action;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import king.flow.common.CommonConstants;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.setFont;
import king.flow.view.ComponentEnum;
import king.flow.view.Rules;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author LiuJin
 * @param <O>
 */
public abstract class DefaultAction<O extends JComponent> extends BaseAction {

    protected O owner = null;
    protected Rules rules;

    @Override
    public void holdComponents(int owner_id, Map<Integer, Object> components, Map<Integer, Object> components_meta) {
        super.holdComponents(owner_id, components, components_meta);
        this.owner = (O) this.components.get(this.id);
    }

    @Override
    public void initializeData() {

    }

    /**
     * http://www.weste.net/2013/2-20/89142.html
     * <br>Windows system chinese font types :
     * <br>PMingLiU
     * <br>MingLiU
     * <br>DFKai-SB
     * <br>SimHei
     * <br>SimSun
     * <br>NSimSun
     * <br>FangSong
     * <br>KaiTi
     * <br>FangSong_GB2312
     * <br>KaiTi_GB2312
     * <br>Microsoft JhengHei
     * <br>Microsoft YaHei
     *
     * @param <T extends JComponent>
     * @param font
     * @param component
     */
    protected <T extends JComponent> void setSpecialFont(king.flow.view.Font font, T component) {
        if (font != null) {
            setFont(font, component);
        }
    }

    @Override
    protected void panelJump(int next_id) {
        JPanel current_panel = (JPanel) this.owner.getParent();
        JPanel next_panel = getBlock(next_id, JPanel.class);
        if (current_panel == next_panel) {
            return;
        }
        super.panelJump(next_id);
    }

    protected void setCursorBusy(boolean busy, java.awt.Container container) {
        if (container == null) {
            if (busy) {
                owner.getTopLevelAncestor().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            } else {
                owner.getTopLevelAncestor().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        } else {
            if (busy) {
                container.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            } else {
                container.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    public <T extends SwingWorker> void waitCommunicationTask(T task) {
        if (task != null) {
            final JDialog dialog = buildProgressDialog();
            setCursorBusy(true, dialog);
            task.addPropertyChangeListener(new ProgressMonitor(dialog));
            task.execute();
            dialog.setVisible(true);
        }
    }

    public <T extends SwingWorker> void waitCommunicationTask(T task, JDialog animation) {
        if (task != null) {
            if (animation != null) {
                setCursorBusy(true, animation);
                task.addPropertyChangeListener(new ProgressMonitor(animation));
                task.execute();
                animation.setVisible(true);
            } else {
                waitCommunicationTask(task);
            }
        }
    }

    private JDialog buildProgressDialog() {
//        JProgressBar progress = new JProgressBar();
//        progress.setIndeterminate(true);
//        progress.setString("60s");
//        progress.setStringPainted(true);
//        progress.setPreferredSize(new Dimension(400, 50));
        JDialog dialog = new JDialog((Window) owner.getTopLevelAncestor());
        dialog.setModal(true);
//        dialog.getContentPane().add(progress, BorderLayout.CENTER);
//        JLabel backgroud = new JLabel(UIManager.getIcon(CommonConstants.KING_FLOW_BACKGROUND),
//                SwingConstants.LEADING);
        Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        Dimension dim = new Dimension(252, 256);
        int lx = (int) (centerPoint.getX() - dim.getWidth() / 2);
        int ly = (int) (centerPoint.getY() - dim.getHeight() / 2);
        final Rectangle progressBounds = new Rectangle(lx, ly, (int) dim.getWidth(), (int) dim.getHeight());
        dialog.setBounds(progressBounds);
//        backgroud.setBounds(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
//        backgroud.setBorder(new LineBorder(Color.red));
//        dialog.getContentPane().add(backgroud, -1);
        JLabel progressAnimation = new JLabel(UIManager.getIcon(CommonConstants.KING_FLOW_PROGRESS));
        Dimension ani_dim = new Dimension(dim);
        progressAnimation.setBounds(0, 0, (int) ani_dim.getWidth(), (int) ani_dim.getHeight());
//        progressAnimation.setBorder(new LineBorder(Color.black));
        dialog.getContentPane().add(progressAnimation, 0);

        dialog.setUndecorated(true);
        dialog.getRootPane().setOpaque(false);
        final Color transparentColor = new Color(255, 255, 255, 0);
        dialog.getContentPane().setBackground(transparentColor);
        dialog.setBackground(transparentColor);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        return dialog;
    }

    protected JDialog buildAnimationDialog() {
        JDialog dialog = new JDialog((Window) owner.getTopLevelAncestor());
        dialog.setModal(true);
        dialog.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        JLabel progressAnimation = new JLabel(UIManager.getIcon(CommonConstants.KING_FLOW_PROGRESS));
        progressAnimation.setBounds(0, 0, 252, 256);
        dialog.getContentPane().add(progressAnimation, 0);
        dialog.setUndecorated(true);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        return dialog;
    }

    protected long retrieveNumber(int componentID) {
        long value = Long.MIN_VALUE;
        king.flow.view.Component meta = (king.flow.view.Component) getBlockMeta(componentID);
        ComponentEnum type = meta.getType();
        switch (type) {
            case DATE:
                JXDatePicker jdp = getBlock(componentID, JXDatePicker.class);
                Date date = jdp.getDate();
                value = date.getTime();
                break;
            case TEXT_FIELD:
                JTextField jtf = getBlock(componentID, JTextField.class);
                String text = jtf.getText();
                try {
                    value = Long.parseLong(text);
                } catch (NumberFormatException e) {
                    CommonUtil.getLogger(DefaultAction.class.getName()).log(Level.WARNING,
                            "Invalidated textfiled numeric value", e);
                }
                break;
            default:
                CommonUtil.getLogger(DefaultAction.class.getName()).log(Level.INFO,
                        "Unsupported more-less-comparing component type : {0}", type);
        }
        return value;
    }

    protected String retrieveString(int componentID) {
        String value = null;
        king.flow.view.Component meta = (king.flow.view.Component) getBlockMeta(componentID);
        ComponentEnum type = meta.getType();
        switch (type) {
            case TEXT_FIELD:
                JTextField jtf = getBlock(componentID, JTextField.class);
                value = jtf.getText();
                break;
            case PASSWORD_FIELD:
                JPasswordField jpf = getBlock(componentID, JPasswordField.class);
                value = new String(jpf.getPassword());
                break;
            case COMBO_BOX:
                JComboBox jcb = getBlock(componentID, JComboBox.class);
                if (jcb.isEditable()) {
                    value = jcb.getEditor().getItem().toString();
                } else {
                    value = jcb.getSelectedItem() == null ? null : jcb.getSelectedItem().toString();
                }
                break;
            default:
                CommonUtil.getLogger(DefaultAction.class.getName()).log(Level.INFO,
                        "Unsupported equality-checking component type : {0}", type);
        }
        return value;
    }

    protected boolean checkCJK() {
        List<Rules.ValidateCJK> validateCJK = rules.getValidateCJK();
        if (validateCJK != null && validateCJK.size() > 0) {
            for (Rules.ValidateCJK cjk : validateCJK) {
                int content = cjk.getContent();
                String value = retrieveString(content);
                if (value == null || value.length() == 0) {
                    CommonUtil.showMsg(owner.getTopLevelAncestor(), cjk.getErrMsg());
                    return false;
                }
                for (int i = 0; i < value.length(); i++) {
                    if (!Character.isIdeographic(value.codePointAt(i))) {
                        CommonUtil.showMsg(owner.getTopLevelAncestor(), cjk.getErrMsg());
                        return false;
                    }
                }
            }
        }
        return true;
    }

    protected boolean checkEqualConditions() {
        List<Rules.Equal> equalConditions = rules.getEqual();
        if (equalConditions != null && equalConditions.size() > 0) {
            for (Rules.Equal condition : equalConditions) {
                String c = condition.getConditions();
                ArrayList<String> parameters = CommonUtil.buildListParameters(c);
                if (parameters != null && parameters.size() > 1) {
                    HashSet<String> set = new HashSet<>();
                    for (String componentID : parameters) {
                        try {
                            int cid = Integer.parseInt(componentID);
                            String value = retrieveString(cid);
                            set.add(value);
                        } catch (NumberFormatException e) {
                            CommonUtil.getLogger(DefaultAction.class.getName()).log(Level.WARNING,
                                    "Invalidated component id", e);
                            break;
                        }
                    }
                    if (set.size() != 1) {
                        CommonUtil.showMsg(owner.getTopLevelAncestor(), condition.getErrMsg());
                        return false;
                    }
                } else {
                    CommonUtil.getLogger(DefaultAction.class.getName()).log(Level.WARNING,
                            "equal conditions is null or only 1 compared object as : {0}", condition.getConditions());
                }
            }
        }
        return true;
    }

    protected boolean checkNotEqualConditions() {
        List<Rules.NotEqual> notEqualConditions = rules.getNotEqual();
        if (notEqualConditions != null && notEqualConditions.size() > 0) {
            for (Rules.NotEqual condition : notEqualConditions) {
                long more = retrieveNumber(condition.getMore());
                long less = retrieveNumber(condition.getLess());
                if (more < less) {
                    CommonUtil.showMsg(owner.getTopLevelAncestor(), condition.getErrMsg());
                    return false;
                }
            }
        }
        return true;
    }

    protected boolean checkNotNull() {
        List<Rules.NotNull> notNull = rules.getNotNull();
        if (notNull != null && notNull.size() > 0) {
            for (Rules.NotNull n : notNull) {
                int content = n.getContent();
                String value = retrieveString(content);
                if (value == null || value.length() == 0) {
                    CommonUtil.showMsg(owner.getTopLevelAncestor(), n.getErrMsg());
                    return false;
                }
            }
        }
        return true;
    }

    protected boolean checkTemplate() {
        List<Rules.Template> template = rules.getTemplate();
        if (template != null && template.size() > 0) {
            for (Rules.Template t : template) {
                int content = t.getContent();
                String pattern = t.getPattern();
                String value = retrieveString(content);
                if (!value.startsWith(pattern)) {
                    CommonUtil.getLogger(DefaultAction.class.getName()).info(t.getErrMsg() + " value " + value + "/ pattern " + pattern);
                    CommonUtil.showMsg(owner.getTopLevelAncestor(), t.getErrMsg());
                    return false;
                }
            }
        }
        return true;
    }

    protected class ProgressMonitor implements PropertyChangeListener {

        private JDialog jDialog;

        public ProgressMonitor(JDialog jDialog) {
            super();
            this.jDialog = jDialog;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("state") && SwingWorker.StateValue.DONE == evt.getNewValue()) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        jDialog.setVisible(false);
                        jDialog.dispose();
                    }
                });
            }
//            if (evt.getPropertyName().equals("111")) {
//                final int next = Integer.parseInt(evt.getNewValue().toString());
//                 jDialog.setVisible(false);
//                 jDialog.dispose();
//                SwingUtilities.invokeLater(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        panelJump(next);
//                    }
//                });
//            }
        }
    }
}
