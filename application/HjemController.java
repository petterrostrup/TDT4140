package application;

import java.time.LocalTime;
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
import javafx.stage.Stage;

public class HjemController extends Application {
	
	@FXML
	private GridPane gridpane;
	
	//@FXML
	
	

	@Override
	public void start(Stage stage) throws Exception {
	       Parent root = FXMLLoader.load(getClass().getResource("hjem.fxml"));
	       
	        Scene scene = new Scene(root);
	        
	        stage.setTitle("Hjem");
	        stage.setScene(scene);
	        stage.show();
	        
	        Calendar kalender = new Calendar();
	        
	        GridPane nyAvtale = new GridPane();
	        Room testRoom = new Room("245", "somewhere", 10);
	        Appointment appointment1 = new Appointment("Gruppem�te", "Vanlig m�te", "Bygg-1", testRoom,new Date(2015, 03, 02),LocalTime.parse("16:00"),LocalTime.parse("17:30")); 
	        
	        
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
			//Henter stage parameter
//			Node  source = (Node)  event.getSource(); 
//		    Stage stage  = (Stage) source.getScene().getWindow();
//		    stage.close();
		    
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
				public void brukerButt (ActionEvent event) {
					try {
						
						new RedigerBrukerController().start(new Stage());
					} catch (Exception e) {
						e.printStackTrace();
					}
					//Henter stage parameter
//					Node  source = (Node)  event.getSource(); 
//				    Stage stage  = (Stage) source.getScene().getWindow();
//				    stage.close();
				    
				}
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
