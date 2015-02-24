package classes;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
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
	
	public Appointment(String name, String location, Room room, Date date, LocalTime start, LocalTime end){
		setName(name);
		setLocation(location);
		setRoom(room);
		setDate(date);
		setStart(start);
		setEnd(end);
	}
	
	public Appointment(String name, String desc, String location, Room room, ArrayList<User> participants, Date date, LocalTime start, LocalTime end){
		setName(name);
		setDescription(desc);
		setLocation(location);
		setRoom(room);
		setParticipants(participants);
		setDate(date);
		setStart(start);
		setEnd(end);
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
