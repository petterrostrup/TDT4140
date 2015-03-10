package application;

import java.util.ArrayList;

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
import javafx.scene.control.MenuItem;
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
	private Label text1;
	@FXML
	private Label text2;
	@FXML
	private Label medlemmerText;
	@FXML
	private MenuButton listevalg;
	@FXML
	private MenuItem visPersoner;
	@FXML
	private MenuItem visGrupper;
	@FXML 
	private ListView deltarList;
	@FXML 
	private ListView deltagereList;
	@FXML
	private ListView gruppeMedlemmer;
	@FXML
	private Button toDeltar;
	@FXML
	private Button toCandidates;
	// start lister
	private ObservableList<String> valgtePersoner= FXCollections.observableArrayList(); // denne skal være null
	private ObservableList<String> deltagere = FXCollections.observableArrayList("Petter", "Kristian", "Fredrik", "Aleksander", "Emil");
	
	private ObservableList<String> PettersBitches = FXCollections.observableArrayList("Petters Bitches", "Aleksander", "Everbody");
	private ObservableList<Object> valgteGrupper = FXCollections.observableArrayList(PettersBitches.get(0), "testGruppe3"); // denne skal være Null
	
	
	private ObservableList<String> KristiansGruppe = FXCollections.observableArrayList("Kristians Bitches", "Fredrik", "Emil");
	private ObservableList<Object> grupper = FXCollections.observableArrayList("testGruppe1", "testGruppe2", KristiansGruppe.get(0));
	
	//listene over skal hente inn info fra database, stående verdier i listene skal FJERNES
	
	@FXML
	private void initialize(){
		
		deltarList.setVisible(false);
		deltagereList.setVisible(false);
		toDeltar.setVisible(false);
		toCandidates.setVisible(false);
		gruppeMedlemmer.setVisible(false);
		medlemmerText.setVisible(false);
		
		//initialiserer med en gang siden loades
	}
	
	public void visPersonerList(ActionEvent event){
		deltarList.setItems(valgtePersoner);
		deltagereList.setItems(deltagere);
		
		
		listevalg.setText(visPersoner.getText());
		text1.setText("Valgt");
		text2.setText("Personer");

		deltarList.setVisible(true);
		deltagereList.setVisible(true);
		toDeltar.setVisible(true);
		toCandidates.setVisible(true);
	}
	public void visGrupperList(ActionEvent event){
		deltarList.setItems(valgteGrupper);
		deltagereList.setItems(grupper);
		
		listevalg.setText(visGrupper.getText());
		text1.setText("Valgt");
		text2.setText("Grupper");
		
		deltarList.setVisible(true);
		deltagereList.setVisible(true);
		toDeltar.setVisible(true);
		toCandidates.setVisible(true);
		gruppeMedlemmer.setVisible(true);
		medlemmerText.setVisible(true);
	}

	public void sendRight(ActionEvent event){
		
		Object fjern = (Object) deltarList.getSelectionModel().getSelectedItem();
		if(fjern != null){
			deltarList.getSelectionModel().clearSelection();
			
			if(listevalg.getText().equals(visPersoner.getText())){
				valgtePersoner.remove(fjern);
				deltagere.add((String) fjern);
			}
			else if(listevalg.getText().equals(visGrupper.getText())){
				valgteGrupper.remove(fjern);
				grupper.add(fjern);
			}
		}
	}
	
	public void sendLeft (ActionEvent event){
		Object leggtil = (Object) deltagereList.getSelectionModel().getSelectedItem();
//		String leggtil2 = (String) 
		if(leggtil != null){
			deltagereList.getSelectionModel().clearSelection();
			
			if(listevalg.getText().equals(visPersoner.getText())){
				deltagere.remove(leggtil);
				valgtePersoner.add((String) leggtil);
			}
			else if(listevalg.getText().equals(visGrupper.getText())){
				grupper.remove(leggtil);
				valgteGrupper.add(leggtil);
			}
			
		}
	}// PRInT TIL MEDLEMTINGEN NEXT BOI
	
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
