package classes;

public class User {
	private String userName;
	private String password;
	private String name;
	private String eMail;
	private String address;
	
	public User(String user, String pass, String mail){
		setUserName(user);
		setPassword(pass);
		seteMail(mail);
	}
	
	public User(String user, String pass, String mail, String varName, String varAddress){
		setUserName(user);
		setPassword(pass);
		seteMail(mail);
		setName(varName);
		setAddress(varAddress);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void logInn(){
		//Insert sql logic for login with this.userName and this.password
	}
	
	public void logOut(){
		//Terminate connection
	}
	
	public void changeAttending(){
		//Change an attending status on event
	}
	
	
}
