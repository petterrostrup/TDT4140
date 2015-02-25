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
    	test();
    }
}
