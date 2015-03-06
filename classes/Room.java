package classes;

import java.time.LocalTime;
import java.util.Date;

public class Room {
	private String roomNr;
	private String place;
	private int capacity;
	private boolean available;
	

	public Room(String room, String place, int cap) {
		setRoomNr(room);
		setPlace(place);
		setCapacity(cap);
		setAvailable(false);
	}


	public String getRoomNr() {
		return roomNr;
	}


	public void setRoomNr(String roomNr) {
		if (roomNr.matches("[a-zA-Z]+-?[a-zA-Z]* \\d*")){
			this.roomNr = roomNr;			
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

}
