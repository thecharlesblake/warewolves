package uk.co.thecharlesblake.personal.warewolves.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class RootLayoutController {
    @FXML
    Button abc;

    @FXML
    MediaView mediaView;

    @FXML
    public void initialize() {
        Media dayMusicMedia = new Media(this.getClass().getClassLoader()
                .getResource("music/day.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(dayMusicMedia);
        mediaPlayer.setCycleCount(Integer.MAX_VALUE);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    public void onAbc() {
        System.out.println("here!");
    }
}
