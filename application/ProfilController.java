package application;

import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import classes.Appointment;
import classes.DatabaseCommunicator;
import classes.Group;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
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
	
	@FXML
	private Label adresse;
	
	@FXML
	private Label email;
	@FXML
	private Pane mainPane;
	
	@FXML
	private ColorPicker colorpick;
	@FXML
	private Label innloggetsom;
	
	@FXML
	private Label avtaler;
	
	@FXML
	private ListView dineGrupper;
	
	private ArrayList<Group> myGroups = new ArrayList<Group>();
	
	private ObservableList<String> grupper = FXCollections.observableArrayList(); // HENT INN GRUPPER
	
	@FXML
	private RadioButton deltar;
	
	@FXML
	private RadioButton deltarIkke;
	
	@FXML
	private AnchorPane avtScrollpane;
	
	
	@FXML
	public void initialize(){
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
		brukernavn.setText(this.sessionUser.getUserName() + " - " + this.sessionUser.getName());
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
		
		dineGrupper.setItems(grupper);
		
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
	
	public void pickColor(ActionEvent event){
		
		//getStyleClass().add("bordered-titled-border");
		//mainPane.getStyleClass().add("boromirBorder");
		//private color = pickColor.
		
//		mainPane.setFill(ColorPicker.getValue());
		
		Color c = colorpick.getValue();
		System.out.println("Hey this is " + c.getRed() + " and " + c.getBlue());
//		mainPane.set(colorpick.getValue());
	}
	
	
	//Henter instanser av en avtale
	public void hentAvtale(ActionEvent event) {
		//Har en klikk event når du klikker på info i panen
		//System.out.println("asd");
		Appointment newApp = new Appointment(null, null, null, null, null, null, null, null, sessionUser);
		//Putte alle appointments til en bruker i en list, kan så velge attend/ikke attending
		
	}
	
	//Setter bruker som deltar på instans av avtale
	public void deltar(ActionEvent event) {
		//System.out.println("hei");
		
	}
	
	//Setter bruker som ikke deltar på instans av avtale
	public void deltarIkke(ActionEvent event) {
		//System.out.println("hade");
	}
	
	
	
	//Testknapp, tar deg nå til avtaleOversikt
	public void test(ActionEvent event){
		try{
			Main newMain= new Main();
			newMain.setSession(this.sessionUser);
			newMain.startAvtaleOversikt(new Stage());
			 
		}catch (Exception e){
			e.printStackTrace();
		}
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
	}

	
	
}
