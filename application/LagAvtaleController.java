package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LagAvtaleController extends Application {
	
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
	private TextArea beskrivelse;
	
	@FXML
	private Button lagreavtale;
	
	@FXML
	private GridPane gridpane;
	
	@FXML
	private Label label1;

	@Override
	public void start(Stage stage) throws Exception {
	       Parent root = FXMLLoader.load(getClass().getResource("lagavtale.fxml"));
	       
	        Scene scene = new Scene(root);
	        
	        stage.setTitle("Lage avtale");
	        stage.setScene(scene);
	        stage.show();
	        
	        
	        
	        
	}
	
	
	public void lagreButt (ActionEvent event) {
		System.out.println("test");
		//Lagre data fra skjema i database
		Boolean checkpointReached = true;
		
		Button lagreButt = new Button("Lagre");
		lagreButt.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				if ((tittel.getText() != null && !tittel.getText().isEmpty())) {
					label1.setText(tittel.getText()); //utvides og kobles til korrekt LABEL/BUTTON(kalenderelement)
				}
				else{
					label1.setText("Fyll in det feltet"); //kobles med feilmeldingslabels
				}
			}
			
		});
		// hvis validering er godkjent, send til hjem
		if(checkpointReached){
			try {
				new HjemController().start(new Stage());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			//Henter stage parameter
			Node  source = (Node)  event.getSource(); 
		    Stage stage  = (Stage) source.getScene().getWindow();
		    stage.close();
		}
	
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
