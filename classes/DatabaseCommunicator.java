package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseCommunicator {
	
	private static String url = "jdbc:mysql://mysql.stud.ntnu.no/petternr_calendar";
    private static String user = "petternr_user";
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
		
		String appointmentTable = "CREATE TABLE IF NOT EXISTS APPOINTMENT " +
                "(id INTEGER not NULL, " +
                " name VARCHAR(255), " + 
                " description VARCHAR(255), " + 
                " location VARCHAR(255), " + 
                " room INTEGER not NULL, " + 
                " date DATE, " +
                " start DATETIME, " +
                " end DATETIME, " +
                " owner INTEGER not NULL, " + 
                " FOREIGN KEY (room) REFERENCES ROOM(id), " +
                " FOREIGN KEY (owner) REFERENCES USER(id), " +
                " PRIMARY KEY ( id ))"; 
		String userTable = "CREATE TABLE IF NOT EXISTS USER " +
                "(id INTEGER not NULL, " +
                " username VARCHAR(255), " +
                " password VARCHAR(255), " +
                " name VARCHAR(255), " +
                " email VARCHAR(255), " +
                " address VARCHAR(255), " +
                " PRIMARY KEY ( id ))"; 
		String roomTable = "CREATE TABLE IF NOT EXISTS ROOM " +
                "(id INTEGER not NULL, " +
                " name VARCHAR(255), " + 
                " place VARCHAR(255), " + 
                " capacity INTEGER(25), " + 
                " PRIMARY KEY ( id ))"; 
		 String membergroupTable = "CREATE TABLE IF NOT EXISTS MEMBERGROUP " +
                "(id INTEGER not NULL, " +
                " name VARCHAR(255), " +  
                " PRIMARY KEY ( id ))"; 
		String connectedTable = "CREATE TABLE IF NOT EXISTS CONNECTED " +
                "(id INTEGER not NULL, " +
                " person INTEGER not NULL, " +
                " appointment INTEGER not NULL, " + 
                " status INTEGER, " +  
                " changed BOOLEAN, " +  
                " notification INTEGER, " + 
                " FOREIGN KEY (person) REFERENCES USER(id), " +
                " FOREIGN KEY (appointment) REFERENCES APPOINTMENT(id), " +
                " PRIMARY KEY ( id ))"; 
		String memberTable = "CREATE TABLE IF NOT EXISTS MEMBER " +
                "(id INTEGER not NULL, " +
                " person INTEGER not NULL, " +
                " membergroup INTEGER not NULL, " +
                " FOREIGN KEY (person) REFERENCES USER(id), " +
                " FOREIGN KEY (membergroup) REFERENCES MEMBERGROUP(id), " +
                " PRIMARY KEY ( id ))"; 
		String bookingTable = "CREATE TABLE IF NOT EXISTS BOOKING " +
                "(id INTEGER not NULL, " +
                " room INTEGER not NULL, " +
                " appointment INTEGER not NULL, " +
                " FOREIGN KEY (room) REFERENCES ROOM(id), " +
                " FOREIGN KEY (appointment) REFERENCES APPOINTMENT(id), " +
                " PRIMARY KEY ( id ))";  
		try {
			System.out.println("Connecting to database");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Connected");
			st = con.createStatement();
			//st.executeUpdate("DROP TABLE IF EXISTS  USER ");
			st.executeUpdate(userTable);
			//System.out.println("(╯°□°）╯︵ ┻�?┻");
			//st.executeUpdate("DROP TABLE IF EXISTS  ROOM ");
			st.executeUpdate(roomTable);
			//System.out.println("(╯°□°）╯︵ ┻�?┻");
			//st.executeUpdate("DROP TABLE IF EXISTS  APPOINTMENT ");
			st.executeUpdate(appointmentTable);
			//System.out.println("(╯°□°）╯︵ ┻�?┻");
			//st.executeUpdate("DROP TABLE IF EXISTS  MEMBERGROUP ");
			st.executeUpdate(membergroupTable);
			//System.out.println("(╯°□°）╯︵ ┻�?┻");
			//st.executeUpdate("DROP TABLE IF EXISTS  ATTENDING ");
			st.executeUpdate(connectedTable);
			//System.out.println("(╯°□°）╯︵ ┻�?┻");
			//st.executeUpdate("DROP TABLE IF EXISTS  MEMBER ");
			st.executeUpdate(memberTable);
			//System.out.println("(╯°□°）╯︵ ┻�?┻");
			//st.executeUpdate("DROP TABLE IF EXISTS  BOOKING ");
			st.executeUpdate(bookingTable);
			//System.out.println("(╯°□°）╯︵ ┻�?┻");
			
			System.out.println("Created all tables. Databases is initialized");
			
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
	
	public static void update(String statement){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			st.executeUpdate(statement);
			
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
	
	public static ResultSet execute(String statement){
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
