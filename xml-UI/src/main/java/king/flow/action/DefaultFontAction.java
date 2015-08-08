/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package king.flow.action;

import javax.swing.JComponent;
import king.flow.view.Font;

/**
 *
 * @author LiuJin
 */
public class DefaultFontAction extends DefaultAction<JComponent>{
    
    private Font font = null;

    public DefaultFontAction(Font newFont) {
        this.font = newFont;
    }

    @Override
    public void setupListener() {
        
    }

    @Override
    public void initializeData() {
        super.initializeData(); 
        setSpecialFont(font, owner);
    }
}
