/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import javax.swing.JMenuItem;

/**
 *
 * @author Administrator
 * @param <T>
 */
public abstract class DefaultMenuBaseAction<T extends JMenuItem> extends BaseAction {

    @Override
    public void initializeData() {

    }

    @Override
    public void setupListener() {
        intallActionListener();
    }

    protected abstract void intallActionListener();

}
