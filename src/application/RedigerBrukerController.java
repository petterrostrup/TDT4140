package application;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	
	
	String navnRed;
	String nyttBrukernavnRed;
	String epostRed;
	String adresseRed;
	String gammeltPassordRed;
	String nyttPassordRed;
	
	
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
		boolean dataOk = true;
		
		gammeltPassordRed = passord.getText();
		System.out.println(gammeltPassordRed);
		
		
		
		
	
	}
	
	
	public void openButt (ActionEvent event) {
		System.out.println("test2");
		//�pne bilde
	
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
