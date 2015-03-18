package application;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import classes.Appointment;
import classes.DatabaseCommunicator;
import classes.Group;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
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
	private Label visRomInfo;
	@FXML
	private ComboBox visRom;
	
	@FXML
	private Label feilTittelLabel;
	@FXML
	private Label feilRomLabel;
	@FXML
	private Label feilDatoLabel;
	@FXML
	private Label feilStartSluttLabel;
	@FXML
	private Label feilBeskrivelseLabel;
	@FXML
	private Label feilDeltagerLabel;
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
	
	private Boolean checkpointReached;
	
	
	// start lister
	
	private ArrayList<User> allUsers = new ArrayList<User>();
	private ArrayList<Room> allRooms = new ArrayList<Room>();
	private ArrayList<Group> allGroups = new ArrayList<Group>();
	private ArrayList<User> selectedUsers = new ArrayList<User>();
	private ArrayList<Group> selectedGroups = new ArrayList<Group>();

	///////////////////////////////////////////////////////////////////////////////
	private ObservableList<String> valgtePersoner= FXCollections.observableArrayList(); // denne skal v�re null
	private ObservableList<String> deltagere = FXCollections.observableArrayList(); // Her henter vi inn enkelt-PERSONER fra database - PETTER
	//RANDOM GRUPPER START
	
	private ObservableList<Object> valgteGrupper = FXCollections.observableArrayList(); // denne skal v�re Null
	private ObservableList<Object> grupper = FXCollections.observableArrayList(); // Her henter vi inn grupper fra database - PETTER
	//rANDOM GRUPPER SLUTT
	//MEDLEMMER START
	
	private ObservableList<Object> medlemmer = FXCollections.observableArrayList();
	//MEDLEMMER SLUTT
	

	private ObservableList<Object> valgte = FXCollections.observableArrayList(); // Denne gruppen inneholder(skal sende tilbake) valgte personer/grupper  - PETTER

	//slutt lister
	@FXML
	private void initialize(){
		// Gets all rooms and adds them to the list
		String sqlStatement = "SELECT * FROM ROOM";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		Room newRoom;
		try {
			while (results.next()){
				newRoom = new Room(results.getLong(1) + "", results.getString("name"), results.getString("place"), results.getInt("capacity"));
				
				allRooms.add(newRoom);
				visRom.getItems().add(newRoom.getName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Gets all users and adds them to the list
		
		sqlStatement = "SELECT * FROM USER";
		results = DatabaseCommunicator.execute(sqlStatement);
		User newUser;
		try {
			while (results.next()){
				String name = results.getString(results.findColumn("name"));
				String dbPassword = results.getString(results.findColumn("password"));
				String dbUsername = results.getString(results.findColumn("username"));
				String mail = results.getString(results.findColumn("email"));
				String address = results.getString(results.findColumn("address"));
				Long id = results.getLong(1);
				newUser = new User(dbUsername, dbPassword, mail, name, address, id.toString());
				
				allUsers.add(newUser);
				deltagere.add(newUser.getName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Gets all groups and adds them to the list
		
		sqlStatement = "SELECT * FROM MEMBERGROUP";
		results = DatabaseCommunicator.execute(sqlStatement);
		Group newGroup;
		try {
			while (results.next()){
				String name = results.getString("name");
				Long id = results.getLong(1);
				int leader = results.getInt("leader");
				newGroup = new Group(name,leader + "", id.toString());
				
				allGroups.add(newGroup);
				grupper.add(newGroup.getGroupName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(valgte);
		dato.setValue(LocalDate.now());
		//
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
		
		//feil-labels
		feilTittelLabel.setVisible(false);
		feilRomLabel.setVisible(false);
		feilDatoLabel.setVisible(false);
		feilStartSluttLabel.setVisible(false);
		feilBeskrivelseLabel.setVisible(false);
		feilDeltagerLabel.setVisible(false);
		//feil-labels slutt
		
		checkpointReached = false;
	}
	public Color farger(){
		Color fargekoder = new Color(Color.HSBtoRGB((float) Math.random(), (float) Math.random(), 0.5F + ((float) Math.random())/2F));
		return fargekoder;
		
		// SE HER ALEKSANDER
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
				for (int i = 0; i < allUsers.size(); i++) {
					if (allUsers.get(i).getName().equals(leggtilPerson)){
						selectedUsers.add(allUsers.get(i));
						allUsers.remove(i);
					}
				}
				personListe.getSelectionModel().clearSelection();
				deltagere.remove(leggtilPerson);
				valgtePersoner.add(leggtilPerson.toString());
			}
		}
		else if(listevalg.getText().equals(visGrupper.getText())){
			Object leggtilGruppe = (Object) gruppeListe.getSelectionModel().getSelectedItem();
			if(leggtilGruppe != null){
				for (int i = 0; i < allGroups.size(); i++) {
					if (allGroups.get(i).getGroupName().equals(leggtilGruppe)){
						selectedGroups.add(allGroups.get(i));
						allGroups.remove(i);
					}
				}
				
				gruppeListe.getSelectionModel().clearSelection();
				grupper.remove(leggtilGruppe);
				valgteGrupper.add(leggtilGruppe.toString());	
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
	
	public void visMedlemmer(MouseEvent event){
		System.out.println("herro");
		medlemmer.clear();
		Object visMedlemmerIGruppe = (Object) gruppeListe.getSelectionModel().getSelectedItem();
		if(visMedlemmerIGruppe != null){
			//gruppeListe.getSelectionModel().clearSelection();
			for (Object i : grupper) {
//				medlemmer.addAll(i, visMedlemmerIGruppe);;
				medlemmer.setAll(visMedlemmerIGruppe);
			}
			
//			gruppeListe.getSelectionModel().clearSelection();
//			medlemmer.add(visMedlemmerIGruppe);
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
		
//		Room VarRoom = new Room("10", "Real 56","Realfagsbygget", 5);
//		Calendar c1 = Calendar.getInstance();
//		c1.set(2015, Calendar.MARCH, 18);
//		Appointment newAppointment = new Appointment("M�te","Dette er et testm�te", "Testrom 3", VarRoom, c1.getTime(),Timestamp.valueOf("2015-03-15 18:00:00.0"),Timestamp.valueOf("2015-03-15 20:00:00.0"), sessionUser);
//		//Appointment newAppointment = new Appointment("M�te","Dette er et testm�te", "Testrom 3", VarRoom, new SimpleDateFormat("yyyy-MM-dd").format(new Date(2015, 13, 03)), LocalTime.parse("07:00"),LocalTime.parse("08:00"), sessionUser);
//		newAppointment.saveAppointment(newAppointment);
		
		//Appointment(String name, String desc, String location, Room room, Date date, LocalTime start, LocalTime end, User user)
		
	}
	
	public void velgRom(ActionEvent event){
		visRomInfo.setText((String) visRom.getSelectionModel().getSelectedItem());
	}
	
	public void lagreButt (ActionEvent event) {
		feilTittelLabel.setVisible(false);
		feilRomLabel.setVisible(false);
		feilDatoLabel.setVisible(false);
		feilStartSluttLabel.setVisible(false);
		feilBeskrivelseLabel.setVisible(false);
		feilDeltagerLabel.setVisible(false);
//tittel
		if(tittel.getText().isEmpty()){
			feilTittelLabel.setVisible(true);
		}
//rom
		if(visRomInfo.getText().equals("")){
			feilRomLabel.setVisible(true);
		}
//dato
		if(dato.getValue() != null){
			LocalDate datoValgt = dato.getValue();
			LocalDate datoidag = LocalDate.now();
			int test = datoValgt.compareTo(datoidag);
			if(!(test == 0 || test > 0)){
				System.out.println("NOO");
				feilDatoLabel.setVisible(true);
				feilDatoLabel.setText("M� sette en dato fram i tid");
			}
		}
		else{
			feilDatoLabel.setVisible(true);
			feilDatoLabel.setText("M� velge dato");
		}

//start/slutt
		if((start.getText().matches("[0-2][0-3]:[0-5][0-9]") && !start.getText().isEmpty()) && (slutt.getText().matches("[0-2][0-3]:[0-5][0-9]") && !slutt.getText().isEmpty())){
	
			String startstring = start.getText().replace(":", "");
			String sluttstring = slutt.getText().replace(":", "");
			int startint = Integer.parseInt(startstring);
			int sluttint = Integer.parseInt(sluttstring);
			//System.out.println(startint + " " + sluttint);
			if(!(startint < sluttint)){
				feilStartSluttLabel.setText("Starttid m� v�re etter slutttid.");
				feilStartSluttLabel.setVisible(true);
			}
		}
		else{feilStartSluttLabel.setVisible(true);
			feilStartSluttLabel.setText("Feil input, eks: '10:00' / '11:00'");}
		
//beskrivelse
		if(beskrivelse.getText().isEmpty()){
			feilBeskrivelseLabel.setVisible(true);
		}
		
//deltagere
		if(!valgte.contains(equals(null))){
			checkpointReached = true;
		}
		else{
			feilDeltagerLabel.setVisible(true);
		}
		
//if nirvana reached, save the stuff
		if(!(feilTittelLabel.isVisible()) && !(feilRomLabel.isVisible()) && !(feilDatoLabel.isVisible()) && !(feilStartSluttLabel.isVisible()) && !(feilBeskrivelseLabel.isVisible()) && !(feilDeltagerLabel.isVisible())){
			System.out.println("GODKJENT");
			// DO THE SHIT
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
		else{
			System.out.println("IKKE GODKJENT");
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
