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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class KalenderController extends Application {
	
	@FXML
	private GridPane gridpane;
//	
	//@FXML
	
	

	@Override
	public void start(Stage stage) throws Exception {
	       Parent root = FXMLLoader.load(getClass().getResource("kalender.fxml"));
	       
	        Scene scene = new Scene(root);
	        
	        stage.setTitle("Hjem");
	        stage.setScene(scene);
	        stage.show();
	        
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
	        	
	        	gridpane.add(new Pane(),startInt, endInt );
	        }
	        
	        
	        
	        
//	        Calendar calendar = new Calendar();
//	        
//	        GridPane nyAvtale = new GridPane();
//	        Room testRoom = new Room("245", "somewhere", 10);
//	        Appointment appointment1 = new Appointment("Gruppemøte", "Vanlig møte", "Bygg-1", testRoom,new Date(2015, 03, 02),LocalTime.parse("16:00"),LocalTime.parse("17:30")); 
//	        private  = kalender.getAppointments();
//	        kalender.fillTest();
//	        
	        
	        //add to appointmentSSS, write in gridpane(label, 1, 1)
	        
	        
	}
	
	
//	//Bytter vindu til hjem
//	public void hjemButt (ActionEvent event) {
//		try {
//			new HjemController().start(new Stage());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		//Henter stage parameter
//		Node  source = (Node)  event.getSource(); 
//	    Stage stage  = (Stage) source.getScene().getWindow();
//	    stage.close();
//	}
	
	
	//Bytter vindu til LagAvtale
		public void avtaleButt (ActionEvent event) {
			try {
				new LagAvtaleController().start(new Stage());
			} catch (Exception e) {
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
					} catch (Exception e) {
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
					} catch (Exception e) {
						e.printStackTrace();
					}
					//Henter stage parameter
					Node  source = (Node)  event.getSource(); 
				    Stage stage  = (Stage) source.getScene().getWindow();
				    stage.close();
				    
				}
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
