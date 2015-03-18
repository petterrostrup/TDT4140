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
import javafx.scene.control.RadioButton;
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
	
	@FXML
	private RadioButton deltarIkke;
	
	@FXML
	private RadioButton deltar;
	

	@FXML
	private void initialize(){
		//Lager instanser for testing
		Calendar c1 = Calendar.getInstance();
		Room testRoom = new Room("5", "Realfag 245", "somewhere", 10);
		
		
		//Instans av appointment
		Appointment appz = new Appointment("Gruppemøte", "Vanlig møte", "Bygg-1", testRoom,  c1.getTime() ,Timestamp.valueOf("2015-03-23 18:00:00.0"),Timestamp.valueOf("2015-03-23 20:00:00.0"), sessionUser);
		//Appointment appz = new Appointment(null, null, null, testRoom, null, null, null, null, sessionUser);
		
		
		//Setter info teksten til avtalen du trykker på
		tittel.setText(appz.getName());
		//rom.setText(room.getName);
		rom.setText(testRoom.getName());
		//deltagere.setText(appz.getParticipants().toString()); Skal vise en liste over deltagere
		dato.setText(appz.getDate().toString());
		tidspunkt.setText(appz.getStart().toString() + (" - ") + appz.getEnd());
		//tidspunkt.setText(appz.getEnd() + "-" );
		//beskrivelse.setText(appz.getDescription()); TEXTAREA SUCKS, THIS DOESN'T WORK
		
	}
	
	
	//Radiobutton til å velge attending
	public void attendButt(ActionEvent event) {
		System.out.println("halla");
		
	}
	
	//Radiobutton til å velge ikke attending
	public void notAttendButt(ActionEvent event) {
		System.out.println("hade");
	}
	
	

	//Knapp til redigerAvtale vindu
	public void redigerAvtale (ActionEvent event) {	
		try {
			Main newMain = new Main();
			newMain.setSession(this.sessionUser);
			newMain.startRedigerAvtale(new Stage());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//Henter stage parameter
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
	}


	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
	}
	
	

}
