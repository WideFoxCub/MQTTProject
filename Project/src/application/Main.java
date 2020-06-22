package application;
	
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Document.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("document.css").toExternalForm());
        
        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
            	Alert alert = new Alert(AlertType.CONFIRMATION);
            	alert.setTitle("Exit");
            	alert.setContentText("Do you really want to quit?");
            	Optional<ButtonType> result = alert.showAndWait();
            	if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            		primaryStage.close();
            	}
            }
        });

        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.setMaximized(true);
        primaryStage.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}
}