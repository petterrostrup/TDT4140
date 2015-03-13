package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;

public class Room {
	private String id;
	private String name;
	private String place;
	private int capacity;
	private boolean available;
	
	public Room(String id, String room, String place, int cap) {
		setId(id);
		setName(room);
		setPlace(place);
		setCapacity(cap);
		setAvailable(false);
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}


	public void setName(String roomNr) {
		if (roomNr.matches("[a-zA-Z]+-?[a-zA-Z]* \\d*")){
			this.name = roomNr;			
		}
		else throw new IllegalArgumentException("Invalid Roomnumber");	
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		if (capacity > 0){
			this.capacity = capacity;			
		}
		else throw new IllegalArgumentException("Not a valid capacity. Must be positive integer");
	}

	public boolean isAvailable(LocalTime start, LocalTime end, Date date) {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public void saveRoom(){
		String sqlStatement = "SELECT * FROM ROOM WHERE name = '" + this.getName() + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		try {
			if (!results.next()){
				sqlStatement = "INSERT INTO USER (name, place, capacity) "
						+ "VALUES ( '" + this.getName() + "', '" + this.getPlace() + "', '" + this.getCapacity() + "')";
				System.out.println("Saving room");
				DatabaseCommunicator.update(sqlStatement);				
			}
			else{
				System.out.println("Room exists. Cannot save");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Something went wrong connecting to the database");
		}
	}
	
	public void readRoom(String id){
		String sqlStatement = "SELECT * FROM ROOM WHERE id = '" + id + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		try {
			if (results.next()){
				this.setId(id);
				this.setName(results.getString(results.findColumn("name")));
				this.setPlace(results.getString(results.findColumn("place")));
				this.setCapacity(results.getInt(results.findColumn("capacity")));
			}
			else{
				System.out.println("Room does not exist. Cannot read");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Something went wrong connecting to the database");
		}
		
	}

}
