package application;

import classes.Login;
import classes.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
	
	@FXML
	private Button registrer;
	
	@FXML
	private void initialize(){
	}
	
	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
	}
	
	public void cheatButt (ActionEvent event) {				
		brukernavn.setText("kong1");
		passord.setText("Qwe123");
		
	}
	
	//Bytter vindu til registreringsskjerm
	public void regButt (ActionEvent event) {				
		try {
			new Main().startRegister(new Stage());
		} catch (Exception e) {
			System.out.println("Error occured: " + e);
		}
		//Henter stage parameter
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
		
	}
    public User logButt (ActionEvent event) {
    	
    	User newuser = null;
    	
		try {
			//Henter brukernavn og passord fra tekstfeltene
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
