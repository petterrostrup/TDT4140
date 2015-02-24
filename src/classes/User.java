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
		//lowercase and numbers allowed between 3 to 15 chars long
		if (userName.matches("^[a-z0-9_-]{3,15}$")){
			this.userName = userName;
		}
		
		else throw new IllegalArgumentException("Invalid username");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		//Lower and Upper case, must contain number and at least length of 8
		if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")){
			this.password = password;			
		}
		
		else throw new IllegalArgumentException("Illegal password. Must contain lower and higher case, numbers and at least 8 chars long");
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		//All unicode chars from any language
		if (name.matches("^[\\p{L} .'-]+$")){
			this.name = name;
		}
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		if (eMail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			this.eMail = eMail;			
		}
		
		else throw new IllegalArgumentException("Invalig email");
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (name.matches("((([A-Z]?[a-z]* ?)*)[0-9]+)")){
			this.address = address;		
		}
		else throw new IllegalArgumentException("Invalid street address");
	}
	
	public void logIn(String username, String password){
		 User varUser = Login.login(username, password);
		//Insert sql logic for login with this.userName and this.password
	}
	
	public void logOut(){
		//Terminate connection
	}
	
	public void changeAttending(){
		//Change an attending status on event
	}
	
	
}
