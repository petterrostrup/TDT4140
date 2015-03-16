package application;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import classes.Appointment;
import classes.Room;
import classes.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdministrerGrupperController {
	
	private User sessionUser;
	
	@FXML
	private ListView dineGrupper;
	@FXML
	private ListView allePersonerList;
	@FXML
	private ListView medlemmerList;
	//Lister
	private ObservableList<Object> grupper = FXCollections.observableArrayList("gruppe1", "gruppe2", "gruppe3"); //henter inn grupper man har
	private ObservableList<Object> medlemmer = FXCollections.observableArrayList("Per", "Pål"); //liste over medlemmer i gruppen
	private ObservableList<Object> personer = FXCollections.observableArrayList("Emil", "Aleksander", "Fredrik", "Petter", "Kristian");  //liste over alle personer som man kan legge til
	//ListerSlutt
	@FXML
	private void initialize(){
		medlemmerList.setItems(medlemmer);
		allePersonerList.setItems(personer);
		dineGrupper.setItems(grupper);
	}
	public void sendRight(ActionEvent event){
		String fjernPerson = (String) medlemmerList.getSelectionModel().getSelectedItem();
		if(fjernPerson != null){
			medlemmerList.getSelectionModel().clearSelection();
			medlemmer.remove(fjernPerson);
			personer.add(fjernPerson);
		}
	}
		
	public void sendLeft (ActionEvent event){

		String leggtilPerson = (String) allePersonerList.getSelectionModel().getSelectedItem();
		if(leggtilPerson != null){
			allePersonerList.getSelectionModel().clearSelection();
			personer.remove(leggtilPerson);
			medlemmer.add(leggtilPerson);
		}
	}
	
	public void visMedlemmerGruppe(MouseEvent event){
		System.out.println("viser medlemmer");
		medlemmer.clear();
		Object visMedlemmerIGruppe = (Object) dineGrupper.getSelectionModel().getSelectedItem();
		if(visMedlemmerIGruppe != null){
			dineGrupper.getSelectionModel().clearSelection();
			for (Object i : grupper) {
//				medlemmer.addAll(i, visMedlemmerIGruppe);;
				medlemmer.setAll(visMedlemmerIGruppe);
			}
			
//			gruppeListe.getSelectionModel().clearSelection();
//			medlemmer.add(visMedlemmerIGruppe);
		}

	}
	
	public void visMedlemmerList(ActionEvent event){
		
	}
	
	public void nyGruppe(ActionEvent event){
		System.out.println("STARTER NyGruppe.java");
		try {
			Main newMain = new Main();
			newMain.setSession(this.sessionUser);
			newMain.startNyGruppe(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
	}
	
	public void oppdaterButt (ActionEvent event) {
		System.out.println("test");
		Boolean checkpointReached = true;
		if(checkpointReached){
			try {
				Main newMain = new Main();
				newMain.setSession(this.sessionUser);
				newMain.startProfil(new Stage());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			//Henter stage parameter
			Node  source = (Node)  event.getSource(); 
		    Stage stage  = (Stage) source.getScene().getWindow();
		    stage.close();
		    }
	}
}
