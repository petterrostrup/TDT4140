package application;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class KalenderController {
	
	private User sessionUser;
	private MainCalendar kalender;
	
    @FXML
    private Label week1, week2, week3, week4, week5, week6;
    @FXML
    private Label lordato, tirdato, onsdato, torsdato, fredato, mandato, sondato;
    @FXML
    private Label month10, month20, month30, month40, month50, month60, month70, month11, month21, month31, month41;
    @FXML
    private Label month51, month61, month71, month12, month22, month32, month42, month52, month62, month72, month13;
    @FXML
    private Label month23, month33, month43, month53, month63, month73, month14, month24, month34, month44, month54;
    @FXML
    private Label month64, month74, month15, month25, month35, month45, month55, month65, month75;
    
    Label[] dayList = new Label[]{};
    Label[] weekList = new Label[]{};    
    ObservableList<Label> monthWeeks = FXCollections.observableArrayList();
    ObservableList<Label> monthDays = FXCollections.observableArrayList();
    @FXML
    private Label notification;
    @FXML
    private Pane weekHeaderPane;
    @FXML
    private Pane monthPane;
    @FXML
    private Label monthLabel;
    @FXML
    private RadioButton radioWeek;
    @FXML
    private Label yearMonth;
    @FXML
    private Button nesteUke;
    @FXML
    private Label innloggetsom;
    @FXML
    private DatePicker datepicker;
    @FXML
    private GridPane gridpane;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private Button prevWeek;
    @FXML
    private Pane monthHeaderPane;
    @FXML
    private Button nesteMoned;
    @FXML
    private ToggleGroup radioGroup1;
    @FXML
    private GridPane monthGrid;
    @FXML
    private Button sisteMoned;
    @FXML
    private Label yearnr;
    @FXML
    private Label weeknr;
    @FXML
    private Label ukeLabel;
    
	private Calendar cal = Calendar.getInstance();
	private Calendar tempCal = Calendar.getInstance();
	ObservableList<Node> avtaleCollection = FXCollections.observableArrayList();
	ObservableList<Node> avtaleMonthCollection = FXCollections.observableArrayList();
	ObservableList<String> paneCollection = FXCollections.observableArrayList();
	@SuppressWarnings("rawtypes")
	private EventHandler appointmentClick;
	

	@FXML
	private void initialize(){
		addToMonthDays();
		addToMonthWeeks();
		avtaleCollection.addAll(gridpane.getChildren());
		avtaleMonthCollection.addAll(monthGrid.getChildren());
		//Creating appointment panes
		datepicker.setValue(LocalDate.now());
		scrollpane.setVisible(true);
		weekHeaderPane.setVisible(true);
		monthPane.setVisible(false);
		monthHeaderPane.setVisible(false);
		
		

		
	}
	
	
	//Fills the week labels in the month view to an array list
	public void addToMonthWeeks(){
		this.weekList = new Label[]{week1, week2, week3, week4, week5, week6};
		for (Label label: weekList){
			this.monthWeeks.add(label);
		}
	}

	//Fills the day labels in the month view to an array list
	public void addToMonthDays(){
		this.dayList = new Label[]{month10, month20, month30, month40, month50, month60, month70, month11, month21, month31, month41,
	    		month51, month61, month71, month12, month22, month32, month42, month52, month62, month72, month13, month23, month33, month43,
	    		month53, month63, month73, month14, month24, month34, month44, month54, month64, month74, month15, month25, month35, month45,
	    		month55, month65, month75};
		for (Label label: dayList){
			this.monthDays.add(label);
		}
	}
	
	//Shows the week view when the radio button is set to Uke
	public void radioUke(ActionEvent event){
		scrollpane.setVisible(true);
		weekHeaderPane.setVisible(true);
		weekHeaderPane.setDisable(false);
		monthPane.setVisible(false);
		monthHeaderPane.setVisible(false);
		monthHeaderPane.setDisable(true);
		setWeek();
		
	}
	
	//Shows the month view when the radio button is set to M�ned
	public void radioMoned(ActionEvent event){
		monthPane.setVisible(true);
		scrollpane.setVisible(false);
		weekHeaderPane.setVisible(false);
		weekHeaderPane.setDisable(true);
		monthHeaderPane.setVisible(true);
		monthHeaderPane.setDisable(false);
		setMonth();
	}
	
	//View next week
	public void nextWeek(ActionEvent event){
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		paneCollection.clear();
		gridpane.getChildren().clear();
		gridpane.getChildren().addAll(avtaleCollection);
		setWeek();
	}
	
	//View previous week
	public void lastWeek(ActionEvent event){
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		paneCollection.clear();
		gridpane.getChildren().clear();
		gridpane.getChildren().addAll(avtaleCollection);
		setWeek();
	}

	
	//Sets and fills the week view appointments and labels
	public void setWeek(){
		tempCal.setTime(this.cal.getTime());;
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
	
	//View next month
	public void nextMonth(ActionEvent event){
		cal.add(Calendar.MONTH, 1);
		setMonth();
	}
	
	//View previous month
	public void lastMonth(ActionEvent event){
		cal.add(Calendar.MONTH, -1);
		setMonth();
	}
	
	//Calculates when the month starts in the view
	public int calculateIndentation(Calendar date){
		int indentation = 0;
		Calendar monthDate = Calendar.getInstance();
		monthDate.setTime(date.getTime());
		monthDate.set(Calendar.DAY_OF_MONTH, 1);
		if (monthDate.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
			indentation = 7;
		}
		else if(monthDate.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
			indentation = 1;
		}
		else if(monthDate.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){
			indentation = 2;
		}
		else if(monthDate.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){
			indentation = 3;
		}
		else if(monthDate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
			indentation = 4;
		}
		else if(monthDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
			indentation = 5;
		}
		else if (monthDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			indentation = 6;
		}
		return indentation;
	}
	
	//Fills and sets appointments and label texts to month view
	public void setMonth(){
		ArrayList<Appointment> avtaler = kalender.getAppointments();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			
		Calendar brukKalender = Calendar.getInstance();
		brukKalender.setTime(this.cal.getTime());
		monthLabel.setText(monthString(brukKalender));
		yearMonth.setText(Integer.toString(brukKalender.get(Calendar.YEAR)));
		
		int max = 42;
		int days = brukKalender.getActualMaximum(Calendar.DAY_OF_MONTH);

		int indentTop = calculateIndentation(brukKalender);
		int missingBottom = (max - (days+indentTop));
		
		brukKalender.set(Calendar.DAY_OF_MONTH, 1);
		
		
		for (int day = indentTop; day < monthDays.size(); day++){
			monthDays.get(day).setText(Integer.toString(brukKalender.get(Calendar.DAY_OF_MONTH)));
			monthDays.get(day).setTextFill(Color.BLACK);
			if (brukKalender.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
				monthDays.get(day).setStyle("-fx-background-color:None");
				monthDays.get(day).setOnMouseClicked(null);
			}
			else{
				monthDays.get(day).setStyle("-fx-background-color:#FFB9B9");
				monthDays.get(day).setOnMouseClicked(null);
			}
			for (Appointment avtale: avtaler){
				Date date = avtale.getDate();
				Calendar calDate = Calendar.getInstance();
				calDate.setTime(date);
				
				
				if (calDate.get(Calendar.DAY_OF_YEAR) == brukKalender.get(Calendar.DAY_OF_YEAR)
						&& calDate.get(Calendar.YEAR) == brukKalender.get(Calendar.YEAR)){
					monthDays.get(day).setStyle("-fx-background-color:#33CC33");
					monthDays.get(day).setOnMouseClicked(new EventHandler<MouseEvent>(){
						public void handle(MouseEvent event) {
							System.out.println("You clickeded meh");
							dagsOversikt(event, avtale);
						}
					});
				}
			}
			brukKalender.add(Calendar.DAY_OF_YEAR, +1);
			
			if (day >= (max-missingBottom)){
				monthDays.get(day).setTextFill(Color.GRAY);
			}
		}

		brukKalender.setTime(this.cal.getTime());
		brukKalender.add(Calendar.MONTH, -1);
		brukKalender.set(Calendar.DAY_OF_MONTH, brukKalender.getActualMaximum(Calendar.DAY_OF_MONTH));

		for (int day = indentTop-1; day > -1; day--){
			monthDays.get(day).setText(Integer.toString(brukKalender.get(Calendar.DAY_OF_MONTH)));
			monthDays.get(day).setTextFill(Color.GRAY);
			if (!(day == 0)){
				brukKalender.add(Calendar.DAY_OF_YEAR, -1);
			}
		}
		
		brukKalender.setTime(this.cal.getTime());
		brukKalender.set(Calendar.DAY_OF_MONTH, 1);
		if (indentTop == 7){
			for (int week = 1; week < monthWeeks.size(); week++){
				monthWeeks.get(week).setText(Integer.toString(brukKalender.get(Calendar.WEEK_OF_YEAR)));
				brukKalender.add(Calendar.WEEK_OF_YEAR, 1);
			}
			brukKalender.setTime(this.cal.getTime());
			brukKalender.set(Calendar.DAY_OF_MONTH, 1);
			brukKalender.add(Calendar.DAY_OF_YEAR, -1);
			week1.setText(Integer.toString(brukKalender.get(Calendar.WEEK_OF_YEAR)));
		}
		else{
			for (int week = 0; week < monthWeeks.size(); week++){
				monthWeeks.get(week).setText(Integer.toString(brukKalender.get(Calendar.WEEK_OF_YEAR)));
				brukKalender.add(Calendar.WEEK_OF_YEAR, 1);
			}
		}

	}
	
	//Returns the string corresponding to the month
	public String monthString(Calendar cal){
		int month = cal.get(Calendar.MONTH);
		if (month == Calendar.JANUARY){
			return "Januar";}
		else if (month == Calendar.FEBRUARY){
			return "Februar";}
		else if (month == Calendar.MARCH){
			return "Mars";}
		else if (month == Calendar.APRIL){
			return "April";}
		else if (month == Calendar.MAY){
			return "Mai";}
		else if (month == Calendar.JUNE){
			return "Juni";}
		else if (month == Calendar.JULY){
			return "Juli";}
		else if (month == Calendar.AUGUST){
			return "August";}
		else if (month == Calendar.SEPTEMBER){
			return "September";}
		else if (month == Calendar.OCTOBER){
			return "Oktober";}
		else if (month == Calendar.NOVEMBER){
			return "November";}
		else if (month == Calendar.DECEMBER){
			return "Desember";}
		else{
			return "No int given";
		}
	}
	
	//sets label text to the corresponding day
	public void setDayLabel(Label label, int weekDay){
		tempCal.set(Calendar.DAY_OF_WEEK, weekDay);
		label.setText(Integer.toString(tempCal.get(Calendar.DAY_OF_MONTH)));
	}
	
	//Gets information needed and calls filler
	public void fillCalendar(){

		ArrayList<Appointment> avtaler = kalender.getAppointments();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		for (Appointment avtale: avtaler){
			LocalTime start = LocalTime.parse(sdf.format(avtale.getStart()));
			LocalTime end = LocalTime.parse(sdf.format(avtale.getEnd()));
			String avtaleNavn = avtale.getName();
			
			Date date = avtale.getDate();
			Calendar calDate = Calendar.getInstance();
			calDate.setTime(date);
			
			if (calDate.get(Calendar.WEEK_OF_YEAR) == tempCal.get(Calendar.WEEK_OF_YEAR)
					&& calDate.get(Calendar.YEAR) == tempCal.get(Calendar.YEAR)){
				if (calDate.get(Calendar.DAY_OF_WEEK) == 1){
					filler(timeToGrid(start), avtaleNavn, 7, timeToGrid(end), avtale);
				}
				else{
					filler(timeToGrid(start), avtaleNavn, calDate.get(Calendar.DAY_OF_WEEK)-1, timeToGrid(end), avtale);
				}
			}
		}
	}


	//Adds appointments to the week view grid pane
	public void filler(int startTime, String navn, int weekDay, int endTime, Appointment avtale){
		int span = endTime - startTime;
		if (span<=0 || startTime <=0){
			return;
		}
		StackPane avtalePane = new StackPane();
		String paneString = Integer.toString(startTime) + ":" + Integer.toString(weekDay) + ":" + Integer.toString(tempCal.get(Calendar.WEEK_OF_YEAR));
		System.out.println(paneString + navn);
		int numberOfAppointments = 1;
		
		for (String panes : paneCollection){
			String[] pane = panes.split(":");
			int startTid = Integer.parseInt(pane[0]);
			int ukedag = Integer.parseInt(pane[1]);
			int uke = Integer.parseInt(pane[2]);
			if (startTid == startTime && ukedag == weekDay && uke == cal.get(Calendar.WEEK_OF_YEAR)){
				numberOfAppointments++;
			}
		}
		System.out.println("number of apointmasdf: " + numberOfAppointments);
		avtalePane.setStyle("-fx-background-color:#FE2E2E");
		avtalePane.setMaxWidth(122/numberOfAppointments);
		Label avtaleNavn = new Label(navn);
		avtalePane.getChildren().add(avtaleNavn);
		Appointment appointment = avtale;
		avtalePane.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				System.out.println("You clickeded meh");
				appointmentView(event, appointment);
			}
		});
		paneCollection.add(paneString);
		gridpane.add(avtalePane, weekDay, startTime, 1, span);
	}

	//Converts the inserted time to grid position
	public int timeToGrid(LocalTime time){
		String timeString = time.toString();
		String [] timeSplit = timeString.split(":");
		int timeInt = Integer.parseInt(timeSplit[0])-6;
		return timeInt;
	}

	//Sets the current session?
	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
		innloggetsom.setText("Innlogget som: " + this.sessionUser.getName());
		
		kalender = new MainCalendar();
		System.out.println(this.sessionUser.getId());
		kalender.fillCalendar(this.sessionUser.getId());
		appointmentClick = new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				System.out.println("You clickeded meh");
			}
		};
		
		setWeek();
	}
	public void appointmentView(MouseEvent event, Appointment appointment){
		try{
			Main newMain= new Main();
			newMain.setSession(this.sessionUser);
			newMain.startAvtaleOversikt(new Stage(), appointment);
			 
		}catch (Exception e){
			e.printStackTrace();
		}
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	}
	
	public void dagsOversikt(MouseEvent event, Appointment appointment){
		try{
			Main newMain= new Main();
			newMain.setSession(this.sessionUser);
			newMain.startDagsOversikt(new Stage(), appointment);
			 
		}catch (Exception e){
			e.printStackTrace();
		}
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
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
