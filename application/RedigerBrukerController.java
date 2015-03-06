package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import classes.User;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RedigerBrukerController extends Application {
	
	@FXML
	private ImageView imageview;
	
	@FXML
	private TextField navn;
	
	@FXML
	private TextField brukernavn;
	
	@FXML
	private TextField epost;
	
	@FXML
	private TextField adresse;
	
	@FXML
	private PasswordField passord;
	
	@FXML
	private PasswordField nyttpassord;
	
	@FXML
	private PasswordField gnyttpassord;
	
	@FXML
	private Label ugyldigNavn;
	
	@FXML
	private Label ugyldigNyttBrukernavn;
	
	@FXML
	private Label ugyldigEpost;
	
	@FXML
	private Label ugyldigAdresse;
	
	@FXML
	private Label ugyldigGammeltpassord;
	
	@FXML
	private Label ugyldigNyttpassord;
	
	@FXML
	private Label ugyldigGjentapassord;
	
	
		
	String navnRed;
	String nyttBrukernavnRed;
	String epostRed;
	String adresseRed;
	String gammeltPassordRed;
	String nyttPassordRed;
	String gnyttPassordRed;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		//final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no/petternr_calendar", "petternr_user" , "gruppe61");
	       Parent root = FXMLLoader.load(getClass().getResource("redigerbruker.fxml"));
	        Scene scene = new Scene(root);
	        stage.setTitle("Rediger bruker");
	        stage.setScene(scene);
	        stage.show();
	        
	}
	
	//setTextFill(Color.RED);

	//Lagre data fra skjema i database
	public void lagreButt (ActionEvent event) {
		//System.out.println("test");
		//boolean dataOk = true;
		

		navnRed = navn.getText();
		nyttBrukernavnRed = brukernavn.getText();
		epostRed = epost.getText();
		adresseRed = adresse.getText();
		gammeltPassordRed = passord.getText();
		nyttPassordRed = nyttpassord.getText();
		gnyttPassordRed = gnyttpassord.getText();
		//System.out.println(nyttBrukernavnRed);
		
		
		
		boolean checkpointReached = true;
		try {User varUser = new User(nyttBrukernavnRed, nyttPassordRed, epostRed, navnRed, adresseRed);
		System.out.println(varUser.geteMail());
		
			
		} catch (Exception e) {
			System.out.println(e);
			
			ugyldigNavn.setText("Ugyldig navn");
			ugyldigNyttBrukernavn.setText("Lowercase and numbers allowed between 3 to 15 chars long");
			ugyldigEpost.setText("Ugyldig epost");
			ugyldigAdresse.setText("Ugyldig adresse");
			ugyldigGammeltpassord.setText("Ugyldig gammelt passord");
			ugyldigNyttpassord.setText("Lower and Upper case, must contain number and at least length of 8");
			ugyldigGjentapassord.setText("Ugyldig gjenta passord");
			
			ugyldigNavn.setTextFill(Color.RED);
			ugyldigNyttBrukernavn.setTextFill(Color.RED);
			ugyldigEpost.setTextFill(Color.RED);
			ugyldigAdresse.setTextFill(Color.RED);
			ugyldigGammeltpassord.setTextFill(Color.RED);
			ugyldigNyttpassord.setTextFill(Color.RED);
			ugyldigGjentapassord.setTextFill(Color.RED);
			
			checkpointReached = true;
		}
		
		
		
		// hvis validering er godkjent, send til hjem
		if(checkpointReached){
			try {
				new KalenderController().start(new Stage());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			//Henter stage parameter
			Node  source = (Node)  event.getSource(); 
		    Stage stage  = (Stage) source.getScene().getWindow();
		    stage.close();
		}
		else{
			ugyldigNyttBrukernavn.setText("");
		}
		
		
		
		
		
		
		
//		if(navnRed.length()<2){
//			ugyldigNavn.setText("lol");
//			dataOk = false;
//		}
//		//Inneholder tall
//		else if(navnRed.matches(".*\\d.*")){
//			ugyldigNavn.setText("Fornavn kan ikke inneholde tall");
//			dataOk = false;
//		}
	
	}
	
	public void openFile(ActionEvent event){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Velg bilde");
		
		 FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		
		File file = fileChooser.showOpenDialog(null);
		
		try{
			BufferedImage buffImg = ImageIO.read(file);
			Image img = SwingFXUtils.toFXImage(buffImg, null);
			imageview.setImage(img);
		}
		catch(IOException ex){
			Logger.getLogger(RegistrerController.class.getName()).log(Level.SEVERE, null, ex);
		}	
	}
	
	

	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
