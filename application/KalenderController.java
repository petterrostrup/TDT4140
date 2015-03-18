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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class KalenderController {
	
	private User sessionUser;
	
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
    
    Label[] dayList = new Label[]{month10, month20, month30, month40, month50, month60, month70, month11, month21, month31, month41,
    		month51, month61, month71, month12, month22, month32, month42, month52, month62, month72, month13, month23, month33, month43,
    		month53, month63, month73, month14, month24, month34, month44, month54, month64, month74, month15, month25, month35, month45,
    		month55, month65, month75};
    private ArrayList<Label> monthDays;
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
	private Calendar tempCal;
	ObservableList<Node> avtaleCollection = FXCollections.observableArrayList();
	

	@FXML
	private void initialize(){
//		monthDays.addAll(Arrays.asList(dayList));
		avtaleCollection.addAll(gridpane.getChildren());
		//Creating appointment panes
		datepicker.setValue(LocalDate.now());
		scrollpane.setVisible(true);
		weekHeaderPane.setVisible(true);
		monthPane.setVisible(false);
		monthHeaderPane.setVisible(false);
		setWeek();
		
	}
	
	public void radioUke(ActionEvent event){
		scrollpane.setVisible(true);
		weekHeaderPane.setVisible(true);
		weekHeaderPane.setDisable(false);
		monthPane.setVisible(false);
		monthHeaderPane.setVisible(false);
		monthHeaderPane.setDisable(true);
		
		
		setWeek();
		
	}
	
	public void radioMoned(ActionEvent event){
		monthPane.setVisible(true);
		scrollpane.setVisible(false);
		weekHeaderPane.setVisible(false);
		weekHeaderPane.setDisable(true);
		monthHeaderPane.setVisible(true);
		monthHeaderPane.setDisable(false);
		setMonth();
	}
	
	
	public void nextWeek(ActionEvent event){
		cal.add(Calendar.WEEK_OF_YEAR, +1);
		gridpane.getChildren().clear();
		gridpane.getChildren().addAll(avtaleCollection);
		setWeek();
	}
	
	
	public void lastWeek(ActionEvent event){
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		gridpane.getChildren().clear();
		gridpane.getChildren().addAll(avtaleCollection);
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
	
	public void nextMonth(ActionEvent event){
		cal.add(Calendar.MONTH, +1);
		setMonth();
	}
	
	public void lastMonth(ActionEvent event){
		cal.add(Calendar.MONTH, -1);
		setMonth();
	}
	
	public void setMonth(){
		tempCal = this.cal;
		int days = tempCal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int indentTop;
		int missingBottom;
		int max = 42;
		monthLabel.setText(monthString(tempCal.get(Calendar.MONTH)));
		yearMonth.setText(Integer.toString(tempCal.get(Calendar.YEAR)));
//		
//		if (tempCal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
//			indentTop = 7;
//			missingBottom = max -(indentTop + days);
//			for (int day = 7; day < monthDays.size()-missingBottom; day++){
//				monthDays.get(day).setText(Integer.toString(Calendar.));
//				tempCal.set(Calendar.DAY_OF_MONTH, +1);
//			}
//		}
//		else {
//			
//		}
//		
//		tempCal.set(Calendar.WEEK_OF_MONTH, 1);
//		if (tempCal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
//			setDayLabel(month11,2); setDayLabel(month21,3); setDayLabel(month31,4); setDayLabel(month41,5); setDayLabel(month51,6); setDayLabel(month61,7); setDayLabel(month71,1);
//			tempCal.set(Calendar.WEEK_OF_MONTH, 2);
//			setDayLabel(month12,2); setDayLabel(month22,3); setDayLabel(month32,4); setDayLabel(month42,5); setDayLabel(month52,6); setDayLabel(month62,7); setDayLabel(month72,1);
//			tempCal.set(Calendar.WEEK_OF_MONTH, 3);
//			setDayLabel(month13,2); setDayLabel(month23,3); setDayLabel(month33,4); setDayLabel(month43,5); setDayLabel(month53,6); setDayLabel(month63,7); setDayLabel(month73,1);
//			tempCal.set(Calendar.WEEK_OF_MONTH, 4);
//			setDayLabel(month14,2); setDayLabel(month24,3); setDayLabel(month34,4); setDayLabel(month44,5); setDayLabel(month54,6); setDayLabel(month64,7); setDayLabel(month74,1);
//			tempCal.set(Calendar.WEEK_OF_MONTH, 5);
//			setDayLabel(month15,2); setDayLabel(month25,3); setDayLabel(month35,4); setDayLabel(month45,5); setDayLabel(month55,6); setDayLabel(month65,7); setDayLabel(month75,1);
//			tempCal.set(Calendar.WEEK_OF_MONTH, 1);
//			setDayLabel(month10,2); setDayLabel(month20,3); setDayLabel(month30,4); setDayLabel(month40,5); setDayLabel(month50,6); setDayLabel(month60,7); setDayLabel(month70,1);
//			
//		}
	}
	
//	public void monthDayName(){
//		String dayName;
//		int day = 1;
//		for (int i = 0; i < 6; i++){
//			for (int j = 1; j<8; j++){
//				dayName = "month"+j+i;
//				dayName.setText(Integer.toString(tempCal.get(Calendar.DAY_OF_WEEK_IN_MONTH)));
//			}
//		}
//	}
	public String monthString(int month){
		if (month == 1){
			return "Januar";}
		else if (month == 2){
			return "Februar";}
		else if (month == 3){
			return "Mars";}
		else if (month == 4){
			return "April";}
		else if (month == 5){
			return "Mai";}
		else if (month == 6){
			return "Juni";}
		else if (month == 7){
			return "Juli";}
		else if (month == 8){
			return "August";}
		else if (month == 9){
			return "September";}
		else if (month == 10){
			return "Oktober";}
		else if (month == 11){
			return "November";}
		else if (month == 12){
			return "Desember";}
		else{
			return "No int given";
		}
		
	}
	
	public void setDayLabel(Label label, int weekDay){
		tempCal.set(Calendar.DAY_OF_WEEK, weekDay);
		label.setText(Integer.toString(tempCal.get(Calendar.DAY_OF_MONTH)));
	}
	
	
	public void fillCalendar(){
		MainCalendar kalender = new MainCalendar();
		kalender.fillTest();
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
					filler(timeToGrid(start), avtaleNavn, 7, timeToGrid(end));
				}
				else{
					filler(timeToGrid(start), avtaleNavn, calDate.get(Calendar.DAY_OF_WEEK)-1, timeToGrid(end));
				}
			}
		}
	}
	

	public void filler(int startTime, String navn, int weekDay, int endTime){
		Pane avtalePane = new Pane();
		int span = endTime - startTime;
		avtalePane.setStyle("-fx-background-color:#FE2E2E");
		avtalePane.setPrefSize(122, 60);
		Label avtaleNavn = new Label(navn);
		avtalePane.getChildren().add(avtaleNavn);
		gridpane.add(avtalePane, weekDay, startTime, 1, span);
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
