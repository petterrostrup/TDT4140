package application;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import classes.Appointment;
import classes.Room;
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
	private Label valgtePersonerText;
	@FXML
	private Label valgteGrupperText;
	@FXML
	private Label medlemmerText;
	@FXML
	private MenuButton listevalg;
	@FXML
	private MenuItem visPersoner;
	@FXML
	private MenuItem visGrupper;
	@FXML 
	private ListView valgtePersonerList;
	@FXML
	private ListView valgteGrupperList;
	@FXML 
	private ListView personListe;
	@FXML
	private ListView gruppeListe;
	@FXML
	private ListView gruppeMedlemmerList;
	@FXML
	private Button toDeltar;
	@FXML
	private Button toCandidates;
	@FXML
	private Button leggtilmedlem;
	
	// start lister

	///////////////////////////////////////////////////////////////////////////////
	private ObservableList<String> valgtePersoner= FXCollections.observableArrayList(); // denne skal v�re null
	private ObservableList<String> deltagere = FXCollections.observableArrayList("Petter", "Kristian", "Fredrik", "Aleksander", "Emil"); // Her henter vi inn enkelt-PERSONER fra database - PETTER
	//RANDOM GRUPPER START
	//private ObservableList<String> PettersGruppe = FXCollections.observableArrayList("Petters Bitches", "Aleksander", "Everbody");
	private ObservableList<String> KristiansGruppe = FXCollections.observableArrayList("Aleksander", "Fredrik", "Emil", "Petter");
	
	private ObservableList<Object> valgteGrupper = FXCollections.observableArrayList(); // denne skal v�re Null
	private ObservableList<Object> grupper = FXCollections.observableArrayList("testGruppe1", "testGruppe2", KristiansGruppe, FXCollections.observableArrayList("a", "b", "c")); // Her henter vi inn grupper fra database - PETTER
	//rANDOM GRUPPER SLUTT
	//MEDLEMMER START
	
	private ObservableList<Object> medlemmer = FXCollections.observableArrayList();
	//MEDLEMMER SLUTT
	
