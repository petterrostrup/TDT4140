package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;

import classes.Appointment;
import classes.DatabaseCommunicator;
import classes.Group;
import classes.MainCalendar;
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
	private ArrayList<User> userMedlemmerNyGruppe = new ArrayList<User>();
	private ArrayList<User> userPersoner = new ArrayList<User>();
	
	private ObservableList<String> medlemmerNyGruppe = FXCollections.observableArrayList(); // liste over medlemmer i den nye gruppen
	private ObservableList<String> personer = FXCollections.observableArrayList(); //liste over alle personer som man kan legge til
	//ListerSlutt
	@FXML
	private void initialize(){
		String sqlStatement = "SELECT * FROM USER";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		User newUser;
		try {
			while (results.next()){
				String name = results.getString(results.findColumn("name"));
				String dbPassword = results.getString(results.findColumn("password"));
				String dbUsername = results.getString(results.findColumn("username"));
				String mail = results.getString(results.findColumn("email"));
				String address = results.getString(results.findColumn("address"));
				Long id = results.getLong(1);
				newUser = new User(dbUsername, dbPassword, mail, name, address, id.toString());
				
				userPersoner.add(newUser);
				personer.add(newUser.getName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nyGruppeMedlemmerList.setItems(medlemmerNyGruppe);
		allePersonerList.setItems(personer);
	}
	public void sendRight(ActionEvent event){
		String fjernPerson = (String) nyGruppeMedlemmerList.getSelectionModel().getSelectedItem();
		if(fjernPerson != null){
			for (int i = 0; i < userMedlemmerNyGruppe.size(); i++) {
				if (userMedlemmerNyGruppe.get(i).getName().equals(fjernPerson)){
					userPersoner.add(userMedlemmerNyGruppe.get(i));
					userMedlemmerNyGruppe.remove(i);	
				}
			}
			nyGruppeMedlemmerList.getSelectionModel().clearSelection();
			medlemmerNyGruppe.remove(fjernPerson);
			personer.add(fjernPerson);
		}
	}
		
	public void sendLeft (ActionEvent event){

		String leggtilPerson = (String) allePersonerList.getSelectionModel().getSelectedItem();
		if(leggtilPerson != null){
			for (int i = 0; i < userPersoner.size(); i++) {
				if (userPersoner.get(i).getName().equals(leggtilPerson)){
					userMedlemmerNyGruppe.add(userPersoner.get(i));
					userPersoner.remove(i);	
				}
			}
			allePersonerList.getSelectionModel().clearSelection();
			personer.remove(leggtilPerson);
			medlemmerNyGruppe.add(leggtilPerson);
		}
	}
	
	public void setSession(User sessionUser){
		this.sessionUser = new User(sessionUser.getUserName(), sessionUser.getPassword(), sessionUser.geteMail(), sessionUser.getName(), sessionUser.getAddress(), sessionUser.getId());
	}
	
	public void lagreButt (ActionEvent event) {
		if (!gruppeNavn.getText().isEmpty() && (userMedlemmerNyGruppe.size() != 0)){
			try {
				Group insertGroup = new Group(gruppeNavn.getText(), this.sessionUser.getId());
				insertGroup.createGroup();
				
				insertGroup = insertGroup.readGroup(insertGroup.getGroupName());
				for (int i = 0; i < userMedlemmerNyGruppe.size(); i++) {
					User member = userMedlemmerNyGruppe.get(i);
					System.out.println(member.getId());
					try {
						insertGroup.addMember(member.getId());
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
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
		
		else throw new IllegalArgumentException("Navnet må være definert og medlemmer må legges til");
	}
	
	public void avbrytButt(ActionEvent event){
		try {
			Main newMain = new Main();
			newMain.setSession(this.sessionUser);
			newMain.startProfil(new Stage());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
	}
}
