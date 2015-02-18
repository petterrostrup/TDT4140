package classes;

public class Room {
	private String roomNr;
	private String place;
	private int capacity;
	private boolean available;
	

	public Room() {
		// TODO Auto-generated constructor stub
	}


	public String getRoomNr() {
		return roomNr;
	}


	public void setRoomNr(String roomNr) {
		this.roomNr = roomNr;
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
		this.capacity = capacity;
	}


	public boolean isAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}

}
