package application;
import java.sql.ResultSet;
import java.util.ArrayList;
import classes.Appointment;
import classes.DatabaseCommunicator;
import classes.MainCalendar;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
	}
	
	public void tilbakeButt (ActionEvent event) {
		try{
			Main newMain= new Main();
			newMain.setSession(this.sessionUser);
			newMain.startProfil(new Stage());
			 
		}catch (Exception e){
			System.out.println("Error occured: " + e);
		}
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
		
	}

	//Setter det som skal vises spesielt for denne kontrolleren. GUI blir fylt ut med variabler etc.
	public void setSession(User sessionUser, Appointment sessionAppointment) {
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
		this.currentAppointment = sessionAppointment;
		currentAppointment.readParticipants();
		currentAppointment.getDate();		
		
		String sqlStatement = "SELECT * FROM MEMBER WHERE person = '" + this.sessionUser.getId() + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		
		DagsCal = new MainCalendar();
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
		
		Appointment chosen = null;
		
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
				System.out.println("Error occured: " + e);
			}
			Node source = (Node) event.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
		}
		

	}
		
		
		
	}


