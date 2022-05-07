package pizzeriaDB;
import java.sql.Connection;
	import java.sql.DatabaseMetaData;
	import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.rowset.CachedRowSetImpl;
	 
public class PizzeriaDB {
	 public static String url="jdbc:sqlite:novanta6.db";
	 private static Connection conn=null;
	 
	   public static void createConnection() {
		   
		   try {
			   conn = DriverManager.getConnection(url);
	            if (conn != null) {
	            	 System.out.println("Connessione Stabilita" );
	            }
		   } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
		    }	 
	    public void createNewDatabase() {
	    	try{
	           createConnection();
	                DatabaseMetaData meta = conn.getMetaData();
	                System.out.println("The driver name is " + meta.getDriverName());	 
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	    public static void dbExecuteQuery(String sqlStm) {
	    	
	    	Statement stmt=null;
	    	try {
	    		createConnection();
	    		stmt=conn.createStatement();
	    		stmt.executeUpdate(sqlStm);
	    	}catch(SQLException e){
	    		 System.out.println("query non eseguita!");
	    	}finally {
	    		try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
		    		 System.out.println("query non eseguita!");
				}
	    	}
	    	
	    	
	    	
	    }
 public static ResultSet dbExecute(String sqlQuery) {
	    	
	    	Statement stmt=null;
	    	ResultSet rs=null;
	        CachedRowSetImpl crs= null;
	    	try {
	    		createConnection();
	    		stmt=conn.createStatement();
	    		rs= stmt.executeQuery(sqlQuery);
	    		crs=  new CachedRowSetImpl();
	    		crs.populate(rs);
	    	}catch(SQLException e){
	    		 System.out.println("query non eseguita!");
	    	}
	    	finally {
	    		if(rs!=null && stmt!=null) {
	    			try {
						rs.close();
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
			    		 System.out.println("query non eseguita!");
					}
	    	}
	     }
	    	return rs;
 }
 
 
 
}