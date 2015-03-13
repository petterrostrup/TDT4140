package application;
	
import java.awt.Image;

import javax.swing.ImageIcon;

import classes.User;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
	        
	        //Image image = new ImageIcon("picture.gif").getImage();
	        
//	        URL url = new URL("com/xyz/resources/camera.png");
//	        Toolkit kit = Toolkit.getDefaultToolkit();
//	        Image img = kit.createImage(url);
//	        getFrame().setIconImage(img);
	        
	        //java.net.URL url = ClassLoader.getSystemResource("com/xyz/resources/camera.png");
	        
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("kalender.fxml"));
		    Scene scene = new Scene((Parent) loader.load());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			KalenderController newCont = loader.<KalenderController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.show();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void startRegister(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("registrer.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			RegistrerController newCont = loader.<RegistrerController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.show();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void startLagAvtale(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("lagavtale.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			LagAvtaleController newCont = loader.<LagAvtaleController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.show();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void startProfil(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("profil.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			ProfilController newCont = loader.<ProfilController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.show();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void startRedigerBruker(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("redigerbruker.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			RedigerBrukerController newCont = loader.<RedigerBrukerController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.show();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void startAdministrerGrupper(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("administrergrupper.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			AdministrerGrupper newCont = loader.<AdministrerGrupper>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.show();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
	}
	
	
	
	//(Kan bare launche ett vindu om gangen, resten må kalles fra andre klasser)
	public static void main(String[] args) {
		launch(args);
	
	
	}
}
