package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {
	private String groupID;
	private String groupName;
	private ArrayList<User> persons;
	private String leader;
	
	public Group(String groupName){
		setGroupName(groupName);
	}
	
	public Group(String groupName, String leader){
		setGroupName(groupName);
		setLeader(leader);
	}
	
	public Group(String groupName, String leader, String id){
		setGroupName(groupName);
		setLeader(leader);
		setGroupID(id);
	}
	
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
	
	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public void createGroup(){
		String sqlStatement = "SELECT * FROM MEMBERGROUP WHERE name = '" + this.getGroupName() + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		try {
			if (!results.next()){
				sqlStatement = "INSERT INTO MEMBERGROUP (name, leader) "
						+ "VALUES ( '" + this.getGroupName() + "', '" + this.getLeader() + "')";
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
	
	public void addMember(String id){
		String sqlStatement = "SELECT * FROM MEMBER WHERE person = '" + id + "' AND membergroup = '" + this.getGroupID() + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		try {
			if (!results.next()){
				sqlStatement = "INSERT INTO MEMBER (person, membergroup) "
						+ "VALUES ( '" + id + "', '" + this.getGroupID() + "')";
				System.out.println("Adding member to group");
				DatabaseCommunicator.update(sqlStatement);			
			}
			else{
				System.out.println("User is already in group");
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
	
	public Group readGroup(String name){
		Group returnGroup = null;
		String sqlStatement = "SELECT * FROM MEMBERGROUP WHERE name = '" + name + "';";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		try {
			if (results.next()){
					Long id = results.getLong(1);
					int leader = results.getInt("leader");
					returnGroup = new Group(name,leader + "", id.toString());
			}
			else {
				System.out.println("No group found in database matching that name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnGroup;
	}
	
	public Group readGroupId(String gId){
		Group returnGroup = null;
		String sqlStatement = "SELECT * FROM MEMBERGROUP WHERE id = '" + gId + "';";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		try {
			if (results.next()){
				String name = results.getString("name");
				Long id = results.getLong(1);
				int leader = results.getInt("leader");
				returnGroup = new Group(name,leader + "", id.toString());
			}
			else {
				System.out.println("No group found in database matching that name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnGroup;
	}
	
	public Group getMyGroups(String pId){
		Group returnGroup = null;
		String sqlStatement = "SELECT * FROM MEMBER WHERE person = '" + pId + "';";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		try {
			if (results.next()){
				String name = results.getString("name");
				Long id = results.getLong(1);
				returnGroup = new Group(name,leader, id.toString());
			}
			else {
				System.out.println("No group found in database matching that name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnGroup;
	}
	
	
}
