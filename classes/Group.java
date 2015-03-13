package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
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
		String sqlStatement = "SELECT * FROM MEMBERGROUP WHERE name = '" + this.getGroupName() + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		try {
			if (!results.next()){
				sqlStatement = "INSERT INTO MEMBERGROUP (name) "
						+ "VALUES ( '" + this.getGroupName() + "')";
				System.out.println("Saving group");
				DatabaseCommunicator.update(sqlStatement);			
			}
			else{
				System.out.println("Group exists. Cannot save");
			}}  catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Something went wrong connecting to the database");
				}

	}
	
	public void addMember(int id){
		String sqlStatement = "SELECT * FROM MEMBER WHERE person = '" + id + "' AND membergroup = '" + this.getGroupID() + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		try {
			if (!results.next()){
				sqlStatement = "INSERT INTO MEMBER (person, membergroup) "
						+ "VALUES ( '" + id + "', '" + this.getGroupID() + "')";
				System.out.println("Saving group");
				DatabaseCommunicator.update(sqlStatement);			
			}
			else{
				System.out.println("Group exists. Cannot save");
			}}  catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Something went wrong connecting to the database");
				}
	}
	
	public void removeMember(int id){
		String sqlStatement = "DELETE FROM MEMBER WHERE person = '" + id + "' AND membergroup = '" + this.getGroupID() + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
	}
	
	public void readMembers(){
		String sqlStatement = "SELECT * FROM MEMBER WHERE membergroup = '" + this.getGroupID() + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		
		try {
			while (results.next()){
				persons.add(User.readUser(results.getLong(1)));
				System.out.println("Added user with ID: " + results.getLong(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
