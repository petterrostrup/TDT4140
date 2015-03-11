package classes;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Appointment {
	// Make logic to generate appointmentID
	private String appointmentID;
	private String name;
	private String description;
	private String location;
	private Room room;
	private ArrayList<User> participants;
	private Date date;
	private LocalTime start;
	private LocalTime end;
	private User owner;
	
	public Appointment(String name, String desc, String location, Room room, Date date, LocalTime start, LocalTime end, User user){
		setName(name);
		setLocation(location);
		setRoom(room);
		setDate(date);
		setStart(start);
		setEnd(end);
		setOwner(user);
	}
	
	public Appointment(String name, String desc, String location, Room room, ArrayList<User> participants, Date date, LocalTime start, LocalTime end, User user){
		setName(name);
		setDescription(desc);
		setLocation(location);
		setRoom(room);
		setParticipants(participants);
		setDate(date);
		setStart(start);
		setEnd(end);
		setOwner(user);
	}
	
	public String getAppointmentID() {
		return appointmentID;
	}
	public void setAppointmentID(String appointmentID) {
		if (appointmentID.matches("([0-9])+")){
			this.appointmentID = appointmentID;			
		}
		else throw new IllegalArgumentException("Invalid id. Must be positive integer");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		// Doesn't need validation here
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		// Doesn't need validation here
		this.description = description;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		// Doesn't need validation here
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
		Date now = new Date();
		int result = now.compareTo(date);
		if (result < 0){
			this.date = date;			
		}
		else throw new IllegalArgumentException("Date must be after current date");
	}
	
	public LocalTime getStart() {
		return start;
	}
	public void setStart(LocalTime start) {
		this.start = start;	
	}
	
	public LocalTime getEnd() {
		return end;
	}
	public void setEnd(LocalTime end) {
		this.end = end;
	}
	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void saveAppointment(Appointment appointment){
		String sqlStatement = "SELECT * FROM APPOINTMENT WHERE owner = '" + this.getOwner() + "' AND start = '" + this.getStart() + "' AND date = '" + this.getDate() + "'" ;
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		try {
			if (!results.next()){
				sqlStatement = "INSERT INTO APPOINTMENT (name, description, location, room, date, start, end, owner) "
						+ "VALUES ('" + appointment.getName() + "', '" + appointment.getDescription() + "', '" + appointment.getLocation() + "', '" + appointment.getRoom().getRoomNr() +"', '" + appointment.getDate().toString() +"', '" + appointment.getStart().toString() +"', '" + appointment.getEnd().toString() +"', '" + appointment.getOwner().getId() + "')";
				System.out.println("Saving appointment");
				DatabaseCommunicator.update(sqlStatement);				
			}
			else{
				System.out.println("Appointment exists. Cannot save");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Something went wrong connecting to the database");
		}
	}
	
	public void addParticipant(User user){
		this.participants.add(user);
	}
	
	public void removeParticipant(User user){
		this.participants.remove(user);
	} 
	
	public void reserveRoom(Room room){
		// Add room to this appointment if available
	}
	

}
