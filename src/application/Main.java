package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import application.LoginController;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loginStart(){
		javafx.application.Application.launch(LoginController.class);}
	
	public static void lageAvtaleStart(){
		javafx.application.Application.launch(LagAvtaleController.class);}
	
	
	
	
	//(Kan bare launche ett vindu om gangen, resten m� kalles fra andre klasser)
	public static void main(String[] args) {
		//launch(args);
		loginStart();
		//lageAvtaleStart();
		
		
	}
}
