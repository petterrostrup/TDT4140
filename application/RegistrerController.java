package application;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import classes.User;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RegistrerController {
	
	private User sessionUser;
	
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
	private PasswordField gpassord;
	
	@FXML
	private Button registrer;
	
	@FXML
	private Button velgfil;
	
	@FXML
	private ImageView imageview;
	
	@FXML
	private Label feilNavnText;
	@FXML
	private Label feilBrukernavnText;
	@FXML
	private Label feilEpostText;
	@FXML
	private Label feilAdresseText;
	@FXML
	private Label feilPassordText;
	@FXML
	private Label feilGPassordText;

	private boolean accept;
	
	@FXML
	private void initialize(){
		feilNavnText.setVisible(false);
		feilBrukernavnText.setVisible(false);
		feilEpostText.setVisible(false);
		feilAdresseText.setVisible(false);
		feilPassordText.setVisible(false);
		feilGPassordText.setVisible(false);
		
		accept = false;
	}
	
	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress() , sessionUser.getId());
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
	
	public void regButt (ActionEvent event){
		feilNavnText.setVisible(false);
		feilBrukernavnText.setVisible(false);
		feilEpostText.setVisible(false);
		feilAdresseText.setVisible(false);
		feilPassordText.setVisible(false);
		feilGPassordText.setVisible(false);
//validering start		
//navn
		if(navn.getText().isEmpty()){
			feilNavnText.setVisible(true);
			feilNavnText.setText("M� fylles ut");
		}
		else if(!navn.getText().matches("[a-zA-Z]+")){
			feilNavnText.setVisible(true);
			feilNavnText.setText("Navn kan bare inneholde bokstaver");
		}
		else{
			accept = true;
		}
//brukernavn
		if(brukernavn.getText().isEmpty()){
			feilBrukernavnText.setVisible(true);
			feilBrukernavnText.setText("M� fylles ut");
		}
		else if(!brukernavn.getText().matches("[a-z]+")){
			feilBrukernavnText.setVisible(true);
			feilBrukernavnText.setText("Bare lower-case bokstaver");
		}
		else{
			accept = true;
		}
//epost
		if(epost.getText().isEmpty()){
			feilEpostText.setVisible(true);
			feilEpostText.setText("M� fylles ut");
		}
		else if(!epost.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			feilEpostText.setVisible(true);
			feilEpostText.setText("Ugyldig epost, eks: 'abc@ntnu.no'");
		}
		else{
			accept = true;
		}
//adresse
		if(adresse.getText().isEmpty()){
			feilAdresseText.setVisible(true);
			feilAdresseText.setText("M� fylles ut");
		}
		else if(!adresse.getText().matches("^.*\\d$")){
			feilAdresseText.setVisible(true);
			feilAdresseText.setText("Ugyldig adresse, eks: 'ntnu 1'");
		}
		else{
			accept = true;
		}
//passord
		if(passord.getText().isEmpty()){
			feilPassordText.setVisible(true);
			feilPassordText.setText("M� fylles ut");
		}
		else if(!passord.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$")){ // IKKE LAGET NO-WHITESPACEVALIDATION
										//^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$
			feilPassordText.setVisible(true);
			feilPassordText.setText("Ugyldig. 6 karakterer, stor bokstav og tall");
		}
		else{
			accept = true;
		}
//gjentapassord
		if(gpassord.getText().isEmpty()){
			feilGPassordText.setVisible(true);
			feilGPassordText.setText("M� fylles ut");
		}
		else if(!gpassord.getText().equals(passord.getText())){
			feilGPassordText.setVisible(true);
			feilGPassordText.setText("Passordene er ulike");
		}
		else{
			accept = true;
		}
		
//		if(feilNavnText.isVisible() || feilBrukernavnText.isVisible() || feilEpostText.isVisible() || feilAdresseText.isVisible() || feilPassordText.isVisible() || feilGPassordText.isVisible()){
//			accept = false;
//		} JALLA METODE FOR � SJEKKE HVORFOR ACCEPT BLIR SATT TIL TRUE NOMATTER WHAT
		
//validering slutt		

		//hvis godkjent, gj�r dette && "^[^\\d\\s]+$"
		
			if(accept=true){
				System.out.println("GODKJENT");
//				User varUser = null;
//				try {
//					String userName = brukernavn.getText();
//					String password = passord.getText();
//					String name = navn.getText();
//					String eMail = epost.getText();
//					String address = adresse.getText();
//					
//					varUser = new User(userName, password, eMail, name, address);
//					
//					varUser.saveUser();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//				if (varUser != null){
//					try {
//						Main newMain = new Main();
//						newMain.setSession(varUser);
//						newMain.start(new Stage());
//						Node  source = (Node)  event.getSource(); 
//						Stage stage  = (Stage) source.getScene().getWindow();
//						stage.close();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
			}
			else{
				System.out.println("Ikke godkjent");
			}
	}
	
	public void toLogginn (ActionEvent event){
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
