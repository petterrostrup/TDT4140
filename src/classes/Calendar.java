package classes;

import java.util.ArrayList;

public class Calendar {
	private User owner;
	private ArrayList<Appointment> appointments;

	public User getOwner() {
		return this.owner;
	}

	public void setOwner(User user) {
		this.owner = user;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	public void createAppointment(){
		// Construct Appointment (this will insert into database)
		// Add appointment do appointments
	}
	
	public void deleteAppointment(Appointment appointment){
		// Delete appointment (this will remove from database)
		// Remove appointment from appointments
	}
	
}
