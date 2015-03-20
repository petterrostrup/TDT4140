package application;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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

public class RedigerAvtaleController {
	private Stage primaryStage;
	private User sessionUser;
	private Appointment currentAppointment;
	

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
	
	private Appointment saveAppointment;
	int startint;
	int sluttint;
	String startstring;
	String sluttstring;
	
	
	// start lister
	
	private ArrayList<User> allUsers = new ArrayList<User>();
	private ArrayList<Room> allRooms = new ArrayList<Room>();
	private ArrayList<Group> allGroups = new ArrayList<Group>();
	private ArrayList<User> selectedUsers = new ArrayList<User>();
	private ArrayList<Group> selectedGroups = new ArrayList<Group>();
	private ArrayList<User> groupMembers = new ArrayList<User>();
	private ArrayList<User> saveUsers = new ArrayList<User>();
	
	///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	private ObservableList<String> valgtePersoner= FXCollections.observableArrayList(); // denne skal være null
	private ObservableList<String> deltagere = FXCollections.observableArrayList(); // Her henter vi inn enkelt-PERSONER fra database - PETTER
	//RANDOM GRUPPER START
	
	private ObservableList<Object> valgteGrupper = FXCollections.observableArrayList(); // denne skal være Null
	private ObservableList<Object> grupper = FXCollections.observableArrayList(); // Her henter vi inn grupper fra database - PETTER
	//rANDOM GRUPPER SLUTT
	//MEDLEMMER START
	
	private ObservableList<Object> medlemmer = FXCollections.observableArrayList();
	//MEDLEMMER SLUTT
	

	private ObservableList<Object> valgte = FXCollections.observableArrayList();
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
		

	}
