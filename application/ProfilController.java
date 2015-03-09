package application;

import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ProfilController extends Application {
	
	@FXML
	private ImageView imageview;
	
	@FXML
	private TextField navn;
	
	@FXML
	private TextField brukernavn;
	
	@FXML
	private Pane mainPane;
	
	@Override
	public void start(Stage stage) throws Exception {
		//final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no/petternr_calendar", "petternr_user" , "gruppe61");
	       Parent root = FXMLLoader.load(getClass().getResource("profil.fxml"));
	        Scene scene = new Scene(root);
	        stage.setTitle("Profil");
	        stage.setScene(scene);
	        stage.show();
	        
	}

	
	public void kalenderButt (ActionEvent event) {
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
	
	public void avtaleButt (ActionEvent event) {
		try {
			new LagAvtaleController().start(new Stage());
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
			new LoginController().start(new Stage());
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
			new RedigerBrukerController().start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Henter stage parameter
		((Node)(event.getSource())).getScene().getWindow().hide();
	    
	    
	}
	
	public void pickColor(ActionEvent event){
		
		
		
		mainPane.setStyle("-fx-background-color: red");
//		mainPane.setFill(ColorPicker.getValue());
		
	}
	
	public void test(ActionEvent event){
		try{
			new ProfilController().start(new Stage());
			 
		}catch (Exception e){
			e.printStackTrace();
		}
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
