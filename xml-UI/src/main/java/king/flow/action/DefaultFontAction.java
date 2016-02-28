/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import java.awt.Color;
import javax.swing.JComponent;
import king.flow.view.Font;

/**
 *
 * @author LiuJin
 */
public class DefaultFontAction extends DefaultAction<JComponent> {
    
    private Font font = null;
    private Color foregroundColor = null;
    private Color backgroundColor = null;
    
    public DefaultFontAction() {
    }
    
    public DefaultFontAction(Font newFont) {
        this.font = newFont;
    }
    
    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }
    
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    @Override
    public void setupListener() {
        
    }
    
    @Override
    public void initializeData() {
        super.initializeData();
        if (font != null) {
            setSpecialFont(font, owner);
        }
        if (foregroundColor != null) {
            owner.setForeground(foregroundColor);
        }
        if (backgroundColor != null) {
            owner.setBackground(backgroundColor);
        }
    }
}
