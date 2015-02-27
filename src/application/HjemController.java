package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HjemController extends Application {
	
	//@FXML

	@Override
	public void start(Stage stage) throws Exception {
	       Parent root = FXMLLoader.load(getClass().getResource("hjem.fxml"));
	       
	        Scene scene = new Scene(root);
	        
	        stage.setTitle("Hjem");
	        stage.setScene(scene);
	        stage.show();
	}
	
	
	//Bytter vindu til LagAvtale
		public void avtaleButt (ActionEvent event) {
			try {
				//(Bytt ut med registreringsskjerm når den er laget)
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
					Node  source = (Node)  event.getSource(); 
				    Stage stage  = (Stage) source.getScene().getWindow();
				    stage.close();
				    
				}
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
