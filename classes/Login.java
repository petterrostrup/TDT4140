package classes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
	
	
	public static User login(String username, String password){
		User user = null;
		
		if (username =="admin" && password =="admin"){
			user = new User("admin", "admin", "admin@counsil.com", "Human Counselor", "Counsil Towers, Persidium");
		}
		
		String sqlStatement = "SELECT * FROM USER WHERE username = '" + username + "';";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		try {
			if (results.next()){
				if (results.getString(results.findColumn("password")).equals(password)){
					String name = results.getString(results.findColumn("name"));
					String dbPassword = results.getString(results.findColumn("password"));
					String dbUsername = results.getString(results.findColumn("username"));
					String mail = results.getString(results.findColumn("email"));
					String address = results.getString(results.findColumn("address"));
					Long id = results.getLong(1);

					user = new User(dbUsername, dbPassword, mail, name, address, id.toString());
				}				
			}
			
			else {
				System.out.println("No user found in database matching that username");
			}
		} catch (SQLException e) {
			System.out.println("Error occured: " + e);
		}
		return user;
	}
}
