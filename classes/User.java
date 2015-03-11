package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class User {
	private String id;
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
	
	public User(String user, String pass, String mail, String varName, String varAddress, String id){
		setUserName(user);
		setPassword(pass);
		seteMail(mail);
		setName(varName);
		setAddress(varAddress);
		setId(id);
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
		if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$")){
			this.password = password;			
		}
		
		else throw new IllegalArgumentException("Illegal password. Must contain lower and higher case, numbers and at least 6 chars long");
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		//All unicode chars from any language
		//if (name.matches("^[\\p{L} .'-]+$")){
			this.name = name;
		//}
		//else throw new IllegalArgumentException("Invalid name");
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
		if (address.matches("((([A-Z]?[a-z]* ?)*)[0-9]+)")){
			this.address = address;		
		}
		else throw new IllegalArgumentException("Invalid street address");
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User logIn(String username, String password){
		 User varUser = Login.login(username, password);
		 
		 return varUser;
		 
	}
	
	public void logOut(){
		//Terminate connection
	}
	
	public void attend(){
		//Change an attending status on event
	}
	
	public void saveUser(){
		System.out.println(this.getUserName());
		String sqlStatement = "SELECT * FROM USER WHERE username = '" + this.getUserName() + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		try {
			if (!results.next()){
				sqlStatement = "INSERT INTO USER (username, password, name, email, address) "
						+ "VALUES ( '" + this.getUserName() + "', '" + this.getPassword() + "', '" + this.getName() + "', '" + this.geteMail() +"', '" + this.getAddress() + "');";
				System.out.println("Saving user");
				DatabaseCommunicator.update(sqlStatement);				
			}
			else{
				System.out.println("User exists. Cannot save");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Something went wrong connecting to the database");
		}
		
	}
	
	public void updateUser(){
		System.out.println(this.getUserName());
		String sqlStatement = "SELECT * FROM USER WHERE username = '" + this.getUserName() + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		try {
			if (results.next()){
				sqlStatement = "UPDATE USER SET name = '" + this.getName() + "', email = '" + this.geteMail() + "', address = '" + this.getAddress() + "', password = '" + this.getPassword() + "' WHERE username = '" + this.getUserName() + "'";
				System.out.println("Updating user");
				DatabaseCommunicator.update(sqlStatement);				
			}
			else{
				System.out.println("User does not exists. Cannot save changes");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something went wrong connecting to the database");
		}
	}
	
	
}
