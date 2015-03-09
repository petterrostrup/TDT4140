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
		
		try {
			System.out.println("herro");
			String sqlStatement = "SELECT * FROM USER";
			ResultSet results = DatabaseCommunicator.execute(sqlStatement);
			while (results.next()){
				System.out.println(results.getString(results.findColumn("username")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String sqlStatement = "SELECT * FROM USER WHERE username = '" + username + "';";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		try {
			results.next();
			if (results.getString(results.findColumn("password")).equals(password)){
				System.out.println("bro");
				String name = results.getString(results.findColumn("name"));
				System.out.println(name);
				String dbPassword = results.getString(results.findColumn("password"));
				System.out.println(password);
				String dbUsername = results.getString(results.findColumn("username"));
				System.out.println(username);
				String mail = results.getString(results.findColumn("email"));
				System.out.println(mail);
				String address = results.getString(results.findColumn("address"));
				System.out.println(address);
				
				user = new User(dbUsername, dbPassword, name, mail, address);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
