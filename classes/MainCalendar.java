package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainCalendar {
	private User owner;
	private ArrayList<Appointment> appointments = new ArrayList<Appointment>();


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
	
	public Appointment getAppointment(long appointmentID){
		String sqlStatement = 	"SELECT * FROM APPOINTMENT WHERE id = '" + appointmentID + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		Appointment returning = null;
		try {
				results.next();
				Long id = (results.getLong(1));
				String name = results.getString("name");
				String desc = results.getString("description");
				String loc = results.getString("location");
				Room room = getroom(results.getLong(5));
				Date date = results.getDate("date");
				Timestamp start = results.getTimestamp("start");
				Timestamp end = results.getTimestamp("end");
				
				returning = new Appointment(name, desc, loc, room, new ArrayList<User>(), date, start, end, owner, id + "");
				this.appointments.add(returning);
				System.out.println("Adding appointment " + id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returning;
	}
	
	public Room getroom(Long roomid) {
		String sqlStatement = 	"SELECT * FROM ROOM WHERE id = '" + roomid + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		Room room = null;
		try {
			results.next();
			room = new Room(results.getLong(1) + "",results.getString("name"), results.getString("place"), results.getInt("capacity"));
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
		c1.set(2015, Calendar.MARCH, 23);
		Appointment appointment1 = new Appointment("Gruppemøte", "Vanlig møte", "Bygg-1", testRoom,  c1.getTime() ,Timestamp.valueOf("2015-03-23 18:00:00.0"),Timestamp.valueOf("2015-03-23 20:00:00.0"), varUser);
		c1.set(2015, Calendar.MARCH, 24);
		Appointment appointment2 = new Appointment("Gruppemøte", "Vanlig møte", "Bygg-1", testRoom, c1.getTime(),Timestamp.valueOf("2015-03-24 19:00:00.0"),Timestamp.valueOf("2015-03-24 21:00:00.0"), varUser);
		c1.set(2015, Calendar.MARCH, 25);
		Appointment appointment3 = new Appointment("Gruppemøte", "Vanlig møte", "Bygg-1", testRoom, c1.getTime(),Timestamp.valueOf("2015-03-25 20:00:00.0"),Timestamp.valueOf("2015-03-25 22:00:00.0"), varUser);
		c1.set(2015, Calendar.MARCH, 26);
		Appointment appointment4 = new Appointment("Gruppemøte", "Vanlig møte", "Bygg-1", testRoom, c1.getTime(),Timestamp.valueOf("2015-03-26 21:00:00.0"),Timestamp.valueOf("2015-03-26 23:00:00.0"), varUser);
		
		appointments = new ArrayList<Appointment>();
		appointments.add(appointment1);
		appointments.add(appointment2);
		appointments.add(appointment3);
		appointments.add(appointment4);
	}
	
	public void fillCalendar(String id){
		appointments.clear();
		String sqlStatement = "SELECT * FROM CONNECTED WHERE person = '" + id + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		Appointment adding;
		try {
			//System.out.println(results.isClosed());
			while (results.next()) {
				if (results.getInt("status") != -1){
					adding = this.getAppointment(results.getLong(3));
					if (!appointments.contains(adding)){
						appointments.add(adding);					
					}					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
	}
	
}
