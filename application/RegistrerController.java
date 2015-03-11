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
	private Label ugyldigBrukernavn;
	
	@FXML
	private Label ugyldigEpost;
	
	// M� ADDE UGJYLDIG-LABELZ
	@FXML
	private void initialize(){
		
	}
	
	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress());
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
		User varUser = null;
		
		try {
			String userName = brukernavn.getText();
			String password = passord.getText();
			String name = navn.getText();
			String eMail = epost.getText();
			String address = adresse.getText();
			
			varUser = new User(userName, password, eMail, name, address);
			
			varUser.saveUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (varUser != null){
			try {
				Main newMain = new Main();
				newMain.setSession(varUser);
				newMain.start(new Stage());
				Node  source = (Node)  event.getSource(); 
				Stage stage  = (Stage) source.getScene().getWindow();
				stage.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//Henter stage parameter

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
