package application;

import java.sql.Connection;
import java.sql.DriverManager;

import classes.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RedigerBrukerController extends Application {
	
	@FXML
	private ImageView imageview;
	
	@FXML
	private TextField navn;
	
	@FXML
	private TextField brukernavn;
	
	@FXML
	private TextField epost;
	
	@FXML
	private TextField adresse;
	
	@FXML
	private PasswordField passord;
	
	@FXML
	private PasswordField nyttpassord;
	
	@FXML
	private PasswordField gnyttpassord;
	
	@FXML
	private Label ugyldigNavn;
	
	@FXML
	private Label ugyldigNyttBrukernavn;
	
	
	
	String navnRed;
	String nyttBrukernavnRed;
	String epostRed;
	String adresseRed;
	String gammeltPassordRed;
	String nyttPassordRed;
	String gnyttPassordRed;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no/petternr_calendar", "petternr_user" , "gruppe61");
	       Parent root = FXMLLoader.load(getClass().getResource("redigerbruker.fxml"));
	       
	        Scene scene = new Scene(root);
	        
	        stage.setTitle("Rediger bruker");
	        stage.setScene(scene);
	        stage.show();
	        
	}
	
	//setTextFill(Color.RED);

	//Lagre data fra skjema i database
	public void lagreButt (ActionEvent event) {
		//System.out.println("test");
		//boolean dataOk = true;
		
		
		navnRed = navn.getText();
		nyttBrukernavnRed = brukernavn.getText();
		epostRed = epost.getText();
		adresseRed = adresse.getText();
		gammeltPassordRed = passord.getText();
		nyttPassordRed = nyttpassord.getText();
		gnyttPassordRed = gnyttpassord.getText();
		//System.out.println(nyttBrukernavnRed);
		
		
		ugyldigNavn.setText("");
		ugyldigNyttBrukernavn.setText("");
		
		
		
		
		
		try {User varUser = new User(nyttBrukernavnRed, nyttPassordRed, epostRed, navnRed, adresseRed);
		//System.out.println(varUser);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		
//		if(navnRed.length()<2){
//			ugyldigNavn.setText("lol");
//			dataOk = false;
//		}
//		//Inneholder tall
//		else if(navnRed.matches(".*\\d.*")){
//			ugyldigNavn.setText("Fornavn kan ikke inneholde tall");
//			dataOk = false;
//		}
		
		
		
		
		
		
	
	}
	
	
	public void openButt (ActionEvent event) {
		System.out.println("test2");
		//Åpne bilde
	
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
