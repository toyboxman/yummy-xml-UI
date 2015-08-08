/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.test.common;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.CannotRealizeException;
import javax.media.ControllerAdapter;
import javax.media.ControllerEvent;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;

/**
 * http://www.ibm.com/developerworks/java/tutorials/j-jmf/j-jmf.html
 * http://www.oracle.com/technetwork/java/javase/tech/index-jsp-140239.html
 *
 * @author LiuJin
 */
public class TestAudioUtil {

    private Player audioPlayer = null;

    public TestAudioUtil(File f) throws MalformedURLException, IOException, NoPlayerException, CannotRealizeException {
        audioPlayer = Manager.createRealizedPlayer(f.toURI().toURL());
    }

    public void play() {
        audioPlayer.start();
    }
    
    public void stop() {
        audioPlayer.stop();
        audioPlayer.close();
    }
    

    public long getMediaTime() {
        Time duration = audioPlayer.getDuration();
        System.out.println(duration.getSeconds());
        Time mediaTime = audioPlayer.getMediaTime();
        System.out.println(mediaTime.getSeconds());
        return audioPlayer.getMediaNanoseconds();
    }

    public static void main(String[] args) {
        try {
            TestAudioUtil player = new TestAudioUtil(new File("./media/song.wav"));
            player.getMediaTime();
            player.audioPlayer.addControllerListener(new ControllerListenerImpl());
            player.play();
            Thread.sleep(2000);
            player.stop();
        } catch (IOException | NoPlayerException | CannotRealizeException ex) {
            Logger.getLogger(TestAudioUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestAudioUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class ControllerListenerImpl extends ControllerAdapter {

        @Override
        public void endOfMedia(EndOfMediaEvent e) {
            e.getSourceController().stop();
            e.getSourceController().close();
        }

//        @Override
//        public void controllerUpdate(ControllerEvent ce) {
//            if (ce.getClass().equals(EndOfMediaEvent.class)) {
//                ce.getSourceController().stop();
//                ce.getSourceController().close();
//            }
////            System.out.println(ce);
//        }
    }
}
