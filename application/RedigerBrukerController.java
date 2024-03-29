package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import classes.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RedigerBrukerController {
	
	private User sessionUser;
	
	@FXML
	private ImageView imageview;
	
	@FXML
	private TextField navn;
	
	@FXML
	private TextField epost;
	
	@FXML
	private TextField adresse;
	
	@FXML
	private PasswordField gammeltpassord;
	
	@FXML
	private PasswordField nyttpassord;
	
	@FXML
	private PasswordField gnyttpassord;
	
	@FXML
	private Label feilNavnText;
	@FXML
	private Label feilEpostText;
	@FXML
	private Label feilAdresseText;
	@FXML
	private Label feilGammeltPassordText;
	@FXML
	private Label feilNyttPassordText;
	@FXML
	private Label feilGPassordText;
	
	
		
//	String navnRed;
//	String epostRed;
//	String adresseRed;
//	String gammeltPassordRed;
//	String nyttPassordRed;
//	String gnyttPassordRed;
//	
	private User varUser;
	
	
	@FXML
	private void initialize(){
		feilNavnText.setVisible(false);
		feilEpostText.setVisible(false);
		feilAdresseText.setVisible(false);
		feilGammeltPassordText.setVisible(false);
		feilNyttPassordText.setVisible(false);
		feilGPassordText.setVisible(false);
	}
	
	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
		navn.setText(this.sessionUser.getName());
		epost.setText(this.sessionUser.geteMail());
		adresse.setText(this.sessionUser.getAddress());
		
	}

	//Lagre data fra skjema i database
	public void lagreButt (ActionEvent event) {
		
		String userName;
		String password;
		String name;
		String eMail;
		String address;

		feilNavnText.setVisible(false);
		feilEpostText.setVisible(false);
		feilAdresseText.setVisible(false);
		feilGammeltPassordText.setVisible(false);
		feilNyttPassordText.setVisible(false);
		feilGPassordText.setVisible(false);
//validering start		-------------------------------------
//navn
		if(navn.getText().isEmpty()){
			feilNavnText.setVisible(true);
			feilNavnText.setText("M� fylles ut");
		}
		else if(!navn.getText().matches("([a-zA-Z]*)([\\s\\\'-][a-zA-Z]*)*")){
			feilNavnText.setVisible(true);
			feilNavnText.setText("Navn kan bare inneholde bokstaver");
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

//adresse
		if(adresse.getText().isEmpty()){
			feilAdresseText.setVisible(true);
			feilAdresseText.setText("M� fylles ut");
		}
		else if(!adresse.getText().matches("((([A-Z]?[a-z]* ?)*)[0-9]+)")){
			feilAdresseText.setVisible(true);
			feilAdresseText.setText("Ugyldig adresse, eks: 'ntnu 1'");
		}
		
		boolean isOkay = false;
		
		if (gammeltpassord.getText().isEmpty() && nyttpassord.getText().isEmpty() && gnyttpassord.getText().isEmpty()){
			isOkay = true;
		}


		
//nyttpassord
		if (!isOkay){
			if(nyttpassord.getText().isEmpty()){
				feilNyttPassordText.setVisible(true);
				feilNyttPassordText.setText("M� fylles ut");
			}
			else if(!nyttpassord.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$")){
				feilNyttPassordText.setVisible(true);
				feilNyttPassordText.setText("Ugyldig. 6 karakterer, stor bokstav og tall");
			}			
//gjentapassord
			if(gnyttpassord.getText().isEmpty()){
				feilGPassordText.setVisible(true);
				feilGPassordText.setText("M� fylles ut");
			}
			else if(!gnyttpassord.getText().equals(nyttpassord.getText())){
				feilGPassordText.setVisible(true);
				feilGPassordText.setText("Passordene er ulike");
			}
//gammeltpassord
			if(gammeltpassord.getText().isEmpty()){
				feilGammeltPassordText.setVisible(true);
				feilGammeltPassordText.setText("M� fylles ut");
			}
			else if(!(gammeltpassord.getText().equals(this.sessionUser.getPassword()))){
//			gammeltpassord.setText(this.sessionUser.getPassword());
				feilGammeltPassordText.setVisible(true);
				feilGammeltPassordText.setText("Passordet er feil");
			}
		}
		
		else{
			System.out.println("Is okay");
		}

		

//validering slutt		--------------------------------
	
		//hvis validering er godkjent, send tilbake til Profil
		if(!(feilNavnText.isVisible()) && !(feilEpostText.isVisible()) && !(feilAdresseText.isVisible()) && !(feilGammeltPassordText.isVisible()) && !(feilNyttPassordText.isVisible()) &&!(feilGPassordText.isVisible())){
			System.out.println("godkjent");
			User varUser = null;
			try{
				userName = this.sessionUser.getUserName();
				if (isOkay) password = sessionUser.getPassword();
				else password = nyttpassord.getText();
				name = navn.getText();
				eMail = epost.getText();
				address = adresse.getText();
				
				varUser = new User(userName, password, eMail, name, address, sessionUser.getId());
			
				varUser.updateUser();
			}catch (Exception e){
				System.out.println("Error occured: " + e);
			}
			if(varUser != null){
				try {
					Main newMain = new Main();
					newMain.setSession(varUser);
					newMain.startProfil(new Stage());
					Node  source = (Node)  event.getSource(); 
				    Stage stage  = (Stage) source.getScene().getWindow();
				    stage.close();
				} catch (Exception e) {
					System.out.println("Error occured: " + e);
				}
			}
		}
		else{
			System.out.println("feil");
		}
	
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
	
	public void avbrytButt (ActionEvent event) {
		try {
			Main newMain = new Main();
			newMain.setSession(this.sessionUser);
			newMain.startProfil(new Stage());
		} catch (Exception e) {
			
			System.out.println("Error occured: " + e);
		}
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
	    
	}


	
	
}
