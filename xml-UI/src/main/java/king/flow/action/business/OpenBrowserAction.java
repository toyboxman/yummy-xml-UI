/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.business;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.DefaultWebBrowserDecorator;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserDecorator;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import king.flow.action.DefaultBaseAction;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.getInputMethodActiveCmd;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.getResourceMsg;

/**
 *
 * @author Administrator
 */
public class OpenBrowserAction extends DefaultBaseAction {

    private final String url;
    private static final NativeBrowser nativeBrowser;

    static {
        NativeInterface.open();
        nativeBrowser = new NativeBrowser();
    }

    public OpenBrowserAction(String url) {
        this.url = url;
    }

    @Override
    protected void installButtonAction() {
        components_meta.values();
        JButton btn = (JButton) owner;
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new LoadPageTask().execute();
            }
        });
    }

    class LoadPageTask extends SwingWorker<Integer, Object> {

        @Override
        protected Integer doInBackground() throws Exception {
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    if (nativeBrowser.isVisible()) {
                        nativeBrowser.navigateURL(url);
                        nativeBrowser.toFront();
                    } else {
                        nativeBrowser.setVisible(true);
                        nativeBrowser.navigateURL(url);
                    }
                }
            });
            return 1;
        }

    }

    private static class NativeBrowser extends JFrame {

        private final JWebBrowser swingBrowser;
        private volatile String homePage;

        public NativeBrowser() throws HeadlessException {
            super();
            swingBrowser = new JWebBrowser() {
                @Override
                protected WebBrowserDecorator createWebBrowserDecorator(Component renderingComponent) {
                    return createCustomWebBrowserDecorator(this, renderingComponent);
                }
            };
            swingBrowser.setLocationBarVisible(false);
            swingBrowser.setMenuBarVisible(false);
            swingBrowser.setBarsVisible(false);
            swingBrowser.setStatusBarVisible(false);
            swingBrowser.addWebBrowserListener(new WebBrowserAction());
            
            getContentPane().add(swingBrowser, BorderLayout.CENTER);
            JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
            JButton exitButton = createButton(getResourceMsg("button.text.Exit"));
            exitButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    navigateURL(homePage);
                    setVisible(false);
                    CommonUtil.getCurrentView().toFront();
                }
            });
            JButton homeButton = createButton(getResourceMsg("button.text.Home"));
            homeButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    navigateURL(homePage);
                }
            });
            JButton backButton = createButton(getResourceMsg("button.text.Back"));
            backButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    swingBrowser.navigateBack();
                }
            });
            jPanel.add(exitButton);
            jPanel.add(homeButton);
            jPanel.add(backButton);
            JButton inputButton = createButton(getResourceMsg("button.text.InputMethod"));
            inputButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    new VirtualKeyBoardTask().execute();
                }
            });
            jPanel.add(inputButton);

            getContentPane().add(jPanel, BorderLayout.NORTH);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                    .getDefaultConfiguration().getBounds();
            setBounds(screenBounds);
            setUndecorated(true);
        }

        JButton createButton(String text) {
            final JButton exitButton = new JButton(text);
            Dimension buttonSize = exitButton.getPreferredSize();
            buttonSize.setSize(buttonSize.getWidth() + 30, buttonSize.getHeight());
            exitButton.setPreferredSize(buttonSize);
            return exitButton;
        }

        public boolean navigateURL(String url) {
            homePage = url;
            return swingBrowser.navigate(url);
        }

        private WebBrowserDecorator createCustomWebBrowserDecorator(JWebBrowser webBrowser, Component renderingComponent) {
            // Let's extend the default decorator.
            // We could rewrite our own decorator, but this is far more complex and we generally do not need this.
            return new DefaultWebBrowserDecorator(webBrowser, renderingComponent) {
                @Override
                protected void addMenuBarComponents(DefaultWebBrowserDecorator.WebBrowserMenuBar menuBar) {
                    // We let the default menus to be added and then we add ours.
                    super.addMenuBarComponents(menuBar);
                    JMenu myMenu = new JMenu("[[My Custom Menu]]");
                    myMenu.add(new JMenuItem("My Custom Item 1"));
                    myMenu.add(new JMenuItem("My Custom Item 2"));
                    menuBar.add(myMenu);
                }

                @Override
                protected void addButtonBarComponents(DefaultWebBrowserDecorator.WebBrowserButtonBar buttonBar) {
                    // We completely override this method so we decide which buttons to add
                    buttonBar.add(buttonBar.getBackButton());
                    final JButton button = new JButton("[[My Custom Button!]]");
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(button, "My Custom Button was pressed!");
                        }
                    });
                    buttonBar.add(button);
                    buttonBar.add(buttonBar.getForwardButton());
                    buttonBar.add(buttonBar.getReloadButton());
                    buttonBar.add(buttonBar.getStopButton());
                }
            };
        }

        class VirtualKeyBoardTask extends SwingWorker<Integer, Object> {

            @Override
            protected Integer doInBackground() throws Exception {
//                JOptionPane.showMessageDialog(null, getInputMethodActiveCmd());
                Process proc = Runtime.getRuntime().exec(getInputMethodActiveCmd());
                Thread.sleep(1000 * 1);
                proc.destroy();
                return 1;
            }

        }

        private class WebBrowserAction extends WebBrowserAdapter {

            @Override
            public void windowWillOpen(WebBrowserWindowWillOpenEvent e) {
                final JWebBrowser windowNew = e.getNewWebBrowser();
                windowNew.addWebBrowserListener(new WebBrowserAdapter() {

                    @Override
                    public void locationChanging(WebBrowserNavigationEvent e) {
                        String nextPage = e.getNewResourceLocation();
                        getLogger(WebBrowserAction.class.getName()).log(Level.INFO, "next page {0} will be opened soon ", nextPage);
                        swingBrowser.navigate(nextPage);
                        SwingUtilities.invokeLater(new Runnable() {

                            @Override
                            public void run() {
                                windowNew.getWebBrowserWindow().dispose();
                            }
                        });
                    }
                });
            }
        }
    }
}
