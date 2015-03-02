package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import com.sun.javafx.logging.Logger;

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

	
	private Desktop desktop = Desktop.getDesktop();
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("registrer.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Registrer");
		stage.setScene(scene);
		stage.show();
		
		
		
		final FileChooser fileChooser = new FileChooser();
		final Button openButton = new Button("Velg bilde");
		
		openButton.setOnAction(
				new EventHandler<ActionEvent>(){
					@Override
					public void handle(final ActionEvent e){
						File file = fileChooser.showOpenDialog(stage);
						if(file != null){
							openFile(file);
						}
					}
				
				});
			
		}
		
	public void openFile(File file){
		System.out.println("supp bro, open a file bro");
		
		
		try{
			desktop.open(file);
		}
		catch (IOException ex){
			Logger.getLogger(
					RegistrerController.class.getName()).log(
							Level.SEVERE, null, ex
							);
		}
		
	}
		
	
	public void regButt (ActionEvent event){
		System.out.println("registrert");
//		try{
//			new RegistrerController().start(new Stage());
//		}
//		catch (Exception e){
//			e.printStackTrace();
//		}
//		Node source = (Node) event.getSource();
//		Stage stage = (Stage) source.getScene().getWindow();
//		stage.close();
	}
	

	
	
	public static void main(String[] args){
		launch(args);
	}

}
