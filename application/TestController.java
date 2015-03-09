package application;
 
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
/**
 *
 * @web http://mobile-web-app.blogspot.com/
 */
public class TestController extends Application {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
     
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("java-buddy.blogspot.com: Load image");
        Group root = new Group();
         
        double width = 400;
        double height = 300;
        Scene scene = new Scene(root, width, height, Color.WHITE);
 
        Image image = new Image(getClass().getResourceAsStream("youngmaster.jpg"));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
 
        root.getChildren().add(imageView);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}