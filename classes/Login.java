package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class Login {
	
	
	public static User login(String username, String password){
		User user = null;
		
		if (username =="admin" && password =="admin"){
			user = new User("admin", "admin", "admin@counsil.com", "Human Counselor", "Counsil Towers, Persidium");
		}
		
		String sqlStatement = "SELECT * FROM USER WHERE username = '" + username + "';";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		try {
			results.next();
			if (results.isClosed()){
				System.out.println("No user found in database matching that username");
			}
			
			else {
				if (results.getString(results.findColumn("password")).equals(password)){
					String name = results.getString(results.findColumn("name"));
					String dbPassword = results.getString(results.findColumn("password"));
					String dbUsername = results.getString(results.findColumn("username"));
					String mail = results.getString(results.findColumn("email"));
					String address = results.getString(results.findColumn("address"));
					
					user = new User(dbUsername, dbPassword, name, mail, address);
				}				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
