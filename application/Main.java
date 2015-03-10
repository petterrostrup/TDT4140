package application;
	
import classes.User;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import application.LoginController;


public class Main extends Application {
	
	private User sessionUser;
	
	@Override
	public void start(Stage stage) throws Exception {
	       Parent root = FXMLLoader.load(getClass().getResource("logginn.fxml"));
	       
	        final Scene scene = new Scene(root);
	        
	        stage.setTitle("Login");
	        stage.setScene(scene);
	        stage.show();
	      //stage.resizableProperty().set(true/false);
	        
	        //User varUser = new User("testabrur", "Pass123", "tester@gmail.com", "Testus Testson", "Testelia 14");
	        //varUser.saveUser();
	        
	        stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
	        	@Override
	        	public void handle(KeyEvent p) {
	        		if(p.getCode()==KeyCode.ENTER)
	        		{
	        			System.out.println("trykket enter");
	        			//LoginController().logButt(new ActionEvent());
	        			//new LoginController().logButt(new ActionEvent());
	        			//LoginController.logButt(ActionEvent);
	        		}
	        	}
	        });	        
	}
	
	public void startKalender(Stage primaryStage) {
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
	
	
	public void startRegister(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("registrer.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void startLagAvtale(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("lagavtale.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void startProfil(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("profil.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void startRedigerBruker(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("redigerbruker.fxml"));
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
