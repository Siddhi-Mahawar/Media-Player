package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;


import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private MediaPlayer mediaPlayer;
    private String filepath;

    private Duration duration1;


    @FXML
    private Slider seeslider;


    @FXML
    private Slider slider;

    @FXML
    private MediaView mediaView;


    @FXML
    private void handleButtonAction(ActionEvent event){

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("SELECT A FILE (*.mp4)","*.mp4");
        fileChooser.getExtensionFilters().add(filter);

        File file = fileChooser.showOpenDialog(null);
        filepath = file.toURI().toString();

        if(filepath != null)
        {

            Media media = new Media(filepath);
            mediaPlayer = new MediaPlayer(media);

            mediaView.setMediaPlayer(mediaPlayer);


            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty height = mediaView.fitHeightProperty();


            width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));


            slider.setValue(mediaPlayer.getVolume()*100.0D);
            slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(slider.getValue()/100.0D);
                }
            });



            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {

                    seeslider.setMax(mediaPlayer.getTotalDuration().toSeconds());
                    seeslider.setValue(t1.toSeconds());
                }
            });


            seeslider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mediaPlayer.seek(Duration.seconds(seeslider.getValue()));
                }
            });





            mediaPlayer.play();

        }


    }

    @FXML
    private void pauseVideo(ActionEvent event){

        mediaPlayer.pause();


    }

    @FXML
    private void playVideo(ActionEvent event){

        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }
    @FXML
    private void stopVideo(ActionEvent event){

        mediaPlayer.stop();

    }
    @FXML
    private void fastVideo(ActionEvent event){
        mediaPlayer.setRate(1.5);
    }
    @FXML
    private void fasterVideo(ActionEvent event){
        mediaPlayer.setRate(2);
    }
    @FXML
    private void exitVideo(ActionEvent event){

        System.exit(0);

    }

    @FXML
    private void slowVideo(ActionEvent event){

        mediaPlayer.setRate(0.75);

    }
    @FXML
    private void slowerVideo(ActionEvent event){

        mediaPlayer.setRate(0.5);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
