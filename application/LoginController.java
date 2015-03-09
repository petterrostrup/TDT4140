package application;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import sun.util.logging.resources.logging_pt_BR;
import application.RegistrerController;
import classes.User;

import com.sun.javafx.property.adapter.PropertyDescriptor.Listener;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	
	@FXML
	private Button registrer;
	
	@Override
	public void start(Stage stage) throws Exception {
	       Parent root = FXMLLoader.load(getClass().getResource("logginn.fxml"));
	       final Connection con = DriverManager.getConnection("jdbc:mysql://jdbc:mysql://mysql.stud.ntnu.no/petternr_calendar", "petternr_user" , "gruppe61");
	       
	        final Scene scene = new Scene(root);
	        
	        stage.setTitle("Login");
	        stage.setScene(scene);
	        stage.show();
	      //stage.resizableProperty().set(true/false);
	        
	        
	        
	        
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
	   
	
	
	@FXML
	//Bytter vindu til registreringsskjerm
	public void regButt (ActionEvent event) {
		//System.out.println("hade");
		//RegistrerBrukerKlasse().start(new Stage());
		try {
			new RegistrerController().start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Henter stage parameter
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
		
	}
	
	
	
	//User user = new User(null, null, null, null, null);
	
    public void logButt (ActionEvent event) {
    	//String correctUsername = user.getUserName();
    	//String correctPassword = user.getPassword();
    	//String correctUsername = "admin";
		//String correctPassword = "admin";
    	String correctPassword = "";
		ResultSet resultSet = null;
		
		try {
			PreparedStatement statement = con.prepareStatement ("select * from userTable where username = " + "'" + brukernavn.getText().toString() + "'");
			ResultSet results = statement.executeQuery();
			results.next();
			resultSet = results;
			correctPassword += results.getString(8);
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

			if(!passord.getText().isEmpty() && passord.getText().equals(correctPassword)){ //Sjekker om brukernavn og passord stemmer og bytter skjermbilde.
				
				//stage.resizableProperty().set(true);
				logFail = 0;
				
				try {
					new Main().start(new Stage());
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				//Henter stage parameter
				Node  source = (Node)  event.getSource(); 
			    Stage stage  = (Stage) source.getScene().getWindow();
			    stage.close();
				
			}
		else{	// Gir melding om at brukernavn eller passord er feil
			if(logFail == 0){
				
				logFail = 1;
				//brukernavn.setText("noob");
				brukernavn.setStyle("-fx-background:#FE2E2E");
				passord.setStyle("-fx-background:#FE2E2E");
				feilLabel.setText("Feil brukernavn eller passord");
				
				
			}
		;
	        
	        
			
			
				
				
	        
	        

	        
	}}});}
	

	public static void main(String[] args) {
		launch(args);
	}
}
