package application;
import classes.Appointment;
import classes.Login;
import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class AvtaleOversiktController {
	
	private User sessionUser;
	
	@FXML
	private Label tittel;
	
	@FXML
	private Label rom;
	
	@FXML
	private Label deltagere;
	
	@FXML
	private Label dato;
	
	@FXML
	private Label tidspunkt;
	
	@FXML
	private Label beskrivelse;
	
	@FXML
	private Button redigerAvtale;

	private Appointment nyApp;
	
	@FXML
	private void initialize(){
	}
	
//	private Appointment VisAvtale(){
//		
//		Appointment nyApp = null;
//		
//		//newuser = Login.login(brukernavn.getText(), passord.getText());
//		nyApp = new Appointment(null, null, null, null, null, null, null, null, null);
//		//nyApp.getName();
//		System.out.println(nyApp.getName());
//		System.out.println("asd");
//		tittel.setText(nyApp.getName());
//		
//		
//		return nyApp;
//		
//	}
	
	
	public Appointment redigerAvtale (ActionEvent event) {	
		
		Appointment nyApp = new Appointment(null, null, null, null, null, null, null, null, sessionUser);
		
		
		try {
			nyApp.getName();			
			System.out.println(nyApp.getName());
			System.out.println("asd");
			
						
			
			
		} catch (Exception e) {
			System.out.println("asd");
			System.out.println(e);
		
		}
		return nyApp;
		
		
	}


	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
	}
	
	

}
