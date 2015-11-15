/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.swing;

import java.awt.BorderLayout;
import java.io.File;
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

    private JFXPanel fxContainer;
    private MediaPlayer mediaPlayer;

    public VideoPlayer() {
        this.setLayout(new BorderLayout(0, 0));
        fxContainer = new JFXPanel();
        this.add(fxContainer, BorderLayout.CENTER);
    }

    public void preparePlayer(final String mediaUrl) {
        // create JavaFX scene
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                createScene(mediaUrl);
            }
        });
        
        try {
            //wait mediaPlayer ready to play
            Thread.sleep(2000);
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
        root.getChildren().add(mv);
        fxContainer.setScene(scene);
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
}
