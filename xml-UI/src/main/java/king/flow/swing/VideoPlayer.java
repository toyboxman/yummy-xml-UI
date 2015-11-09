/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.swing;

import java.awt.BorderLayout;
import java.io.File;
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
        initComponents();
    }

    private void initComponents() {
        fxContainer = new JFXPanel();
        this.add(fxContainer, BorderLayout.CENTER);
        // create JavaFX scene
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                createScene();
            }
        });
    }

    private void createScene() {
        StackPane root = new StackPane();
        final Scene scene = new Scene(root);
        final File f = new File("./media/video/NSX-Introduction.mp4");
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