//	public Color farger(){
//		Color fargekoder = new Color(Color.HSBtoRGB((float) Math.random(), (float) Math.random(), 0.5F + ((float) Math.random())/2F));
//		return fargekoder;
//		
//		// SE HER ALEKSANDER
//	}
	
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
			for (int i = 0; i < selectedUsers.size(); i++) {
				if (selectedUsers.get(i).getName().equals(fjernPerson)){
					if (!allUsers.contains(selectedUsers.get(i))){
						allUsers.add(selectedUsers.get(i));						
					}
					selectedUsers.remove(i);
				}
			}
			valgtePersonerList.getSelectionModel().clearSelection();
			valgtePersoner.remove(fjernPerson);
			if (!deltagere.contains(fjernPerson.toString())){
				deltagere.add(fjernPerson.toString());				
			}
		}
		Object fjernGruppe = (Object) valgteGrupperList.getSelectionModel().getSelectedItem();
		if(fjernGruppe != null){
			for (int i = 0; i < selectedGroups.size(); i++) {
				if (selectedGroups.get(i).getGroupName().equals(fjernGruppe.toString())){
					allGroups.add(selectedGroups.get(i));
					selectedGroups.remove(i);
				}
			}
			
			valgteGrupperList.getSelectionModel().clearSelection();
			valgteGrupper.remove(fjernGruppe);
			grupper.add(fjernGruppe);
		}
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
					if (allGroups.get(i).getGroupName().equals(leggtilGruppe.toString())){
						selectedGroups.add(allGroups.get(i));
						allGroups.remove(i);
					}
				}
				
				gruppeListe.getSelectionModel().clearSelection();
				grupper.remove(leggtilGruppe);
				valgteGrupper.add(leggtilGruppe.toString());	
			}
		}
	}
	
	public void addGruppeMedlem(ActionEvent event){
		Object leggtilMedlem = (Object) gruppeMedlemmerList.getSelectionModel().getSelectedItem();
		if(leggtilMedlem != null){
			for (int i = 0; i < groupMembers.size(); i++) {
				if (groupMembers.get(i).getName().equals(leggtilMedlem)){
					selectedUsers.add(groupMembers.get(i));
					groupMembers.remove(i);
				}
			}
			gruppeMedlemmerList.getSelectionModel().clearSelection();
			medlemmer.remove(leggtilMedlem);
			valgtePersoner.add(leggtilMedlem.toString());
		}
	}
	
	public void visMedlemmer(MouseEvent event){
		groupMembers.clear();
		medlemmer.clear();
		Object visMedlemmerIGruppe = (Object) gruppeListe.getSelectionModel().getSelectedItem();
		if(visMedlemmerIGruppe != null){
			//gruppeListe.getSelectionModel().clearSelection();
			for (int i = 0; i < allGroups.size(); i++) {
				if (allGroups.get(i).getGroupName().equals(visMedlemmerIGruppe.toString())){
					String sqlStatement = "SELECT * FROM MEMBER WHERE membergroup = '" + allGroups.get(i).getGroupID() + "'";
					ResultSet results = DatabaseCommunicator.execute(sqlStatement);
					User newUser;
					try {
						while (results.next()){
							newUser = User.readUser(results.getLong(2));
							groupMembers.add(newUser);
							medlemmer.add(newUser.getName());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

	}
	
	public void setSession(User sessionUser, Appointment sessionAppointment){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
		this.currentAppointment = sessionAppointment;
		currentAppointment.readParticipants();

		ArrayList<User> newUsers = currentAppointment.getParticipants();
		
		User addingUser;
		for (int i = 0; i < newUsers.size(); i++) {
			addingUser = newUsers.get(i);
			selectedUsers.add(addingUser);
			valgtePersoner.add(addingUser.getName());
		}
		
		valgtePersonerList.setItems(valgtePersoner);
		personListe.setItems(deltagere);
		
		tittel.setText(currentAppointment.getName());
		
		Date input = currentAppointment.getDate();
		Instant instant = Instant.ofEpochMilli(input.getTime());
		LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		
		dato.setValue(res);
		
		SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm");
		
		start.setText(sdfTime.format(currentAppointment.getStart()));
		slutt.setText(sdfTime.format(currentAppointment.getEnd()));
		beskrivelse.setText(currentAppointment.getDescription());
		visRom.setValue(currentAppointment.getLocation());
		visRomInfo.setText(currentAppointment.getLocation());
		
		
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
				feilDatoLabel.setText("Må sette en dato fram i tid");
			}
		}
		else{
			feilDatoLabel.setVisible(true);
			feilDatoLabel.setText("Må velge dato");
		}
//tidsvalidering
		if(((start.getText().matches("[0-2][0-3]:[0-5][0-9]") && !start.getText().isEmpty()) || 
				((start.getText().matches("[0-1][0-9]:[0-5][0-9]") && !start.getText().isEmpty())))
				&& ((slutt.getText().matches("[0-2][0-3]:[0-5][0-9]") && !slutt.getText().isEmpty()) ||
				((slutt.getText().matches("[0-1][0-9]:[0-5][0-9]") && !slutt.getText().isEmpty())))){
			
			startstring = start.getText();
			sluttstring = slutt.getText();
			
			if(startstring.matches("[0][0-5]:[0-5][1-9]") || sluttstring.matches("[0][0-5]:[0-5][1-9]")){
				feilStartSluttLabel.setText("Kan ikke lage avtaler mellom 00:00 /06:00");
				feilStartSluttLabel.setVisible(true);
			}
			if(startstring.matches("00:00")){
				feilStartSluttLabel.setText("Kan ikke lage avtaler mellom 00:00 /06:00");
				feilStartSluttLabel.setVisible(true);
			}
			if(sluttstring.equals("00:00")){
				sluttstring = "23:59";
			}
			if(startstring.startsWith("0")|| sluttstring.startsWith("0")){
				start.getText().replace("0", "");
				slutt.getText().replace("0", "");
			}
			startint = Integer.parseInt(startstring.replace(":", ""));
			sluttint = Integer.parseInt(sluttstring.replace(":", ""));
			System.out.println(startint + " " + sluttint);
			
			if(sluttint - startint < 100){
				feilStartSluttLabel.setText("Avtalen må være minst 1 time lang");
				feilStartSluttLabel.setVisible(true);
			}
			if(!(startint < sluttint)){
				feilStartSluttLabel.setText("Starttid må være før slutttid.");
				feilStartSluttLabel.setVisible(true);
			}
		
			System.out.println(startstring + " " + sluttstring);
			
		}
		else{feilStartSluttLabel.setVisible(true);
			feilStartSluttLabel.setText("Feil input, eks: '10:00' / '11:00'");}
		
//beskrivelse
		if(beskrivelse.getText().isEmpty()){
			feilBeskrivelseLabel.setVisible(true);
		}
		
//deltagere
		if(selectedUsers.isEmpty() && selectedGroups.isEmpty()){ 
			feilDeltagerLabel.setVisible(true);
		}
		
//if nirvana reached, save the stuff
		if(!(feilTittelLabel.isVisible()) && !(feilRomLabel.isVisible()) && !(feilDatoLabel.isVisible()) && !(feilStartSluttLabel.isVisible()) && !(feilBeskrivelseLabel.isVisible()) && !(feilDeltagerLabel.isVisible())){
			System.out.println("GODKJENT");
			// DO THE SHIT
			try{
				saveUsers.addAll(selectedUsers);
				for (int i = 0; i < selectedGroups.size(); i++) {
					String sqlStatement = "SELECT * FROM MEMBER WHERE membergroup = '" + selectedGroups.get(i).getGroupID() + "'";
					ResultSet results = DatabaseCommunicator.execute(sqlStatement);
					User newUser;
					try {
						while (results.next()){
							newUser = User.readUser(results.getLong(2));
							if (!saveUsers.contains(newUser)){
								saveUsers.add(newUser);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				String sqlStatement = "SELECT * FROM ROOM WHERE name = '" + visRomInfo.getText() + "'";
				ResultSet results = DatabaseCommunicator.execute(sqlStatement);
				Room newRoom = null;
				try {
					if (results.next()){
						String id = (results.getLong(1) + "");
						String name = (results.getString(results.findColumn("name")));
						String place = (results.getString(results.findColumn("place")));
						int capacity = (results.getInt(results.findColumn("capacity")));
						newRoom = new Room(id, name, place, capacity);
					}
					else{
						System.out.println("Room does not exist. Cannot read");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Something went wrong connecting to the database");
				}
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SS");
				String name = tittel.getText();
				String location = visRomInfo.getText();
				String description = beskrivelse.getText();
				LocalDate date = dato.getValue();
				Date finalDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
				
				String startformat = date.toString() + " " + startstring + ":00.00";
				Date parsedDate = dateFormat.parse(startformat);
				Timestamp startTime = new Timestamp(parsedDate.getTime());
				
				String endformat = date.toString() + " " + sluttstring + ":00.00";
				parsedDate = dateFormat.parse(endformat);
				Timestamp endTime = new Timestamp(parsedDate.getTime());
				
				if (newRoom.getCapacity() >= saveUsers.size()){
					if (newRoom.checkAvailable(finalDate, startTime, endTime)){
						Appointment saveAppointment = new Appointment(name, description, location, newRoom, saveUsers, finalDate, startTime, endTime, this.sessionUser);
						saveAppointment.updateAppointment();
						saveAppointment.inviteParticipants();
						saveAppointment.reserveRoom(newRoom);
					}
					else{
						System.out.println("Double booking is not allowed");
					}
				}
				else System.out.println("You are over capacity. You have invited " + saveUsers.size() + " while the max capacity for the room is " + newRoom.getCapacity());
				

				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(saveAppointment != null){
				try {

					Node  source = (Node)  event.getSource(); 
				    Stage stage  = (Stage) source.getScene().getWindow();
				    stage.close();
//				    --------
//					FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/kalender.fxml"));
//				    Scene scene = new Scene((Parent) loader.load());
//				    ProfilController profil = new ProfilController();
//			        scene.getStylesheets().add(profil.getCss());
//					primaryStage.setScene(scene);
//					KalenderController newCont = loader.<KalenderController>getController();
//					newCont.setSession(this.sessionUser);
////					primaryStage.getIcons().add(icon);
//					
//					primaryStage.show();
//					primaryStage.setResizable(false);
				} catch (Exception e) {
					  
					e.printStackTrace();
				}
				
			}
		}
		else{
			System.out.println("IKKE GODKJENT");
		}
	
	}

	public void avbrytButt(ActionEvent event){
//		try {
//			Main newMain = new Main();
//			newMain.setSession(this.sessionUser);
//			newMain.startKalender(new Stage());
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
		//Henter stage parameter
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
	}

	public void slettAvtale(ActionEvent event){
		
//		do your shit here
		
//		-----------------
//			try {
//			Main newMain = new Main();
//			newMain.setSession(this.sessionUser);
//			newMain.startKalender(new Stage());
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		det som er over åpner NY kalendercontroller, usikker på om vi skal ha det. ATM closer den bare redigeravtaler

		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
	}

}
