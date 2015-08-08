/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.test.swingx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.SwingUtilities;
import org.jdesktop.swingx.JXBusyLabel;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTextField;
import org.jdesktop.swingx.icon.EmptyIcon;
import org.jdesktop.swingx.painter.BusyPainter;
import org.jdesktop.swingx.prompt.BuddySupport;

/**
 *
 * @author Administrator
 */
public class TestJxframe {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JXFrame jxFrame = new JXFrame("Test JXFrame", true);
            jxFrame.setPreferredSize(new Dimension(300, 200));
            jxFrame.setStartPosition(JXFrame.StartPosition.CenterInScreen);
            JXBusyLabel label = new JXBusyLabel();
            label.setBusy(true);
            JXTextField textField = new JXTextField("<html><h1>test prompt</html>");
            textField.addBuddy(new JXButton("buddy"), BuddySupport.Position.LEFT);
            final JXPanel jPanel = new JXPanel(new BorderLayout());
            jPanel.add(label, BorderLayout.NORTH);
            jPanel.add(textField, BorderLayout.CENTER);
            jxFrame.getRootPaneExt().setContentPane(jPanel);
            jxFrame.pack();
            jxFrame.setVisible(true);
        });
    }
}
