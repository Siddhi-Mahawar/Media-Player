package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Scene scene = new Scene(root,600,400);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Media Player");
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    primaryStage.setFullScreen(true);
                }

            }
        });

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
