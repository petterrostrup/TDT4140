package classes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Alarm {	
	public void setNotification(int appID, int userID, int minutes){
		String sqlStatement = "UPDATE CONNECTED SET notification = '" + minutes + "' WHERE appointment = '" + appID + "' AND person = '" + userID + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
	}
	
	public int getNotification(int appID, int userID){
		String sqlStatement = 	"SELECT * FROM CONNECTED WHERE appointment = '" + appID + "' AND person = '" + userID + "'";
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		int i = 0;
		
		try {
			results.next();
			i = results.getInt("notification");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error occured: " + e);
		}
		
		return i;
	}
	
	public void sendNotification(){
		
	}
	

}
