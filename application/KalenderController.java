package application;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import classes.Appointment;
import classes.MainCalendar;
import classes.Room;
import classes.User;
import javafx.application.Application;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class KalenderController {
	
	private User sessionUser;
	
	@FXML
	private GridPane gridpane;
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
	private Button nextWeek;
	@FXML
	private Button prevWeek;
	
	
	@FXML
	private void initialize(){
	        
		//Creating appointment panes
		datepicker.setValue(LocalDate.now());
		fillCalendar();
	}

	
	public void fillCalendar(){
		MainCalendar kalender = new MainCalendar();
		kalender.fillTest();
		ArrayList<Appointment> avtaler = kalender.getAppointments();
		for (Appointment avtale: avtaler){
			LocalTime start = avtale.getStart();
			LocalTime end = avtale.getEnd();
			
			String startString = start.toString();
			String [] startSplit = startString.split(":");
			int startInt = Integer.parseInt(startSplit[0])-6;
			
			String endString = end.toString();
			String [] endSplit = endString.split(":");
			int endInt = Integer.parseInt(endSplit[0])-6;
			
			String avtaleNavn = avtale.getName();
			filler(startInt, avtaleNavn);
			avtaleTester(12, "Lunsj", "-fx-background-color:#9999FF", 4);
			avtaleTester(15, "Sluttm�te", "-fx-background-color:FF33CC", 6);
			avtaleTester(7, "Trening", "-fx-background-color:#33CC33", 7);
			avtaleTester(9, "Morgenm�te", "-fx-background-color:#0033CC", 2);
		}
	}

	
	public void setSession(User sessionUser){
		this.sessionUser = sessionUser;
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
			new LagAvtaleController().start(new Stage());
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
			new LoginController().start(new Stage());
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
			new ProfilController().start(new Stage());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		//Henter stage parameter
		Node  source = (Node)  event.getSource(); 
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	
	public void filler(int startTime, String navn){
		Pane avtalePane = new Pane();
		avtalePane.setStyle("-fx-background-color:#FE2E2E");
		avtalePane.setPrefSize(122, 60);
		Label avtaleNavn = new Label(navn);
		avtalePane.getChildren().add(avtaleNavn);
		gridpane.add(avtalePane, 1, startTime);	
	}


}
