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



public class Login extends Application {
	
	private int logFail = 0;
		
	@FXML
	private Button logginn;
	
	@FXML
	private TextField brukernavn;
	
	@FXML
	private PasswordField passord;
	
	@Override
	public void start(Stage stage) throws Exception {
	       Parent root = FXMLLoader.load(getClass().getResource("logginn.fxml"));
	       
	        Scene scene = new Scene(root, 500, 450);
	    
	        stage.setTitle("Login");
	        stage.setScene(scene);
	        stage.show();
	        
	        
	        //Button action handler 1
	        logginn.setOnAction((event) -> {
	        	System.out.println("test");
	        	});
	        
	        //Button action handler 2
	        logginn.setOnAction(new EventHandler<ActionEvent>(){
	    		public void handle(ActionEvent event){
	    			String correctUsername = "admin";
	    			String correctPassword = "admin";
	    			System.out.println("testytest");
	    		
	    			
	    			//failedLabel.setText("Feil brukernavn eller passord!");
	    				if((!passord.getText().isEmpty() && passord.getText().equals(correctPassword)) &&
	    				(!brukernavn.getText().isEmpty() && brukernavn.getText().equals(correctUsername))){ 
	    					//Sjekker om brukernavn og passord stemmer og bytter skjerm.
	    					
	    					stage.resizableProperty().set(true);
	    					logFail = 0;
	    					
	    					
	    					//stage.close();
	    					
	    					
	    				}
	    			else{	// Gir melding om at brukernavn og passord er feil
	    				if(logFail == 0){
	    					
	    					logFail = 1;
	    				}
	    			}
	    		}
	    	});
	    }
	

	public static void main(String[] args) {
		launch(args);
	}
}
