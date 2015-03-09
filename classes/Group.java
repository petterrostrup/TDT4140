package classes;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Group {
	private String groupID;
	private String groupName;
	private ArrayList<User> persons;
	
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public ArrayList<User> getPersons() {
		return persons;
	}
	public void setPersons(ArrayList<User> persons) {
		this.persons = persons;
	}
	
	public void createGroup(){
		String sqlStatement = "INSERT IGNORE INTO MEMBERGROUP (name) "
				+ "VALUES (" + getGroupName() + ")";
		DatabaseCommunicator.update(sqlStatement);
	}
	
	public void addMember(int id){
		String sqlStatement = "INSERT IGNORE INTO MEMBER (person, membergroup) "
				+ "VALUES (" + id + ", " + this.getGroupID() + ")";
		DatabaseCommunicator.update(sqlStatement);
	}
	
	public void removeMember(int id){
		String sqlStatement = "DELETE IGNORE FROM MEMBER WHERE person = " + id;
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
	}
	
	public void getMembers(){
		
	}
}
