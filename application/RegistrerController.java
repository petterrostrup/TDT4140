package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;



import javafx.application.Application;
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
	
	@FXML
	private Label visBilde;
	
	private Desktop desktop = Desktop.getDesktop();
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("registrer.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Registrer");
		stage.setScene(scene);
		stage.show();
		
		
	
		
		
		
		}
		
	public void openFile(ActionEvent event){
		System.out.println("åpne fil");

				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Velg bilde");
				
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*", "*");
				fileChooser.getExtensionFilters().add(extFilter);
				
				File file = fileChooser.showOpenDialog(null);
				if(file!=null){
					//imageview.setImage(new Image(file.getPath()));
					imageview.setImage(new Image("C:\Users\Kristian\Pictures"));
					
					
				}		
	}
		
	
	public void regButt (ActionEvent event){
		System.out.println("registrert");

	}
	

	
	
	public static void main(String[] args){
		launch(args);
	}

}
