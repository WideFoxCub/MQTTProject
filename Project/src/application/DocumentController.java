package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

public class DocumentController implements Initializable {
    
    @FXML
    private Label label1, label2, label3, label4, label5, label6, label7;
    @FXML
    private Button button;
    @FXML
    private ImageView image;
    @FXML
    private TextField textField;
    @FXML
    private AnchorPane anchorPane;
    
    private Subscriber subscriber;
    private Timeline clock;
    
    private Polyline spiral = new Polyline();
    ObservableList<Double> list = spiral.getPoints();
    private int RefreshTime = 1;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	subscriber = new Subscriber();
        subscriber.client();
    	anchorPane.getChildren().add(spiral);
        RefreshLabel();
    }
    
    private void RefreshLabel() {
    	clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
    		label1.setText(subscriber.value1);
    		label2.setText(subscriber.value2);
    		label3.setText(subscriber.value3);
    		label4.setText(subscriber.value4);
    		label5.setText(subscriber.value5);
    		label6.setText(subscriber.value6);
    		label7.setText(subscriber.value7);
    		visualizationTime(Integer.parseInt(subscriber.value1));
        }),
    		new KeyFrame(Duration.seconds(RefreshTime))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    
    private void visualizationTime(int time) {

    	list.clear();
    	spiral.setStroke(Color.BLACK);
    	spiral.setStrokeWidth(9);
    	
    	double x = 10;
    	double y = 10;
    	double angle = 0.0f;

        // Space between the spirals
        int a = 2, b = 2;

        for (int i = 0; i < time; i++)
        {
            angle = 0.1 * i;
            x = (a + b * angle) * Math.cos(angle);
            y = (a + b * angle) * Math.sin(angle);

            list.add(x + 100);
            list.add(y + 70);
        }
    }   
}