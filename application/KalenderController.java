package application;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import classes.Appointment;
import classes.MainCalendar;
import classes.Room;
import classes.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class KalenderController {
	
	private User sessionUser;
	
	@FXML
	private ScrollPane scrollpane;
	@FXML
	private GridPane gridpane;
	private GridPane saveGrid = gridpane;
	@FXML
	private DatePicker datepicker;
	@FXML
	private Label weeknr;
	@FXML
	private Label yearnr;
	@FXML
	private Label mandato;
	@FXML
	private Label tirdato;
	@FXML
	private Label onsdato;
	@FXML
	private Label torsdato;
	@FXML
	private Label fredato;
	@FXML
	private Label lordato;
	@FXML
	private Label sondato;
	@FXML
	private Button nesteUke;
	@FXML
	private Button prevWeek;
	@FXML
	private Label innloggetsom;
	
	private Calendar cal = Calendar.getInstance();
	private Calendar tempCal;
	private ObservableList<Node> avtaleCollection = FXCollections.observableArrayList();
	

	@FXML
	private void initialize(){
	        
		//Creating appointment panes
		datepicker.setValue(LocalDate.now());
		setWeek();
		
	}
	
	
	public void nextWeek(ActionEvent event){
		cal.add(Calendar.WEEK_OF_YEAR, +1);
		setWeek();
	}
	
	
	public void lastWeek(ActionEvent event){
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		setWeek();
	}

	
	public void setWeek(){
		tempCal = this.cal;
		weeknr.setText(Integer.toString(tempCal.get(Calendar.WEEK_OF_YEAR)));
		yearnr.setText(Integer.toString(tempCal.get(Calendar.YEAR)));
		fillCalendar();
		
		setDayLabel(mandato, 2);
		setDayLabel(tirdato, 3);
		setDayLabel(onsdato, 4);
		setDayLabel(torsdato,5);
		setDayLabel(fredato, 6);
		setDayLabel(lordato, 7);
		setDayLabel(sondato, 1);
		
	}
	
	public void setDayLabel(Label label, int weekDay){
		tempCal.set(Calendar.DAY_OF_WEEK, weekDay);
		label.setText(Integer.toString(tempCal.get(Calendar.DAY_OF_MONTH)));
	}
	
	
	public void fillCalendar(){
		MainCalendar kalender = new MainCalendar();
		kalender.fillTest();
		ArrayList<Appointment> avtaler = kalender.getAppointments();
		for (Appointment avtale: avtaler){
			LocalTime start = avtale.getStart();
			LocalTime end = avtale.getEnd();
			String avtaleNavn = avtale.getName();
			
			Date date = avtale.getDate();
			Calendar calDate = Calendar.getInstance();
			calDate.setTime(date);
			
			if (calDate.get(Calendar.WEEK_OF_YEAR) == tempCal.get(Calendar.WEEK_OF_YEAR)
					&& calDate.get(Calendar.YEAR) == tempCal.get(Calendar.YEAR)){
				if (calDate.get(Calendar.DAY_OF_WEEK) == 1){
					filler(timeToGrid(start), avtaleNavn, 7);
				}
				else{
					filler(timeToGrid(start), avtaleNavn, calDate.get(Calendar.DAY_OF_WEEK)-1);
				}
			}
		}
	}
	
	
	public void filler(int startTime, String navn, int weekDay){
		Pane avtalePane = new Pane();
		avtalePane.setStyle("-fx-background-color:#FE2E2E");
		avtalePane.setPrefSize(122, 60);
		Label avtaleNavn = new Label(navn);
		avtalePane.getChildren().add(avtaleNavn);
		gridpane.add(avtalePane, weekDay, startTime);	
	}
	
	
	public int timeToGrid(LocalTime time){
		String timeString = time.toString();
		String [] timeSplit = timeString.split(":");
		int timeInt = Integer.parseInt(timeSplit[0])-6;
		return timeInt;
	}

	
	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
		innloggetsom.setText("Innlogget som: " + this.sessionUser.getName());
	}
	
	
	public void avtaleTester (int startTime, String navn, String style, int dag){
		Pane avtalePane = new Pane();
		avtalePane.setStyle(style);
		avtalePane.setPrefSize(122, 60);
		Label avtaleNavn = new Label(navn);
		avtalePane.getChildren().add(avtaleNavn);
		gridpane.add(avtalePane, dag, startTime-6, 1, 2);	
	}
	
	
	//Bytter vindu til LagAvtale
	public void avtaleButt (ActionEvent event) {
		try {
			Main newMain =new Main();
			newMain.setSession(this.sessionUser);
			newMain.startLagAvtale(new Stage());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
			
		Node  source = (Node)  event.getSource(); 
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.close();
	}
		
		
	//Bytter vindu til login
	public void logoutButt (ActionEvent event) {
		try {
			new Main().start(new Stage());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//Henter stage parameter
		Node  source = (Node)  event.getSource(); 
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.close();
	}

	
	//Bytter vindu til bruker
	public void profilButt (ActionEvent event) {
		try {
			Main newMain = new Main();
			newMain.setSession(this.sessionUser);
			newMain.startProfil(new Stage());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		//Henter stage parameter
		Node  source = (Node)  event.getSource(); 
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	
	

	
	

}
