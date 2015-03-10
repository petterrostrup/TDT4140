package application;

import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.RegistrerController;
import classes.Login;
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




public class LoginController {
	
	private User sessionUser;
		
	@FXML
	private Button logginn;
	
	@FXML
	private TextField brukernavn;
	
	@FXML
	private PasswordField passord;
	
	@FXML
	private Label feilLabel;
	
//	@FXML
//	private Button registrer;
	
	@FXML
	private void initialize(){
		
	}
//	public void start(Stage stage) throws Exception {
//	       Parent root = FXMLLoader.load(getClass().getResource("logginn.fxml"));
//	       
//	        final Scene scene = new Scene(root);
//	        
//	        stage.setTitle("Login");
//	        stage.setScene(scene);
//	        stage.show();
//	      //stage.resizableProperty().set(true/false);
//	        
//	        //User varUser = new User("testabrur", "Pass123", "tester@gmail.com", "Testus Testson", "Testelia 14");
//	        //varUser.saveUser();
//	        
//	        stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
//	        	@Override
//	        	public void handle(KeyEvent p) {
//	        		if(p.getCode()==KeyCode.ENTER)
//	        		{
//	        			System.out.println("trykket enter");
//	        			//LoginController().logButt(new ActionEvent());
//	        			//new LoginController().logButt(new ActionEvent());
//	        			//LoginController.logButt(ActionEvent);
//	        		}
//	        	}
//	        });	        
//	}
	
	public void setSession(User sessionUser){
		this.sessionUser = sessionUser;
	}
	
	
	
	//Bytter vindu til registreringsskjerm
	public void regButt (ActionEvent event) {
		//System.out.println("hade");
		//RegistrerBrukerKlasse().start(new Stage());
		
				
		try {
			new Main().startRegister(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Henter stage parameter
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
		
	}
	
	
	
	
    public User logButt (ActionEvent event) {
    	//String correctUsername = "admin";
		//String correctPassword = "admin";
    	
    	
    	User newuser = null;
    	
		try {
			//Henter brukernavn og passord fra tekstfeltene
			//System.out.println(brukernavn.getText());
			//System.out.println(passord.getText());
			newuser = Login.login(brukernavn.getText(), passord.getText());
		} catch (Exception e) {
			System.out.println(e);
		}
		if(newuser != null){
			System.out.println("logget inn");
			Main newMain = new Main();
			newMain.setSession(newuser);
			newMain.startKalender(new Stage());
			
			Node  source = (Node)  event.getSource(); 
		    Stage stage  = (Stage) source.getScene().getWindow();
		    stage.close();
		}
		else{
			// Gir melding om at brukernavn eller passord er feil
			brukernavn.setStyle("-fx-background:#FE2E2E");
			passord.setStyle("-fx-background:#FE2E2E");
			feilLabel.setText("Feil brukernavn eller passord");
		}
		return newuser;
    }

	
}
