package classes;

import java.util.ArrayList;

public class Group {
	private String GroupID;
	private ArrayList<User> persons;
	
	public String getGroupID() {
		return GroupID;
	}
	public void setGroupID(String groupID) {
		GroupID = groupID;
	}
	public ArrayList<User> getPersons() {
		return persons;
	}
	public void setPersons(ArrayList<User> persons) {
		this.persons = persons;
	}
}
