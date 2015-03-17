package application;
import java.sql.Timestamp;
import java.util.Calendar;

import classes.Appointment;
import classes.Login;
import classes.Room;
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
	

	//private Appointment nyApp;
	
	
	
	
	@FXML
	private void initialize(){
		//Lager
		Calendar c1 = Calendar.getInstance();
		Room testRoom = new Room("5", "Realfag 245", "somewhere", 10);
		
		
		Appointment appz = new Appointment("Gruppemøte", "Vanlig møte", "Bygg-1", testRoom,  c1.getTime() ,Timestamp.valueOf("2015-03-23 18:00:00.0"),Timestamp.valueOf("2015-03-23 20:00:00.0"), sessionUser);
		//Appointment appz = new Appointment(null, null, null, testRoom, null, null, null, null, sessionUser);
		

		tittel.setText(appz.getName());
		//rom.setText(appz.getRoom().toString()); må få vist romnavnet fra Room klassen
		//deltagere.setText(appz.getParticipants().toString()); må vise en liste over deltagere
		dato.setText(appz.getDate().toString());
		tidspunkt.setText(appz.getStart().toString() + "-");
		tidspunkt.setText(appz.getEnd() + "-" );
		System.out.println(appz.getDescription());
		beskrivelse.setText(appz.getDescription());
		
	}
	
//	private Appointment VisAvtale(){
//		
//		Appointment visApp = null;
//		
//		//newuser = Login.login(brukernavn.getText(), passord.getText());
//		visApp = new Appointment(null, null, null, null, null, null, null, null, null);
//		//nyApp.getName();
//		System.out.println(visApp.getName());
//		System.out.println("asd");
//		//tittel.setText(visApp.getName());
//		tittel.setText("hei");
//		
//		
//		return visApp;
//		
//	}
	
	
	public Appointment redigerAvtale (ActionEvent event) {	
		
		//Calendar avt = Calendar.getInstance();
		//avt.set
		
		
		
		Appointment nyApp = new Appointment(null, null, null, null, null, null, null, null, sessionUser);
		//Appointment nyApp = new Appointment(null, null, null, null, null, null, null, sessionUser);
		//Appointment nyApp = new Appointment();
		
		
		try {
			nyApp.getName();			
			System.out.println(nyApp.getName());
			System.out.println("tst");
			
						
			
			
		} catch (Exception e) {
			System.out.println("asd");
			System.out.println(e);
		
		}if(nyApp != null){
			System.out.println("Viser avtaleoversikt");
			
//			tittel.setText(null);
//			rom.setText(null);
//			deltagere.setText(null);
//			dato.setText(null);
//			tidspunkt.setText(null);
//			beskrivelse.setText(null);
			
			
//			Node  source = (Node)  event.getSource(); 
//		    Stage stage  = (Stage) source.getScene().getWindow();
//		    stage.close();
		}
		else{
		
		
		
		
		return nyApp;
		
		}
		return nyApp;
	}


	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
	}
	
	

}
