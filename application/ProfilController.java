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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
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

	
	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress());
		innloggetsom.setText("Innlogget som: " + this.sessionUser.getName());
		brukernavn.setText(this.sessionUser.getUserName() + " - " + this.sessionUser.getName());
		email.setText(this.sessionUser.geteMail());
		adresse.setText(this.sessionUser.getAddress());
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
	
	public void test(ActionEvent event){
		try{
			Main newMain= new Main();
			newMain.setSession(this.sessionUser);
			newMain.startProfil(new Stage());
			 
		}catch (Exception e){
			e.printStackTrace();
		}
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
	}

	
	
}
