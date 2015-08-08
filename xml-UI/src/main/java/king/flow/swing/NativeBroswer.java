/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.swing;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

/**
 *
 * @author Administrator
 */
public class NativeBroswer extends JPanel {

    public NativeBroswer() {
        super(new BorderLayout());
        NativeInterface.open();
        loadListener();
//        NativeInterface.runEventPump();
    }

    private void loadListener() {
        addAncestorListener(new AncestorListener() {

            @Override
            public void ancestorAdded(AncestorEvent event) {
                JWebBrowser browser = new JWebBrowser();
//                JWebBrowser browser = new JWebBrowser(JWebBrowser.destroyOnFinalization());
                add(browser, BorderLayout.CENTER);
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
                Component[] components = getComponents();
                for (Component component : components) {
                    if (component instanceof JWebBrowser) {
                        JWebBrowser browser = (JWebBrowser) component;
                        removeAll();
                        browser.disposeNativePeer();
                        break;
                    }
                }
            }
        });
    }
}
