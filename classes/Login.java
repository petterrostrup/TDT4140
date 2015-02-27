package classes;

public class Login {
	
	
	public static User login(String username, String password){
		
		if (username =="admin" && password =="admin"){
			User user = new User("admin", "admin", "admin@counsil.com", "Human Counselor", "Counsil Towers, Persidium");
			return user;
		}
		else return null;
	}
}
