package application;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import classes.Appointment;
import classes.Calendar;
import classes.Room;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class KalenderController {
	
	@FXML
	private GridPane gridpane;

	
	@FXML
	private void initialize(){
	        
		//Creating appointment panes
	        
		Calendar kalender = new Calendar();
		kalender.fillTest();
		ArrayList<Appointment> avtaler = kalender.getAppointments();
		for (Appointment avtale: avtaler){
			LocalTime start = avtale.getStart();
			LocalTime end = avtale.getEnd();
	        	
			String startString = start.toString();
			String [] startSplit = startString.split(":");
			int startInt = Integer.parseInt(startSplit[0]);
	        	
			String endString = end.toString();
			String [] endSplit = endString.split(":");
			int endInt = Integer.parseInt(endSplit[0]);
		}
		filler();
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
	
	
	public void filler(){
		Button avtalePane = new Button();
		gridpane.add(avtalePane, 1, 1);					
	}


}
