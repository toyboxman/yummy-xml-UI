/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import javax.swing.JComponent;
import king.flow.common.CommonUtil;
import king.flow.swing.JXGridPanel;
import king.flow.view.Action;

/**
 *
 * @author liujin
 */
public class DefaultGridAction extends DefaultAction<JComponent> {
    
    private final int row, column, hgap, vgap;
    private final Action.ShowGridAction.Misc misc;
    
    public DefaultGridAction(Action.ShowGridAction showGridAction) {
        this.row = showGridAction.getRow();
        this.column = showGridAction.getColumn();
        this.hgap = showGridAction.getHgap();
        this.vgap = showGridAction.getVgap();
        this.misc = showGridAction.getMisc();
    }
    
    @Override
    public void initializeData() {
        JXGridPanel grid = (JXGridPanel) owner;
        grid.setGridLayout(row, column, hgap, vgap);
        if (this.misc != null && this.misc.getBgColor() != null) {
            String bgColor = this.misc.getBgColor();
            grid.setGridElementBgColor(CommonUtil.getTrueColor(bgColor));
        }
    }
    
    @Override
    public void setupListener() {
    }
    
}
