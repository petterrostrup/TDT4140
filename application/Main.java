package application;
	

import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;

import javafx.scene.image.*;
import javafx.scene.image.Image;
import classes.Appointment;
import classes.Room;
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
//    String css = LoginController.class.getResource("LaserTheme.css").toExternalForm();
	Image icon = new Image(getClass().getResourceAsStream("kappa.png"));
	//Login skjerm
	@Override
	public void start(Stage stage) throws Exception {
	       Parent root = FXMLLoader.load(getClass().getResource("logginn.fxml"));
	        final Scene scene = new Scene(root);
	        
	        ProfilController profil = new ProfilController(); 
	        scene.getStylesheets().add(profil.getCss());
//	        scene.getStylesheets().add(getClass().getResource("LaserTheme.css").toExternalForm());
	        
	        stage.getIcons().add(icon);
	        
	        stage.setTitle("uCal");
	        stage.setScene(scene);
	        stage.show();
			stage.setResizable(false);

	        		        
	}
	
	
	public void startKalender(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("kalender.fxml"));
		    Scene scene = new Scene((Parent) loader.load());
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			KalenderController newCont = loader.<KalenderController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.getIcons().add(icon);
			
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
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			RegistrerController newCont = loader.<RegistrerController>getController();
			primaryStage.getIcons().add(icon);
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
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			LagAvtaleController newCont = loader.<LagAvtaleController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.getIcons().add(icon);
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
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());

			primaryStage.setScene(scene);
			ProfilController newCont = loader.<ProfilController>getController();
			newCont.setSession(this.sessionUser);		
			primaryStage.getIcons().add(icon);
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
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			RedigerBrukerController newCont = loader.<RedigerBrukerController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.getIcons().add(icon);
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
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			AdministrerGrupperController newCont = loader.<AdministrerGrupperController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.getIcons().add(icon);
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
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			NyGruppeController newCont = loader.<NyGruppeController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.getIcons().add(icon);
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
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			AvtaleOversiktController newCont = loader.<AvtaleOversiktController>getController();
			
			Calendar c1 = Calendar.getInstance();
			Room testRoom = new Room("5", "Realfag 245", "somewhere", 10);
			User varUser = new User("testuser123", "Testpass12345", "test@gmail.com", "Test Testesen", "Testelia 12");
			c1.set(2015, Calendar.MARCH, 26);
			Appointment appointment4 = new Appointment("Gruppemøte", "Vanlig møte", "Bygg-1", testRoom, new ArrayList<User>(), c1.getTime(),Timestamp.valueOf("2015-03-26 21:00:00.0"),Timestamp.valueOf("2015-03-26 23:00:00.0"), varUser, "15");
			
			newCont.setSession(this.sessionUser, appointment4);
			primaryStage.getIcons().add(icon);
			primaryStage.show();
			primaryStage.setResizable(false);
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startDagsOversikt(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("dagsoversikt.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			DagsOversiktController newCont = loader.<DagsOversiktController>getController();
			newCont.setSession(this.sessionUser);		
			primaryStage.getIcons().add(icon);
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
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			RedigerAvtaleController newCont = loader.<RedigerAvtaleController>getController();
			
			Calendar c1 = Calendar.getInstance();
			Room testRoom = new Room("5", "Realfag 245", "somewhere", 10);
			User varUser = new User("testuser123", "Testpass12345", "test@gmail.com", "Test Testesen", "Testelia 12");
			c1.set(2015, Calendar.MARCH, 26);
			Appointment appointment4 = new Appointment("Gruppemøte", "Vanlig møte", "Bygg-1", testRoom, new ArrayList<User>(), c1.getTime(),Timestamp.valueOf("2015-03-26 21:00:00.0"),Timestamp.valueOf("2015-03-26 23:00:00.0"), varUser, "15");
			newCont.setSession(this.sessionUser, appointment4);
			primaryStage.getIcons().add(icon);
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
