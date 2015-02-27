package application;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
	private TextField passord;
	
	@FXML
	private TextField nyttpassord;
	
	@FXML
	private TextField gnyttpassord;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier", "nilsad" , "passord1212");
	       Parent root = FXMLLoader.load(getClass().getResource("redigerbruker.fxml"));
	       
	        Scene scene = new Scene(root);
	        
	        stage.setTitle("Rediger bruker");
	        stage.setScene(scene);
	        stage.show();
	        
	}

	
	public void lagreButt (ActionEvent event) {
		System.out.println("test");
		//Lagre data fra skjema i database
	
	}
	
	
	public void openButt (ActionEvent event) {
		System.out.println("test2");
		//Åpne bilde
	
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
