package classes;

public class Alarm {
	private String noticeID;
	private String name;
	private Appointment appointment;
	private String description;
	
	public Alarm(String id, String name, Appointment appointment) {
		setNoticeID(id);
		setName(name);
		setAppointment(appointment);
	}


	public String getNoticeID() {
		return noticeID;
	}


	public void setNoticeID(String noticeID) {
		this.noticeID = noticeID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Appointment getAppointment() {
		return appointment;
	}


	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	public void createNotice(){
		
	}
	
	public void removeNotice(){
		
	}
	
	public void sendNotice(){
		
	}
	

}