//	private ObservableList<Object> testList = FXCollections.observableArrayList();
	
	
	private ObservableList<Object> valgte = FXCollections.observableArrayList(valgtePersoner, grupper/*(hent gruppemedlemmer fra gruppene i listen "grupper*/); // Denne gruppen inneholder(skal sende tilbake) valgte personer/grupper  - PETTER

	//slutt lister
	@FXML
	private void initialize(){
		personListe.setItems(deltagere);
		gruppeListe.setItems(grupper);
		gruppeMedlemmerList.setItems(medlemmer);
		
		valgtePersonerList.setVisible(false);
		valgteGrupperList.setVisible(false);
		personListe.setVisible(false);
		gruppeListe.setVisible(false);
		toDeltar.setVisible(false);
		toCandidates.setVisible(false);
		gruppeMedlemmerList.setVisible(false);
		medlemmerText.setVisible(false);
		leggtilmedlem.setVisible(false);
		valgtePersonerText.setVisible(false);
		valgteGrupperText.setVisible(false);
		
		//initialiserer med en gang siden loades
		
		
	}
	
	public void visPersonerList(ActionEvent event){

		valgtePersonerList.setItems(valgtePersoner);
		valgteGrupperList.setItems(valgteGrupper);
		
		
		
		listevalg.setText(visPersoner.getText());
		text1.setText("Valgt");
		text2.setText("Personer");

		valgtePersonerList.setVisible(true);
		valgteGrupperList.setVisible(true);
		personListe.setVisible(true);
		gruppeListe.setVisible(false);
		toDeltar.setVisible(true);
		toCandidates.setVisible(true);
		gruppeMedlemmerList.setVisible(false);
		medlemmerText.setVisible(false);
		leggtilmedlem.setVisible(false);
		valgtePersonerText.setVisible(true);
		valgteGrupperText.setVisible(true);
	}
	
	public void visGrupperList(ActionEvent event){
		valgtePersonerList.setItems(valgtePersoner);
		valgteGrupperList.setItems(valgteGrupper);
		
		listevalg.setText(visGrupper.getText());
		text1.setText("Valgt");
		text2.setText("Grupper");
		
		valgtePersonerList.setVisible(true);
		valgteGrupperList.setVisible(true);
		personListe.setVisible(false);
		gruppeListe.setVisible(true);
		toDeltar.setVisible(true);
		toCandidates.setVisible(true);
		gruppeMedlemmerList.setVisible(true);
		medlemmerText.setVisible(true);
		leggtilmedlem.setVisible(true);
		valgtePersonerText.setVisible(true);
		valgteGrupperText.setVisible(true);
	}

	public void sendRight(ActionEvent event){
		Object fjernPerson = (Object) valgtePersonerList.getSelectionModel().getSelectedItem();
		if(fjernPerson != null){
			valgtePersonerList.getSelectionModel().clearSelection();
			valgtePersoner.remove(fjernPerson);
			deltagere.add((String) fjernPerson);
		}
		Object fjernGruppe = (Object) valgteGrupperList.getSelectionModel().getSelectedItem();
		if(fjernGruppe != null){
			valgteGrupperList.getSelectionModel().clearSelection();
			valgteGrupper.remove(fjernGruppe);
			grupper.add(fjernGruppe);
		}
		
//		if(listevalg.getText().equals(visPersoner.getText())){
//			Object fjernPerson = (Object) valgtePersonerList.getSelectionModel().getSelectedItem();
//			if(fjernPerson != null){
//				valgtePersonerList.getSelectionModel().clearSelection();
//				valgtePersoner.remove(fjernPerson);
//				deltagere.add((String) fjernPerson);
//			}
//		}
//		else if(listevalg.getText().equals(visGrupper.getText())){
//			Object fjernGruppe = (Object) valgteGrupperList.getSelectionModel().getSelectedItem();
//			if(fjernGruppe != null){
//				valgteGrupperList.getSelectionModel().clearSelection();
//				valgteGrupper.remove(fjernGruppe);
//				grupper.add(fjernGruppe);
//			}
//		}
	}
		
	public void sendLeft (ActionEvent event){
		if(listevalg.getText().equals(visPersoner.getText())){
			Object leggtilPerson = (Object) personListe.getSelectionModel().getSelectedItem();
			if(leggtilPerson != null){
				personListe.getSelectionModel().clearSelection();
				deltagere.remove(leggtilPerson);
				valgtePersoner.add((String) leggtilPerson);
			}
		}
		else if(listevalg.getText().equals(visGrupper.getText())){
			Object leggtilGruppe = (Object) gruppeListe.getSelectionModel().getSelectedItem();
			if(leggtilGruppe != null){
				gruppeListe.getSelectionModel().clearSelection();
				grupper.remove(leggtilGruppe);
				valgteGrupper.add((Object) leggtilGruppe);	
			}
		}
		
//		Object leggtil = (Object) personListe.getSelectionModel().getSelectedItem();
//		if(leggtil != null){
//			personListe.getSelectionModel().clearSelection();	
//			if(listevalg.getText().equals(visPersoner.getText())){
//				deltagere.remove(leggtil);
//				valgtePersoner.add((String) leggtil);
//			}
//			else if(listevalg.getText().equals(visGrupper.getText())){
//				grupper.remove(leggtil);
//				valgteGrupper.add((String) leggtil);
//			}
//		}
	}
	
	public void addGruppeMedlem(ActionEvent event){
		Object leggtilMedlem = (Object) gruppeMedlemmerList.getSelectionModel().getSelectedItem();
		if(leggtilMedlem != null){
			gruppeMedlemmerList.getSelectionModel().clearSelection();
			
			medlemmer.remove(leggtilMedlem);
			valgtePersoner.add((String) leggtilMedlem);
		}
	}
	
	public void visMedlemmer(ActionEvent event){
		Object visMedlemmer = (Object) gruppeListe.getSelectionModel().getSelectedItem();
		if(visMedlemmer != null){
			//valgtePersoner.remove(visMedlemmer);
			medlemmer.add(visMedlemmer);
		}
	}
	public void visMedlemmer2(ActionEvent event){
		
		 ((MenuButton) grupper).getItems().removeAll(new ArrayList<Object>(gruppeMedlemmerList.getSelectionModel().getSelectedItems()));
//		ArrayList visMedlemmer = gruppeListe.getSelectionModel().getSelectedItem();
		
		ArrayList<Object> medlemmerIgruppe = new ArrayList<Object>();
		
		if(medlemmer != null){
			medlemmer.addAll(grupper);
			//valgtePersoner.remove(visMedlemmer);
//			for (int i = 0; i < medlemmer.size(); i++) {
//				medlemmer..add(i);
//			}
//			medlemmer.add(visMedlemmer);
		}
	}
	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
		innloggetsom.setText("Innlogget som: " + this.sessionUser.getName());
		
		
		//LagAvtale test
		
//		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		Date date = df.parse(date);
		
		
		//String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				//(request.getParameter("date")));
		
		Room VarRoom = new Room("Real 56","Realfagsbygget", 5);
		Appointment newAppointment = new Appointment("M�te","Dette er et testm�te", "Testrom 3", VarRoom, new Date(2015, 13, 03), LocalTime.parse("07:00"),LocalTime.parse("08:00"), sessionUser);
		//Appointment newAppointment = new Appointment("M�te","Dette er et testm�te", "Testrom 3", VarRoom, new SimpleDateFormat("yyyy-MM-dd").format(new Date(2015, 13, 03)), LocalTime.parse("07:00"),LocalTime.parse("08:00"), sessionUser);
		newAppointment.saveAppointment(newAppointment);
		
		//Appointment(String name, String desc, String location, Room room, Date date, LocalTime start, LocalTime end, User user)
		
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
