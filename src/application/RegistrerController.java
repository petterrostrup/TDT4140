package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;

public class RegistrerController {
	
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
	private Button openfile;
	
	@FXML
	private ImageView imageview;
	
	@FXML
	private Label ugyldigBrukernavn;
	
	@FXML
	private Label ugyldigEpost;
	
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("registrer.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Registrer");
		stage.setScene(scene);
		stage.show();
		System.out.println("HELLO WØØØRLD");
	}
	
	public void regButt (ActionEvent event){
		System.out.println("registrert");
		try{
			new RegistrerController().start(new Stage());
		}
		catch (Exception e){
			e.printStackTrace();
		}
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	public void openButt(ActionEvent event){
		System.out.println("butt iz opened");
	}
	
	
	public static void main(String[] args){
		launch(args);
	}

}
