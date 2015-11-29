/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.common;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import javax.media.CannotRealizeException;
import javax.media.ControllerAdapter;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import static king.flow.common.CommonUtil.getLogger;

/**
 *
 * @author LiuJin
 */
public class AudioPlayer {

    public static AudioPlayer getAudioPlayer(File media) {
        AudioPlayer player = null;
        try {
            player = new AudioPlayer(media);
        } catch (IOException | NoPlayerException | CannotRealizeException e) {
            getLogger(AudioPlayer.class.getName()).log(Level.WARNING, "fail to create an AudioPlayer due to {0}", e.getClass().getSimpleName());
        }
        return player;
    }

    private Player audioPlayer = null;

    private AudioPlayer(File f) throws MalformedURLException, IOException, NoPlayerException, CannotRealizeException {
        audioPlayer = Manager.createRealizedPlayer(f.toURI().toURL());
    }

    public void play() {
        audioPlayer.addControllerListener(new PlayControllerListener());
        audioPlayer.start();
    }
    
    public void stop() {
      audioPlayer.stop();
      audioPlayer.close();
    }

    private class PlayControllerListener extends ControllerAdapter {

        @Override
        public void endOfMedia(EndOfMediaEvent e) {
            e.getSourceController().stop();
            e.getSourceController().close();
        }
    }

}
