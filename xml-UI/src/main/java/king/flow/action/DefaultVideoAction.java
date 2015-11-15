/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import king.flow.common.CommonUtil;
import king.flow.swing.VideoPlayer;
import king.flow.view.Bound;
import king.flow.view.Component;

/**
 *
 * @author liujin
 */
public class DefaultVideoAction extends DefaultBaseAction {

    private final String mediaURL;
    private final int showingPage;
    private JDialog dialog;

    public DefaultVideoAction(String mediaURL, int showingPage) {
        this.mediaURL = mediaURL;
        this.showingPage = showingPage;
        dialog = new JDialog(CommonUtil.getCurrentView());
        dialog.setUndecorated(true);
        dialog.getContentPane().setLayout(new BorderLayout(0, 0));
    }

    @Override
    protected void installVideoAction() {
        VideoPlayer videoPlayer = (VideoPlayer) owner;
        videoPlayer.preparePlayer(mediaURL);
        dialog.getContentPane().add(videoPlayer, BorderLayout.CENTER);
        Component component = (Component) this.components_meta.get(id);
        Bound rect = component.getAttribute().getRect();
        dialog.setBounds(rect.getX().intValue(), rect.getY().intValue(), rect.getWidth().intValue(), rect.getHeigh().intValue());
        JPanel parent = (JPanel) this.components.get(this.showingPage);
        parent.addAncestorListener(new AncestorListener() {

            @Override
            public void ancestorAdded(AncestorEvent event) {
                dialog.setVisible(true);
                videoPlayer.play();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
                dialog.setVisible(false);
                videoPlayer.pause();
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
        });
    }

}
