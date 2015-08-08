/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.lookandfeel;

import javax.swing.UIDefaults;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author LiuJin
 */
public class BankLookAndFeel extends NimbusLookAndFeel {

    @Override
    public boolean getSupportsWindowDecorations() {
        return true;
    }

    @Override
    public UIDefaults getDefaults() {
        UIDefaults defaults = super.getDefaults();
        defaults.put("defaultFont", new FontUIResource("SansSerif", java.awt.Font.BOLD, 22));
        return defaults;
    }

}
