package application;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

import classes.Appointment;
import classes.Login;
import classes.MainCalendar;
import classes.Room;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;


public class DagsOversiktController {
	
	private User sessionUser;
	
	
	private MainCalendar DagsCal;
	
	@FXML
	private ListView visAvtaler;
	
	@FXML
	private Button visAvtButt;
	
	private ObservableList<Object> appointz = FXCollections.observableArrayList();

	
	@FXML
	private void initialize(){
		//Klikk på en dag, hent oversikt over avtaler for dagen i listview
		//Sjekke om dagen er den samme som denne og vise alle avtaler for valgt dag
		
		
		//MainCalendar DagsCal = new MainCalendar();		
		
		
		//adde observable list 
		visAvtaler.setItems(appointz);
		
	}
	
	
	
	

	//Knapp tar deg til visAvtale vindu
	public void visAvtale (ActionEvent event) {	
		try {
			//Gå til avtale du har markert i listview
			System.out.println("asdtest");
			
			
			Main newMain = new Main();
			newMain.setSession(this.sessionUser);
			newMain.startAvtaleOversikt(new Stage());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//Henter stage parameter
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
	}





	public void setSession(User sessionUser) {
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
		
		DagsCal.fillCalendar(sessionUser.getId());
		
		
		ArrayList appointments = DagsCal.getAppointments();
		
		
		
	}

}
