package application;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NyGruppeController {
	
	private User sessionUser;
	
	@FXML
	private TextField gruppeNavn;
	@FXML
	private ListView allePersonerList;
	@FXML
	private ListView nyGruppeMedlemmerList;

	//Lister
	private ObservableList<String> medlemmerNyGruppe = FXCollections.observableArrayList(); // liste over medlemmer i den nye gruppen
	private ObservableList<String> personer = FXCollections.observableArrayList("Emil", "Aleksander", "Fredrik"); //liste over alle personer som man kan legge til
	//ListerSlutt
	@FXML
	private void initialize(){
		nyGruppeMedlemmerList.setItems(medlemmerNyGruppe);
		allePersonerList.setItems(personer);
	}
	public void sendRight(ActionEvent event){
		String fjernPerson = (String) nyGruppeMedlemmerList.getSelectionModel().getSelectedItem();
		if(fjernPerson != null){
			nyGruppeMedlemmerList.getSelectionModel().clearSelection();
			medlemmerNyGruppe.remove(fjernPerson);
			personer.add(fjernPerson);
		}
	}
		
	public void sendLeft (ActionEvent event){

		String leggtilPerson = (String) allePersonerList.getSelectionModel().getSelectedItem();
		if(leggtilPerson != null){
			allePersonerList.getSelectionModel().clearSelection();
			personer.remove(leggtilPerson);
			medlemmerNyGruppe.add(leggtilPerson);
		}
	}
	
	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
	}
	
	public void lagreButt (ActionEvent event) {
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
