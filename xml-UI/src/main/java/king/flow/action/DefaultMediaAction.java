/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.util.logging.Level;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import king.flow.common.AudioPlayer;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.AudioPlayer.getAudioPlayer;

/**
 * http://www.ibm.com/developerworks/java/tutorials/j-jmf/j-jmf.html
 * http://www.oracle.com/technetwork/java/javase/tech/index-jsp-140239.html
 *
 * @author LiuJin
 */
public class DefaultMediaAction extends DefaultBaseAction {

    private final String mediaFile;

    public DefaultMediaAction(String media) {
        this.mediaFile = media;
    }

    @Override
    protected void installComboboxAction() {
        JComboBox box = (JComboBox) owner;
        if (box.isEditable()) {
            Component editor = box.getEditor().getEditorComponent();
            editor.addFocusListener(new FocusMediaAction());
        } else {
            box.addFocusListener(new FocusMediaAction());
        }
    }

    @Override
    protected void installButtonAction() {
        JButton btn = (JButton) owner;
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                play();
            }
        });
    }

    @Override
    protected void installTextFieldAction() {
        JTextField textField = (JTextField) owner;
        textField.addFocusListener(new FocusMediaAction());
    }

    private PlayMediaTask play() {
        final PlayMediaTask playMediaTask = new PlayMediaTask();
        playMediaTask.execute();
        return playMediaTask;
    }

    private class FocusMediaAction extends FocusAdapter {

        PlayMediaTask player;

        @Override
        public void focusGained(FocusEvent e) {
            player = play();
        }

        @Override
        public void focusLost(FocusEvent e) {
            player.stop();
        }

    }

    private class PlayMediaTask extends SwingWorker<Integer, Object> {

        AudioPlayer audioPlayer;

        @Override
        protected Integer doInBackground() throws Exception {
            audioPlayer = getAudioPlayer(new File(mediaFile));
            if (audioPlayer != null) {
                audioPlayer.play();
            } else {
                getLogger(PlayMediaTask.class.getName()).log(Level.WARNING, "Fail to play media file {0}", mediaFile);
            }

            return 0;
        }

        public void stop() {
            if (audioPlayer != null) {
                audioPlayer.stop();
            }
        }

    }
}
