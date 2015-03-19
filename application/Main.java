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
	
	
	//Login skjerm
	@Override
	public void start(Stage stage) throws Exception {
	       Parent root = FXMLLoader.load(getClass().getResource("logginn.fxml"));
	       
	        final Scene scene = new Scene(root);
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        stage.setTitle("CalenderBook");
	        stage.setScene(scene);
	        stage.show();
			stage.setResizable(false);
	        		        
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
			primaryStage.setResizable(false);
			
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

			primaryStage.show();		
			primaryStage.setResizable(false);
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
			primaryStage.setResizable(false);
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
			primaryStage.setResizable(false);
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
			primaryStage.setResizable(false);
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
			AdministrerGrupperController newCont = loader.<AdministrerGrupperController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.show();
			primaryStage.setResizable(false);
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void startNyGruppe(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("nygruppe.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			NyGruppeController newCont = loader.<NyGruppeController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.show();
			primaryStage.setResizable(false);
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void startAvtaleOversikt(Stage primaryStage){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("avtaleoversikt.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			AvtaleOversiktController newCont = loader.<AvtaleOversiktController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.show();
			primaryStage.setResizable(false);
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void startRedigerAvtale(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("redigeravtale.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			RedigerAvtaleController newCont = loader.<RedigerAvtaleController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.show();
			primaryStage.setResizable(false);
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
