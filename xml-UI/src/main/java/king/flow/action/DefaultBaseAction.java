/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import java.util.logging.Level;
import javax.swing.JComponent;
import static king.flow.common.CommonUtil.getLogger;
import king.flow.view.Component;

/**
 *
 * @author LiuJin
 */
public class DefaultBaseAction extends DefaultAction<JComponent> {

    @Override
    public void setupListener() {
        Component meta = null;
        Object obj = getBlockMeta(id);
        if (obj instanceof Component) {
            meta = (Component) obj;
            switch (meta.getType()) {
                case BUTTON:
                    installButtonAction();
                    break;
                case TEXT_FIELD:
                case PASSWORD_FIELD:
                    installTextFieldAction();
                    break;
                case COMBO_BOX:
                    installComboboxAction();
                    break;
                case WEB_BROSWER:
                    installWebBroswerAction();
                    break;
                case VIDEO_PLAYER:
                    installVideoAction();
                    break;
                case LABEL:
                    installClockAction();
                    break;
                default:
                    getLogger(DefaultBaseAction.class.getName()).log(Level.INFO,
                            "Unsupported action component type/id : {0}/{1} ", new Object[]{meta.getType(), meta.getId()});
            }
        } else {
            getLogger(DefaultBaseAction.class.getName()).log(Level.INFO,
                    "Unsupported object  type intalling listener: {0}", obj);
        }
    }

    protected void installButtonAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void installTextFieldAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void installComboboxAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected void installClockAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void installWebBroswerAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected void installVideoAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
