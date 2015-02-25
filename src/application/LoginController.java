package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class LoginController extends Application {
	
	private int logFail = 0;
		
	@FXML
	private Button logginn;
	
	@FXML
	private TextField brukernavn;
	
	@FXML
	private PasswordField passord;
	
	@FXML
	private Label feilLabel; 
	
	@Override
	public void start(Stage stage) throws Exception {
	       Parent root = FXMLLoader.load(getClass().getResource("logginn.fxml"));
	       
	        Scene scene = new Scene(root);
	        
	        stage.setTitle("Login");
	        stage.setScene(scene);
	        stage.show();
	        
	        System.out.println("asdf");
	        feilLabel.setVisible(false);
	}


	        
	       
    public void logButt (ActionEvent event) {
    	System.out.println("hei");
    	String correctUsername = "admin";
		String correctPassword = "admin";
		//System.out.println("testytest");
	
		
		//failedLabel.setText("Feil brukernavn eller passord!");
			if((!passord.getText().isEmpty() && passord.getText().equals(correctPassword)) &&
			(!brukernavn.getText().isEmpty() && brukernavn.getText().equals(correctUsername))){ 
				//Sjekker om brukernavn og passord stemmer og bytter skjerm.
				
				//stage.resizableProperty().set(true);
				logFail = 0;
				
				
				//stage.close();
				Platform.exit();
				
			}
		else{	// Gir melding om at brukernavn og passord er feil
			if(logFail == 0){
				
				logFail = 1;
				brukernavn.setText("poop");
				brukernavn.setStyle("-fx-background:#FE2E2E");
				feilLabel.setVisible(true);
				
			}
		}
	}


    	
    	
    
	

	public static void main(String[] args) {
		launch(args);
	}
}
