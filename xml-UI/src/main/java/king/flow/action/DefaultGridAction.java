/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import javax.swing.JComponent;
import king.flow.swing.JXGridPanel;
import king.flow.view.Action;

/**
 *
 * @author liujin
 */
public class DefaultGridAction extends DefaultAction<JComponent> {

    private final int row, column, hgap, vgap;

    public DefaultGridAction(Action.ShowGridAction showGridAction) {
        this.row = showGridAction.getRow();
        this.column = showGridAction.getColumn();
        this.hgap = showGridAction.getHgap();
        this.vgap = showGridAction.getVgap();
    }

    @Override
    public void initializeData() {
        JXGridPanel grid = (JXGridPanel) owner;
        grid.setGridLayout(row, column, hgap, vgap);
    }

    @Override
    public void setupListener() {
    }

}
