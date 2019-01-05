package uk.co.thecharlesblake.personal.warewolves.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import uk.co.thecharlesblake.personal.warewolves.model.Model;

import static uk.co.thecharlesblake.personal.warewolves.model.Model.Time.DAY;
import static uk.co.thecharlesblake.personal.warewolves.model.Model.Time.NIGHT;

public class RootLayoutController {

    @FXML
    Button timeButton;
    @FXML
    MediaView mediaView;
    @FXML
    BorderPane backgroundBorderPane;

    private final Model model = new Model();
    private LIFXController lifxController;

    private MediaPlayer dayMediaPlayer;
    private MediaPlayer nightMediaPlayer;

    private static final String dayMusicFilename = "day.mp3";
    private static final String nightMusicFilename = "night.mp3";


    public void setup() {
        lifxController = new LIFXController();
        setupMusic();
        beginDay();
    }

    private void setupMusic() {
        dayMediaPlayer = setupMediaPlayer(dayMusicFilename);
        nightMediaPlayer = setupMediaPlayer(nightMusicFilename);
    }

    private MediaPlayer setupMediaPlayer(String filename) {
        Media dayMusicMedia = new Media(this.getClass().getClassLoader()
                .getResource("music/" + filename).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(dayMusicMedia);
        mediaPlayer.setCycleCount(Integer.MAX_VALUE);
        return mediaPlayer;
    }

    public void onTimeButtonAction() {
        switch (model.time) {
            case DAY:
                endDay();
                beginNight();
                break;
            case NIGHT:
                endNight();
                beginDay();
                break;
        }
    }

    private void endDay() {
        stop(dayMediaPlayer);
    }

    private void endNight() {
        stop(nightMediaPlayer);
    }

    private void beginDay() {
        lifxController.setDay();
        play(dayMediaPlayer);
        backgroundBorderPane.setBackground(new Background(new BackgroundFill(
                Color.PAPAYAWHIP, null, null)));
        timeButton.setText("To night");
        model.time = DAY;
    }

    private void beginNight() {
        lifxController.setNight();
        play(nightMediaPlayer);
        backgroundBorderPane.setBackground(new Background(new BackgroundFill(
                Color.BLACK, null, null)));
        timeButton.setText("To day");
        model.time = NIGHT;
    }

    private void stop(MediaPlayer mediaPlayer) {
        mediaPlayer.stop();
    }

    private void play(MediaPlayer mediaPlayer) {
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }
}
