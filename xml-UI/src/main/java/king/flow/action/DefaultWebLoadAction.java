/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static king.flow.common.CommonConstants.CONTAINER_KEY;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.getResourceMsg;
import king.flow.swing.SwingBrowser;

/**
 *
 * @author Administrator
 */
public class DefaultWebLoadAction extends DefaultBaseAction {

    private final String url;
    private FXBrowser webBrowser;

    public DefaultWebLoadAction(String url) {
        this.url = url;
    }

    @Override
    public void holdComponents(int owner_id, Map<Integer, Object> components, Map<Integer, Object> components_meta) {
        super.holdComponents(owner_id, components, components_meta);
        webBrowser = new FXBrowser();
    }

    @Override
    protected void installButtonAction() {
        JButton btn = (JButton) owner;
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        webBrowser.setVisible(true);
                        webBrowser.refreshPage();
                    }
                });
            }
        });
    }

    @Override
    protected void installWebBroswerAction() {
        final SwingBrowser sb = (SwingBrowser) owner;
        sb.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        sb.loadURL(url);
                    }
                });
            }
        });
    }

    private class FXBrowser extends JDialog {

        private final SwingBrowser swingBrowser;

        public FXBrowser() throws HeadlessException {
            super(getBlock(CONTAINER_KEY, Window.class));
            setModal(true);
            swingBrowser = new SwingBrowser();
            getContentPane().add(swingBrowser, BorderLayout.CENTER);
            JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
            final JButton jButton = new JButton(getResourceMsg("button.text.Back"));
            Dimension buttonSize = jButton.getPreferredSize();
            buttonSize.setSize(buttonSize.getWidth() + 30, buttonSize.getHeight());
            jButton.setPreferredSize(buttonSize);
            jButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    FXBrowser.this.setVisible(false);
                }
            });
            jPanel.add(jButton);
            getContentPane().add(jPanel, BorderLayout.SOUTH);
            setDefaultCloseOperation(HIDE_ON_CLOSE);
//            setPreferredSize(new Dimension(1024, 600));
//            pack();
            swingBrowser.loadURL(url);
            Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                    .getDefaultConfiguration().getBounds();
//            Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
//            double width = screenBounds.getWidth() * 0.8;
//            double height = screenBounds.getHeight() * 0.8;
//            int lx = (int) (centerPoint.getX() - width / 2);
//            int ly = (int) (centerPoint.getY() - height / 2);
//            final Rectangle browserBounds = new Rectangle(lx, ly, (int) width, (int) height);
//            setBounds(browserBounds);
            setBounds(screenBounds);
            setUndecorated(true);
        }

        void refreshPage() {
            if (swingBrowser != null) {
                swingBrowser.loadURL(url);
            }
        }
    }
}
