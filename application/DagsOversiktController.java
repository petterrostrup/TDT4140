package application;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

import classes.Appointment;
import classes.DatabaseCommunicator;
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
	private Appointment currentAppointment;
	
	private MainCalendar DagsCal;
	
	@FXML
	private ListView visAvtaler;
	
	@FXML
	private Button visAvtButt;
	
	private ObservableList<String> appointz = FXCollections.observableArrayList();
	
	private ArrayList<Appointment> myApps;

	
	@FXML
	private void initialize(){
		//Klikk på en dag, hent oversikt over avtaler for dagen i listview
		//Sjekke om dagen er den samme som denne og vise alle avtaler for valgt dag
		
		 
		
		
	}
	
	public void tilbakeButt (ActionEvent event) {
		try{
			Main newMain= new Main();
			newMain.setSession(this.sessionUser);
			newMain.startProfil(new Stage());
			 
		}catch (Exception e){
			e.printStackTrace();
		}
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
		
	}


	public void setSession(User sessionUser, Appointment sessionAppointment) {
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
		this.currentAppointment = sessionAppointment;
		currentAppointment.readParticipants();
		currentAppointment.getDate();
		
		
		String sqlStatement = "SELECT * FROM MEMBER WHERE person = '" + this.sessionUser.getId() + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		
		
		
		DagsCal = new MainCalendar();
		//System.out.println(sessionUser.getId());
		DagsCal.fillCalendar(sessionUser.getId());
		
		this.myApps = DagsCal.getAppointments();
		
		Appointment enApp;
		for (int i = 0; i < myApps.size(); i++) {
			if (myApps.get(i).getDate().equals(currentAppointment.getDate())){
				enApp = myApps.get(i);				
				this.appointz.add(enApp.getName());
			}
			
			
					}
		visAvtaler.setItems(this.appointz);
				}
			
		

	//Knapp tar deg til visAvtale vindu
	public void visAvtale (ActionEvent event) {
		String visAvtaleValgt = (String) visAvtaler.getSelectionModel().getSelectedItem();
//	// TRYKK HER, så sendes du til avtalen du trykket på
		Appointment chosen = null;
		System.out.println(visAvtaleValgt);
	
		if(visAvtaleValgt != null){
			try {
				Main newMain = new Main();
				newMain.setSession(this.sessionUser);
				for (int i = 0; i < myApps.size(); i++) {
					if (myApps.get(i).getName().equals(visAvtaleValgt.toString())){
						chosen = myApps.get(i);
					}
				}
				
				newMain.startAvtaleOversikt(new Stage(), chosen);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Node source = (Node) event.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
		}
		

	}
		
		
		
	}


