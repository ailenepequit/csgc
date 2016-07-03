package Graph.Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class DBConnect {
	
	Connection conn = null;

	public DBConnect()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public void connectToDB()
    {
    	try {
    		
    	    conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/absked","root","");
    	    System.out.println("connected");

    	} catch (SQLException ex) {
    	    // handle any errors
    		JOptionPane.showMessageDialog(null, "SQLException: " + ex.getMessage() + "\nSQLState: " + ex.getSQLState() + "\nVendorError: " + ex.getErrorCode(), "Error", JOptionPane.ERROR_MESSAGE);
    	}
    }
}