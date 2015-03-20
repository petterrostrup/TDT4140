package application;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import classes.Appointment;
import classes.DatabaseCommunicator;
import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class AvtaleOversiktController {
	
	private User sessionUser;
	private Appointment currentAppointment;
	
	@FXML
	private Label tittel;
	
	@FXML
	private Label rom;
	
	@FXML
	private TextArea deltagere;
	
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
	}
	
	
	//Radiobutton til å velge attending
	public void attendButt(ActionEvent event) {
		//Når vi får en attending data verdi i databasen, skal denne endre en avtales data til attending;
		String sqlStatement = "UPDATE CONNECTED SET status = '" + 1 + "' WHERE appointment = '" + this.currentAppointment.getAppointmentID() + "' AND person = '" + this.sessionUser.getId() + "'";
		DatabaseCommunicator.update(sqlStatement);
	}
	
	//Radiobutton til å velge ikke attending
	public void notAttendButt(ActionEvent event) {
		String sqlStatement = "UPDATE CONNECTED SET status = '" + -1 + "' WHERE appointment = '" + this.currentAppointment.getAppointmentID() + "' AND person = '" + this.sessionUser.getId() + "'";
		DatabaseCommunicator.update(sqlStatement);
	}
	
	

	//Knapp til redigerAvtale vindu
	public void redigerAvtale (ActionEvent event) {	
		try {
			Main newMain = new Main();
			newMain.setSession(this.sessionUser);
			newMain.startRedigerAvtale(new Stage(), this.currentAppointment);
		} catch (Exception e) {
			System.out.println("Error occured: " + e);
		}
		//Henter stage parameter
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
	}

	//Setter det som skal vises spesielt for denne kontrolleren. GUI blir fylt ut med variabler etc.
	public void setSession(User sessionUser, Appointment sessionAppointment){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
		this.currentAppointment = sessionAppointment;
		currentAppointment.readParticipants();
		
		String sqlStatement = "SELECT * FROM CONNECTED WHERE appointment = '" + currentAppointment.getAppointmentID() + "' AND person = '" + this.sessionUser.getId() + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		
		try {
			if (results.next()) {
				if (results.getInt("status") == 1){
					deltar.setSelected(true);;
				}
				else if (results.getInt("status") == -1){
					deltarIkke.setSelected(true);;
				}
				
			}
		} catch (Exception e) {
			System.out.println("Error occured: " + e);
		}	
		
		ArrayList<User> userList = currentAppointment.getParticipants();
		
		String deltagereString = "";
		
		for (int i = 0; i < userList.size(); i++) {
			deltagereString += userList.get(i).getName() + "\n";
		}
		deltagere.setText(deltagereString);
		
		tittel.setText(currentAppointment.getName());
		dato.setText(currentAppointment.getDate().toString());
		
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
		
		String timestring = sdfTime.format(currentAppointment.getStart()) + " - " + sdfTime.format(currentAppointment.getEnd());
		
		tidspunkt.setText(timestring);
		beskrivelse.setText(currentAppointment.getDescription());
		rom.setText(currentAppointment.getLocation());
	}
	
	

}
