package application;
	

import javafx.scene.image.Image;
import classes.Appointment;
import classes.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	private User sessionUser;
//    String css = LoginController.class.getResource("LaserTheme.css").toExternalForm();
	Image icon = new Image(getClass().getResourceAsStream("../images/calicon.png"));
	//Login skjerm
	@Override
	public void start(Stage stage) throws Exception {
	       Parent root = FXMLLoader.load(getClass().getResource("../fxml/logginn.fxml"));
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/kalender.fxml"));
		    Scene scene = new Scene((Parent) loader.load());
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			KalenderController newCont = loader.<KalenderController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("uCal");
			primaryStage.show();
			primaryStage.setResizable(false);
			
		} 
		
		catch(Exception e) {
			System.out.println("Error occured: " + e);
		}
	}
	
	
	public void startRegister(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/registrer.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			RegistrerController newCont = loader.<RegistrerController>getController();
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("uCal");
			primaryStage.show();		
			primaryStage.setResizable(false);
		} 
		
		catch(Exception e) {
			System.out.println("Error occured: " + e);
		}
	}
	
	
	public void startLagAvtale(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/lagavtale.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			LagAvtaleController newCont = loader.<LagAvtaleController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("uCal");
			primaryStage.show();
			primaryStage.setResizable(false);
		} 
		
		catch(Exception e) {
			System.out.println("Error occured: " + e);
		}
	}
	
	
	public void startProfil(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/profil.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());

			primaryStage.setScene(scene);
			ProfilController newCont = loader.<ProfilController>getController();
			newCont.setSession(this.sessionUser);		
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("uCal");
			primaryStage.show();
			primaryStage.setResizable(false);
		} 
		
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error occured: " + e);
		}
	}
	
	
	public void startRedigerBruker(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/redigerbruker.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			RedigerBrukerController newCont = loader.<RedigerBrukerController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("uCal");
			primaryStage.show();
			primaryStage.setResizable(false);
		} 
		
		catch(Exception e) {
			System.out.println("Error occured: " + e);
		}
	}
	public void startAdministrerGrupper(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/administrergrupper.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			AdministrerGrupperController newCont = loader.<AdministrerGrupperController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("uCal");
			primaryStage.show();
			primaryStage.setResizable(false);
		} 
		
		catch(Exception e) {
			System.out.println("Error occured: " + e);
		}
	}
	public void startNyGruppe(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/nygruppe.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			NyGruppeController newCont = loader.<NyGruppeController>getController();
			newCont.setSession(this.sessionUser);
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("uCal");
			primaryStage.show();
			primaryStage.setResizable(false);
		} 
		
		catch(Exception e) {
			System.out.println("Error occured: " + e);
		}
	}

	
	
	public void startAvtaleOversikt(Stage primaryStage, Appointment appointment){

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/avtaleoversikt.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			AvtaleOversiktController newCont = loader.<AvtaleOversiktController>getController();
			
			newCont.setSession(this.sessionUser, appointment);
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("uCal");
			primaryStage.show();
			primaryStage.setResizable(false);
		} 
		
		catch(Exception e) {
			System.out.println("Error occured: " + e);
		}
	}
	
	public void startDagsOversikt(Stage primaryStage, Appointment appointment) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/dagsoversikt.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			DagsOversiktController newCont = loader.<DagsOversiktController>getController();	
			newCont.setSession(this.sessionUser, appointment);	
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("uCal");
			primaryStage.show();
			primaryStage.setResizable(false);
		} 
		
		catch(Exception e) {
			System.out.println("Error occured: " + e);
		}
	}
	
	
	
	public void startRedigerAvtale(Stage primaryStage, Appointment appointment) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/redigeravtale.fxml"));		    
		    Scene scene = new Scene((Parent) loader.load());
		    ProfilController profil = new ProfilController();
	        scene.getStylesheets().add(profil.getCss());
			primaryStage.setScene(scene);
			RedigerAvtaleController newCont = loader.<RedigerAvtaleController>getController();
			
			newCont.setSession(this.sessionUser, appointment);
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("uCal");
			primaryStage.show();
			primaryStage.setResizable(false);
		} 
		
		catch(Exception e) {
			System.out.println("Error occured: " + e);
		}
	}

	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
	}
	//(Kan bare launche ett vindu om gangen, resten m� kalles fra andre klasser)
	public static void main(String[] args) {
		launch(args);
	
	
	}
}
