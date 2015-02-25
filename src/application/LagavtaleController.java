package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LagavtaleController extends Application {
	
	@FXML
	private TextField tittel;  
	
	@FXML
	private SplitMenuButton rom;
	
	@FXML
	private SplitMenuButton deltagere;
	
	@FXML
	private DatePicker dato;
	
	@FXML
	private TextField start;
	
	@FXML
	private TextField slutt;
	
	@FXML
	private TextField beskrivelse;
	
	@FXML
	private Button lagreavtale;
	

	@Override
	public void start(Stage stage) throws Exception {
	       Parent root = FXMLLoader.load(getClass().getResource("lagavtale.fxml"));
	       
	        Scene scene = new Scene(root);
	        
	        stage.setTitle("Lage avtale");
	        stage.setScene(scene);
	        stage.show();
	        
	}

	public static void main(String[] args) {
		launch(args);
	}
}
