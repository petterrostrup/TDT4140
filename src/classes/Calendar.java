package classes;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

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
		Appointment appointment = null;
		this.appointments.add(appointment);
	}
	
	public void deleteAppointment(Appointment appointment){
		// Delete appointment (this will remove from database)
		this.appointments.remove(appointment);
	}
	
	public void fillTest(){
		Room testRoom = new Room("245", "somewhere", 10);
		Appointment appointment1 = new Appointment("Gruppemøte", "Bygg-1", testRoom,new Date(2015, 03, 02),LocalTime.parse("16:00"),LocalTime.parse("17:30"));
		Appointment appointment2 = new Appointment("Gruppemøte", "Bygg-1", testRoom,new Date(2015, 04, 02),LocalTime.parse("15:00"),LocalTime.parse("16:30"));
		Appointment appointment3 = new Appointment("Gruppemøte", "Bygg-1", testRoom,new Date(2015, 05, 02),LocalTime.parse("14:00"),LocalTime.parse("15:30"));
		Appointment appointment4 = new Appointment("Gruppemøte", "Bygg-1", testRoom,new Date(2015, 06, 02),LocalTime.parse("13:00"),LocalTime.parse("14:30"));
		
		appointments.add(appointment1);
		appointments.add(appointment2);
		appointments.add(appointment3);
		appointments.add(appointment4);
	}
	
}
