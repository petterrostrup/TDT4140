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

public class ProfilController extends Application {
	
	@FXML
	private ImageView imageview;
	
	@FXML
	private Label brukernavn;
	
	@FXML
	private Pane mainPane;
	
	@FXML
	private ColorPicker colorpick;
	
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
	public void start(Stage stage) throws Exception {
		
		//final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no/petternr_calendar", "petternr_user" , "gruppe61");
	       Parent root = FXMLLoader.load(getClass().getResource("profil.fxml"));
	        Scene scene = new Scene(root);
	        stage.setTitle("Profil");
	        stage.setScene(scene);
	        stage.show();
	     //   scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        
	        //Image img = new Image(getClass().getResourceAsStream("http://mikecann.co.uk/wp-content/uploads/2009/12/javafx_logo_color_1.jpg"));
	        
	        //Image image = new Image(getClass().getResourceAsStream("youngmaster.jpg"));
	       
	        //imageview.setImage(new Image("youngmaster.jpg"));
	        
	       

	}

	
	public void kalenderButt (ActionEvent event) {
		try {
			new LagAvtaleController().start(new Stage()); // FORANDRES/FJERNES
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
