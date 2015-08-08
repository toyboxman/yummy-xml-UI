/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.customization;

import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import king.flow.action.DefaultMenuBaseAction;
import static king.flow.common.CommonConstants.CONTAINER_KEY;
import org.jdesktop.swingx.JXImageView;

/**
 *
 * @author Liujin
 */
public class AboutAction extends DefaultMenuBaseAction<JMenuItem> {

    @Override
    protected void intallActionListener() {
        JMenuItem item = getBlock(id, JMenuItem.class);
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LOGO logo = new LOGO();
                logo.setVisible(true);
            }
        });
    }

    private class LOGO extends JDialog {

        public LOGO() {
            super(getBlock(CONTAINER_KEY, Window.class));
            setModal(true);
            Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
            Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                    .getDefaultConfiguration().getBounds();
            double width = screenBounds.getWidth() * 0.36;
            double height = screenBounds.getHeight() * 0.36;
            int lx = (int) (centerPoint.getX() - width / 2);
            int ly = (int) (centerPoint.getY() - height / 2);
            final Rectangle browserBounds = new Rectangle(lx, ly, (int) width, (int) height);
            setBounds(browserBounds);
            final JXImageView imageView = new JXImageView();
            final List<Image> iconList = getBlock(CONTAINER_KEY, Window.class).getIconImages();
            if (iconList != null && iconList.size() > 0) {
                final Image image = iconList.get(0);
                imageView.setImage(image);
            }
            getContentPane().add(imageView);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

    }
}
