package application;
	
import classes.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import application.LoginController;


public class Main extends Application {
	
	private User sessionUser;
	
	@Override
	public void start(Stage primaryStage) {
		try {
		    Parent root = FXMLLoader.load(getClass().getResource("kalender.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setSession(User sessionUser){
		this.sessionUser = sessionUser;
	}
	
	
	
	//(Kan bare launche ett vindu om gangen, resten må kalles fra andre klasser)
	public static void main(String[] args) {
		launch(args);
	
	
	}
}
