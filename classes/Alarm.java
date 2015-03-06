package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class Alarm {	
	public void setNotification(int appID, int userID){
		
	}
	
	public void getNotification(int appID, int userID){
		String sqlStatement = 	"SELECT * FROM CONNECTED WHERE appointment = " + appID + " AND person = " + userID;
		ResultSet results = DatabaseCommunicator.execute(sqlStatement);
		
		try {
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendNotification(){
		
	}
	

}
