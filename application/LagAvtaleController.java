package application;

import classes.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LagAvtaleController {
	
	private User sessionUser;
	
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
	
	@FXML
	private Label innloggetsom;
	
	@FXML
	private MenuButton listevalg;
	
	
	
	@FXML 
	private ListView deltarList;
	@FXML 
	private ListView deltagereList;

	@FXML
	private Button toDeltar;
	@FXML
	private Button toCandidates;
	@FXML
	ObservableList<String> names= FXCollections.observableArrayList("Petter", "Kristian", "Fredrik", "Aleksander", "Emil");
	
	
	@FXML
	ObservableList<String> selected = FXCollections.observableArrayList();
	
	@FXML
	private void initialize(){
		
		//initialiserer med en gang siden loades
		
		
		
	}
	public void velgListe2(ActionEvent event){

		
		deltarList.setItems(names);
		deltagereList.setItems(selected);
		
		//initialiserer når button (skal være liste) velges
	}
	public void sendRight(ActionEvent event){
		
		String potential = (String) deltarList.getSelectionModel().getSelectedItem();
		if(potential != null){
			deltarList.getSelectionModel().clearSelection();
			names.remove(potential);
			selected.add(potential);
		}
	}
	public void sendLeft (ActionEvent event){
		String s = (String) deltagereList.getSelectionModel().getSelectedItem();
		if(s != null){
			deltagereList.getSelectionModel().clearSelection();
			selected.remove(s);
			names.add(s);
		}
	}
	

	
	public void velgListe (ActionEvent event){
		System.out.println("HERROERROR LOL");
		
	}
	
	public void setSession(User sessionUser){
		this.sessionUser = sessionUser;
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
				Main newMain = new Main();
				newMain.setSession(this.sessionUser);
				newMain.startKalender(new Stage());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			//Henter stage parameter
			Node  source = (Node)  event.getSource(); 
		    Stage stage  = (Stage) source.getScene().getWindow();
		    stage.close();
		}
	
	}
	
	public void kalenderButt (ActionEvent event){
		try {
			Main newMain = new Main();
			newMain.setSession(this.sessionUser);
			newMain.startKalender(new Stage());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//Henter stage parameter
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
	}
	
	public void profilButt (ActionEvent event){
		try {
			Main newMain = new Main();
			newMain.setSession(this.sessionUser);
			newMain.startProfil(new Stage());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//Henter stage parameter
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
	}
	
	public void logoutButt (ActionEvent event){
		try {
			new Main().start(new Stage());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//Henter stage parameter
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();

	}
	

	
}
