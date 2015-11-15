/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.swing;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.JPanel;

/**
 *
 * @author liujin
 */
public class VideoPlayer extends JPanel {

    private final JFXPanel fxContainer;
    private MediaPlayer mediaPlayer;
    private final Timer videoReplayScheduler;
    private TimerTask replayTask;
    private int replayInterval = 20;

    public VideoPlayer() {
        this.setLayout(new BorderLayout(0, 0));
        fxContainer = new JFXPanel();
        this.add(fxContainer, BorderLayout.CENTER);
        videoReplayScheduler = new Timer("VideoReplayScheduler");
    }

    public void preparePlayer(final String mediaUrl, final int replayInterval) {
        this.replayInterval = replayInterval;
        // create JavaFX scene
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                createScene(mediaUrl);
            }
        });

        try {
            //wait mediaPlayer ready to play
            Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        } catch (InterruptedException ex) {
            Logger.getLogger(VideoPlayer.class.getName()).log(Level.SEVERE, "Wait mediaPlayer ready to play", ex);
        }
    }

    private void createScene(final String mediaUrl) {
        StackPane root = new StackPane();
        final Scene scene = new Scene(root);
        final File f = new File(mediaUrl);
        final Media m = new Media(f.toURI().toString());
        mediaPlayer = new MediaPlayer(m);
        final MediaView mv = new MediaView(mediaPlayer);

        final DoubleProperty width = mv.fitWidthProperty();
        final DoubleProperty height = mv.fitHeightProperty();

        width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
        mv.setPreserveRatio(true);
//        mediaPlayer.setAutoPlay(true);
//        mediaPlayer.setCycleCount(Integer.MAX_VALUE);
        mediaPlayer.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                replay();
            }
        });
        root.getChildren().add(mv);
        fxContainer.setScene(scene);
    }

    public void cancelPendingReplay() {
        if (replayTask != null) {
            replayTask.cancel();
            int purge = videoReplayScheduler.purge();
            Logger.getLogger(VideoPlayer.class.getName()).log(Level.INFO, "Forcedly cancel {0} pending replay task created on {1}",
                    new Object[]{purge, replayTask.toString()});
            replayTask = null;
        } else {
            Logger.getLogger(VideoPlayer.class.getName()).log(Level.INFO, "No pending replay task needs to be cancelled");
        }
    }

    private void replay() {
        cancelPendingReplay();
        Logger.getLogger(VideoPlayer.class.getName()).log(Level.INFO, "Start a replay task and wait a interval {0} seconds to start again", replayInterval);
        pause();
        replayTask = new ReplayTask();
        //replay interval
        videoReplayScheduler.schedule(replayTask, TimeUnit.SECONDS.toMillis(replayInterval));
    }

    public void play() {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                mediaPlayer.play();
            }
        });
    }

    public void pause() {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                mediaPlayer.stop();
            }
        });
    }

    private class ReplayTask extends TimerTask {

        private final long scheduledTimePoint;

        public ReplayTask() {
            this.scheduledTimePoint = System.currentTimeMillis();
        }

        @Override
        public void run() {
            if (VideoPlayer.this.isShowing()) {
                play();
                replayTask = null;
                Logger.getLogger(VideoPlayer.class.getName()).log(Level.INFO, "Finish a replay task and replaying now");
            } else {
                Logger.getLogger(VideoPlayer.class.getName()).log(Level.INFO, "Cancel a replay task, because current page is not showing");
            }
        }

        @Override
        public String toString() {
            return "ReplayTask scheduled on " + String.format("%1$tH:%1$tM:%1$tS:%1$tL", this.scheduledTimePoint);
        }

    }
}
