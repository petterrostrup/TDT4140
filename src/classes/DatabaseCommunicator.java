package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseCommunicator {
	
	private static String url = "jdbc:mysql://mysql.stud.ntnu.no/";
    private static String user = "petternr_felles";
    private static String password = "gruppe61";
	
	public static void test(){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT VERSION()");
			
			if (rs.next()) {
				System.out.println(rs.getString(1));
			}
			
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseCommunicator.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
				
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(DatabaseCommunicator.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		
	}
	
	public static void create(){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		String appointmentTable = "CREATE TABLE APPOINTMENT " +
                "(id INTEGER not NULL, " +
                " name VARCHAR(255), " + 
                " description VARCHAR(255), " + 
                " location VARCHAR(255), " + 
                " location VARCHAR(255), " + 
                " room INTEGER not NULL, " + 
                " date DATE, " +
                " start DATETIME, " +
                " end DATETIME, " +
                " FOREIGN KEY (room) REFERENCES room(id), " +
                " PRIMARY KEY ( id ))"; 
		String userTable = "CREATE TABLE USER " +
                "(id INTEGER not NULL, " +
                " username VARCHAR(255), " +
                " password VARCHAR(255), " +
                " name VARCHAR(255), " +
                " email VARCHAR(255), " +
                " address VARCHAR(255), " +
                " PRIMARY KEY ( id ))"; 
		String roomTable = "CREATE TABLE ROOM " +
                "(id INTEGER not NULL, " +
                " name VARCHAR(255), " + 
                " place VARCHAR(255), " + 
                " capacity INTEGER(25), " + 
                " PRIMARY KEY ( id ))"; 
		String groupTable = "CREATE TABLE GROUP " +
                "(id INTEGER not NULL, " +
                " name VARCHAR(255), " + 
                " PRIMARY KEY ( id ))"; 
		String attendingTable = "CREATE TABLE ATTENDING " +
                "(id INTEGER not NULL, " +
                " person INTEGER not NULL, " +
                " appointment INTEGER not NULL, " +
                " FOREIGN KEY (person) REFERENCES user(id), " +
                " FOREIGN KEY (appointment) REFERENCES appointment(id), " +
                " PRIMARY KEY ( id ))"; 
		String memberTable = "CREATE TABLE MEMBER " +
                "(id INTEGER not NULL, " +
                " person INTEGER not NULL, " +
                " group INTEGER not NULL, " +
                " FOREIGN KEY (person) REFERENCES user(id), " +
                " FOREIGN KEY (group) REFERENCES group(id), " +
                " PRIMARY KEY ( id ))"; 
		String bookingTable = "CREATE TABLE BOOKING " +
                "(id INTEGER not NULL, " +
                " room INTEGER not NULL, " +
                " appointment INTEGER not NULL, " +
                " FOREIGN KEY (room) REFERENCES room(id), " +
                " FOREIGN KEY (appointment) REFERENCES appointment(id), " +
                " PRIMARY KEY ( id ))";  
		
		try {
			System.out.println("Connecting to database");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Connected");
			st = con.createStatement();
			st.executeUpdate(appointmentTable);
			st.executeUpdate(userTable);
			st.executeUpdate(roomTable);
			st.executeUpdate(groupTable);
			st.executeUpdate(attendingTable);
			st.executeUpdate(memberTable);
			st.executeUpdate(bookingTable);
			
			con.commit();
			
			if (rs.next()) {
				System.out.println(rs.getString(1));
			}
			
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseCommunicator.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
				
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(DatabaseCommunicator.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
	}
	
	public ResultSet execute(String statement){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery(statement);
			
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseCommunicator.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
				
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(DatabaseCommunicator.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		
		return rs;
	}

    public static void main(String[] args) {
    	create();
    }
}
