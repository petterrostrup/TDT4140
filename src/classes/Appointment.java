package classes;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Appointment {
	private String appointmentID;
	private String name;
	private String description;
	private String location;
	private Room room;
	private ArrayList<User> participants;
	private Date date;
	private Date start;
	private Date end;
	
	public Appointment(){
		
	}
	
	public String getAppointmentID() {
		return appointmentID;
	}
	public void setAppointmentID(String appointmentID) {
		this.appointmentID = appointmentID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public ArrayList<User> getParticipants() {
		return participants;
	}
	public void setParticipants(ArrayList<User> participants) {
		this.participants = participants;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public void connect(){
		Connection conn = null;
		
		try {conn =DriverManager.getConnection("jdbc:mysql.stud.ntnu.no/petternr_felles" +
		                                   "user=petternr_felles&password=gruppe61");

		    // Do something with the Connection
		
			
			
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}

		
	}
	
	
	
	public void change(){
		// Change the appointment and save to database
	}
	
	public void addParticipant(User user){
		// Find user and add him to the list of participants.
	}
	
	public void removeParticipant(User user){
		// Find user and remove him from the list of participants.
	} 
	
	public void findRoom(Room room){
		// Find room?
	}
	
	public void reserveRoom(Room room){
		// Add room to this appointment if available
	}
	

}
