package application;

import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import com.sun.javafx.css.StyleManager;

import classes.Appointment;
import classes.DatabaseCommunicator;
import classes.Group;
import classes.MainCalendar;
import classes.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ProfilController {
	
	private User sessionUser;
	
	@FXML
	private ImageView imageview;
	
	@FXML
	private Label brukernavn;

	@FXML Label navn;
	
	@FXML
	private Label adresse;
	
	@FXML
	private Label email;

	
	@FXML
	private Label innloggetsom;
	
	@FXML
	private Label avtaler;
	
	@FXML
	private ListView dineGrupper;
	
	private MainCalendar myCal;
	
	private ArrayList<Group> myGroups = new ArrayList<Group>();
	
	private ObservableList<String> grupper = FXCollections.observableArrayList(); // HENT INN GRUPPER

	
	@FXML
	private ListView avtalerList;
	@FXML
	private ListView visAvtalerList;

	@FXML
	private ComboBox visThemes;
	
	private ArrayList<String> allThemes = new ArrayList<>();
	ArrayList<Appointment> myAppointments;
	ArrayList<Appointment> myAppointmentsNotifications;
	
	
	private ObservableList<String> allAppointmentsView = FXCollections.observableArrayList();
	private ObservableList<String> notificationAppointmentsView = FXCollections.observableArrayList();
	
	private String css = getClass().getResource("../style/LightTheme.css").toExternalForm();
	
	@FXML
	public void initialize(){
//		avtalerList.setItems(notificationAppointmentsView);
		   Rectangle clip = new Rectangle(imageview.getFitWidth(), imageview.getFitHeight());
	        clip.setArcWidth(20);
           clip.setArcHeight(20);
           imageview.setClip(clip);
           
           SnapshotParameters parameters = new SnapshotParameters();
           parameters.setFill(Color.TRANSPARENT);
           WritableImage image = imageview.snapshot(parameters, null);

           imageview.setClip(null);
           imageview.setEffect(new DropShadow(20, Color.BLACK));
           imageview.setImage(image);
           
           visThemes.getItems().addAll("Dark Theme", "Light Theme", "Girly Theme", "Laser Theme", "JB Theme");

           
	}

	public void velgThemes(ActionEvent event){
		visThemes.getSelectionModel().getSelectedItem();
		System.out.println(visThemes.getSelectionModel().getSelectedItem());

			
		if(visThemes.getSelectionModel().getSelectedItem().equals("Light Theme")){
			css = getClass().getResource("../style/LightTheme.css").toExternalForm();
		}
		else if(visThemes.getSelectionModel().getSelectedItem().equals("Dark Theme")){
			css = getClass().getResource("../style/DarkTheme.css").toExternalForm();
//			String css = LoginController.class.getResource("DarkTheme.css").toExternalForm();
//			scene.getStylesheets().clear();
			

//			// load default global stylesheet
//			Application.setUserAgentStylesheet(null);
//			// add custom global stylesheet
//			StyleManager.getInstance().addUserAgentStylesheet("../style/DarkTheme.css");
			   
		}
//		else if(visThemes.getSelectionModel().getSelectedItem().equals("Girly Theme")){
//			css = getClass().getResource("../style/GurlyTheme.css").toExternalForm();
//		}
//		else if(visThemes.getSelectionModel().getSelectedItem().equals("Laser Theme")){
//			css = getClass().getResource("../style/LaserTheme.css").toExternalForm();
//		}
//		else if(visThemes.getSelectionModel().getSelectedItem().equals("JB Theme")){
//			css = getClass().getResource("../style/JBTheme.css").toExternalForm();
//		}
	}
	public void setCss(String css) {
		this.css = css;
	}
	public String getCss() {
		
		return css;
	}

	public void administrerGrupperButt(ActionEvent event){
		try {
			Main newMain = new Main();
			newMain.setSession(this.sessionUser);
			newMain.startAdministrerGrupper(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
		innloggetsom.setText("Innlogget som: " + this.sessionUser.getName());
		brukernavn.setText(this.sessionUser.getUserName());
		navn.setText(this.sessionUser.getName());
		email.setText(this.sessionUser.geteMail());
		adresse.setText(this.sessionUser.getAddress());
		
		String sqlStatement = "SELECT * FROM MEMBER WHERE person = '" + this.sessionUser.getId() + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		Group newGroup;
		try {
			while (results.next()){
				
				String innerSql = "SELECT * FROM MEMBERGROUP WHERE id = '" + results.getLong(3) + "'";
				ResultSet innerResults = DatabaseCommunicator.execute(innerSql);
				innerResults.next();
				
				String name = innerResults.getString("name");
				Long id = innerResults.getLong(1);
				int leader = innerResults.getInt("leader");
				newGroup = new Group(name,leader + "", id.toString());
				myGroups.add(newGroup);
				grupper.add(newGroup.getGroupName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myCal = new MainCalendar();
		myCal.fillCalendar(this.sessionUser.getId());
		
		ArrayList<Appointment> comparing = myCal.getAppointments();
		
		myAppointments = new ArrayList<Appointment>();
		myAppointmentsNotifications = new ArrayList<Appointment>();
		
		SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm");
		Appointment localAppointment;
		for (int i = 0; i < comparing.size(); i++) {
			localAppointment = comparing.get(i);
			
			if(localAppointment.getDate().after(new Date())){
				myAppointments.add(localAppointment);
				String timestring = sdfTime.format(localAppointment.getStart()) + " - " + sdfTime.format(localAppointment.getEnd());
				
				allAppointmentsView.add(localAppointment.getName() + ": " + localAppointment.getDate().toString() + " " + timestring );
				sqlStatement = "SELECT * FROM CONNECTED WHERE appointment = '" + localAppointment.getAppointmentID() + "' AND person = '" + this.sessionUser.getId() + "'";
				results = DatabaseCommunicator.execute(sqlStatement);
				try {
					if (results.next()) {
						if (results.getInt("notification") == 0){
							myAppointmentsNotifications.add(localAppointment);
							notificationAppointmentsView.add(localAppointment.getName());
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}				
			}
			
		}
		
		avtalerList.setItems(notificationAppointmentsView);
		visAvtalerList.setItems(allAppointmentsView);
		dineGrupper.setItems(grupper);
		
	}

	public void visAvtale(MouseEvent event){
		// TRYKK HER, så sendes du til avtalen du trykket på
		
		Object visAvtaleValgt = (Object) avtalerList.getSelectionModel().getSelectedItem();
		if(visAvtaleValgt != null){
			try {
				Main newMain = new Main();
				newMain.setSession(this.sessionUser);
				newMain.startAvtaleOversikt(new Stage(), (Appointment) visAvtaleValgt);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Node source = (Node) event.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
//			stage.close();
		}
		
	}
	
	public void kalenderButt (ActionEvent event) {
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
	
	public void avtaleButt (ActionEvent event) {
		try {
			Main newMain = new Main();
			newMain.setSession(this.sessionUser);
			newMain.startLagAvtale(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Henter stage parameter
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();		
	}
	
	public void logoutButt (ActionEvent event) {
		
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
		
	
	public void redigerProfilButt (ActionEvent event) {
		try {
			Main newMain = new Main();
			newMain.setSession(this.sessionUser);
			newMain.startRedigerBruker(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Henter stage parameter
		((Node)(event.getSource())).getScene().getWindow().hide();
	    
	    
	}
	
	//Henter instanser av en avtale
	public void hentAvtale(ActionEvent event) {
		//Har en klikk event når du klikker på info i panen
		//System.out.println("asd");
		Appointment newApp = new Appointment(null, null, null, null, null, null, null, null, sessionUser);
		//Putte alle appointments til en bruker i en list, kan så velge attend/ikke attending
		
	}
	
	
	
	//Testknapp, tar deg nå til avtaleOversikt
//	public void test(ActionEvent event){
//		try{
//			Main newMain= new Main();
//			newMain.setSession(this.sessionUser);
//			newMain.startDagsOversikt(new Stage());
//			 
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//		Node  source = (Node)  event.getSource(); 
//	    Stage stage  = (Stage) source.getScene().getWindow();
//	    stage.close();
//	}

	
	
}
