/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

/**
 *
 * @author Administrator
 */
public class DefaultTipAction extends DefaultBaseAction {

    private final String tip;

    public DefaultTipAction(String tip) {
        this.tip = tip;
    }

    @Override
    public void initializeData() {
        owner.setToolTipText(tip);
    }

    @Override
    protected void installTextFieldAction() {

    }

    @Override
    protected void installComboboxAction() {

    }

    @Override
    protected void installButtonAction() {

    }

}
