package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainCalendar {
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
	
	public void saveAppointment(Appointment appointment){
		// Construct Appointment (this will insert into database)
		this.appointments.add(appointment);
	}
	
	public void deleteAppointment(Appointment appointment){
		// Delete appointment (this will remove from database)
		this.appointments.remove(appointment);
	}
	
	public Appointment getAppointment(int appointmentID){
		String sqlStatement = 	"SELECT * FROM APPOINTMENT WHERE id = '" + appointmentID + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		Appointment returning = null;
		try {
				results.next();
				String id = Integer.toString(results.getInt("id"));
				String name = results.getString("name");
				String desc = results.getString("description");
				String loc = results.getString("location");
				Room room = getroom(Integer.toString(results.getInt("room")));
				Date date = results.getDate("date");
				Time start = results.getTime("start");
				Time end = results.getTime("end");
				
				returning = new Appointment(name, desc, loc, room, null, date, start.toLocalTime(), end.toLocalTime(), owner);
				appointments.add(returning);
				System.out.println("Adding appointment");
			
		} catch (SQLException e) {
			//SUCK MY DIIIIIICK IM A SHAAAARK
			e.printStackTrace();
		}
		return returning;
		
	}
	
	public Room getroom(String roomid) {
		String sqlStatement = 	"SELECT * FROM ROOM WHERE id = '" + roomid + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		Room room = null;
		try {
			results.next();
			room = new Room(String.valueOf(results.getLong(1)),results.getString("name"), results.getString("place"), results.getInt("capacity"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return room;
	}

	public void fillTest(){
		Calendar c1 = Calendar.getInstance();
		Room testRoom = new Room("5", "Realfag 245", "somewhere", 10);
		User varUser = new User("testuser123", "Testpass12345", "test@gmail.com", "Test Testesen", "Testelia 12");
		c1.set(2015, Calendar.MARCH, 15);
		Appointment appointment1 = new Appointment("Gruppemøte", "Vanlig møte", "Bygg-1", testRoom,  c1.getTime() ,LocalTime.parse("16:00"),LocalTime.parse("17:30"), varUser);
		c1.set(2015, Calendar.MARCH, 16);
		Appointment appointment2 = new Appointment("Gruppemøte", "Vanlig møte", "Bygg-1", testRoom, c1.getTime(),LocalTime.parse("15:00"),LocalTime.parse("16:30"), varUser);
		c1.set(2015, Calendar.MARCH, 17);
		Appointment appointment3 = new Appointment("Gruppemøte", "Vanlig møte", "Bygg-1", testRoom, c1.getTime(),LocalTime.parse("14:00"),LocalTime.parse("15:30"), varUser);
		c1.set(2015, Calendar.MARCH, 18);
		Appointment appointment4 = new Appointment("Gruppemøte", "Vanlig møte", "Bygg-1", testRoom, c1.getTime(),LocalTime.parse("13:00"),LocalTime.parse("14:30"), varUser);
		
		appointments = new ArrayList<Appointment>();
		appointments.add(appointment1);
		appointments.add(appointment2);
		appointments.add(appointment3);
		appointments.add(appointment4);
	}
	
	public void fillCalendar(){
		String id = null; //user id here
		String sqlStatement = "SELECT * FROM ATTENDING WHERE person = '" + id + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		Appointment adding = null;
		try {
			//System.out.println(results.isClosed());
			while (results.next()) {
				adding = getAppointment(results.getInt(results.findColumn("appointment")));
				System.out.println("Adding appointment");
				appointments.add(adding);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		MainCalendar trialCalendar = new MainCalendar();
		trialCalendar.fillCalendar();
	}
	
}
