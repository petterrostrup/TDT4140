package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import classes.Appointment;
import classes.DatabaseCommunicator;
import classes.Group;
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
	
	private Group currentGroup;

	//Lister
	private ArrayList<User> userPersoner = new ArrayList<User>();
	private ArrayList<Group> adminGroups = new ArrayList<Group>();
	private ArrayList<User> groupMedlemmer = new ArrayList<User>();

	private ObservableList<Object> grupper = FXCollections.observableArrayList(); //henter inn grupper man har
	private ObservableList<Object> medlemmer = FXCollections.observableArrayList(); //liste over medlemmer i gruppen
	private ObservableList<Object> personer = FXCollections.observableArrayList();  //liste over alle personer som man kan legge til
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
		
		medlemmerList.setItems(medlemmer);
		allePersonerList.setItems(personer);
		dineGrupper.setItems(grupper);
	}
	public void sendRight(ActionEvent event){
		String fjernPerson = (String) medlemmerList.getSelectionModel().getSelectedItem();
		if(fjernPerson != null){
			for (int i = 0; i < groupMedlemmer.size(); i++) {
				if (groupMedlemmer.get(i).getName().equals(fjernPerson)){
					userPersoner.add(groupMedlemmer.get(i));
					groupMedlemmer.remove(i);	
				}
			}
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
		personer.addAll(medlemmer);
		userPersoner.addAll(groupMedlemmer);
		medlemmer.clear();
		groupMedlemmer.clear();
		Object visMedlemmerIGruppe = (Object) dineGrupper.getSelectionModel().getSelectedItem();
		if(visMedlemmerIGruppe != null){
			
			String sqlStatement = "SELECT * FROM MEMBERGROUP WHERE name = '" + visMedlemmerIGruppe.toString() + "'";
			ResultSet results = DatabaseCommunicator.execute(sqlStatement);
			Group newGroup = null;
			try {
				while (results.next()){
					String name = results.getString("name");
					Long id = results.getLong(1);
					int leader = results.getInt("leader");
					newGroup = new Group(name,leader + "", id.toString());
					currentGroup = newGroup;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sqlStatement = "SELECT * FROM MEMBER WHERE membergroup = '" + newGroup.getGroupID() + "'";
			results = DatabaseCommunicator.execute(sqlStatement);
			User newUser;
			try {
				while (results.next()){
					newUser = User.readUser(results.getLong(2));
					groupMedlemmer.add(newUser);
					medlemmer.add(newUser.getName());
					System.out.println("Added user with ID: " + results.getLong(2));
					if (personer.contains(newUser.getName())){
						userPersoner.remove(newUser);
						personer.remove(newUser.getName());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			dineGrupper.getSelectionModel().clearSelection();
			medlemmerList.setItems(medlemmer);
			
//			gruppeListe.getSelectionModel().clearSelection();
//			medlemmer.add(visMedlemmerIGruppe);
		}

	}
	
	public void visMedlemmerList(ActionEvent event){
		
	}
	
	public void nyGruppe(ActionEvent event){
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
		
		String sqlStatement = "SELECT * FROM MEMBERGROUP WHERE leader = '" + this.sessionUser.getId() + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		Group newGroup;
		try {
			while (results.next()){
				String name = results.getString("name");
				Long id = results.getLong(1);
				int leader = results.getInt("leader");
				newGroup = new Group(name,leader + "", id.toString());
				currentGroup = newGroup;
				adminGroups.add(newGroup);
				grupper.add(newGroup.getGroupName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void oppdaterButt (ActionEvent event) {
		Boolean checkpointReached = true;
		if(checkpointReached){
			try {
				String sqlStatement = "DELETE FROM MEMBER WHERE membergroup = '" + currentGroup.getGroupID() + "'";
				DatabaseCommunicator.update(sqlStatement);
				
				
				for (int i = 0; i < groupMedlemmer.size(); i++) {
					User member = groupMedlemmer.get(i);
					System.out.println(member.getId());
					try {
						currentGroup.addMember(member.getId());
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
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
