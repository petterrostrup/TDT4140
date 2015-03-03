package application;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

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

public class RegistrerController extends Application{
	
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
	
	// MÅ ADDE UGJYLDIG-LABELZ
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("registrer.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Registrer");
		stage.setScene(scene);
		stage.show();
		
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
		try {
			new HjemController().start(new Stage());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//Henter stage parameter
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();

	}
	

	
	
	public static void main(String[] args){
		launch(args);
	}

}
