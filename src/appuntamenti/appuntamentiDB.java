package appuntamenti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Main.funzioni;



public class appuntamentiDB {

	public void CreaAppuntamentiDB() {
		String url="jdbc:sqlite:novanta6.db";   
		String sql = "CREATE TABLE IF NOT EXISTS Appuntamenti (\n"
		                + "	descrizione varchar(100) NOT NULL,\n"
		                + "	data date,\n"
		                + "	ora_inizio varchar(5) NOT NULL,\n"
		                + "	ora_fine varchar(5) NOT NULL \n"
		                + ");";
		try (Connection conn = DriverManager.getConnection(url)) {
	        if (conn != null) { 
	        	  PreparedStatement pstmt = conn.prepareStatement(sql);
	              pstmt.executeUpdate();
	        }
		}  catch (SQLException e) {
			funzioni.StampaErrore("Tabella appuntamenti non creata!");
        }
}
	
	public static void AggiungiAppuntamento(String descrizione, String data, String inizio, String fine) {
		
		String url="jdbc:sqlite:novanta6.db";       
		String sql = "INSERT INTO Appuntamenti(descrizione,data,ora_inizio,ora_fine) VALUES(?,?,?,?)";

	    try (Connection conn = DriverManager.getConnection(url)) {
	        if (conn != null) {
		       PreparedStatement pstmt = conn.prepareStatement(sql); 
		            
		            pstmt.setString(1, descrizione);
		            pstmt.setString(2, data);
		            pstmt.setString(3,inizio);
		            pstmt.setString(4,fine);
		           pstmt.executeUpdate();
	               }
		        } catch (SQLException e) {
		        	funzioni.StampaErrore("Operazione non eseguita!");		
		        	}
	    }
	
	    //ELIMINA GLI APPUNTAMENTI VECCHII
	    public static  void Elimina(String n) {
	    	String url="jdbc:sqlite:novanta6.db";      
	        String sql = "DELETE FROM Appuntamenti WHERE data < ?";

	        try (Connection conn = DriverManager.getConnection(url)){
		       if (conn != null) {
		    	   PreparedStatement pstmt = conn.prepareStatement(sql);
		    	   pstmt.setString(1,n);
		    	   pstmt.executeUpdate();
		           conn.close();
		           }
	           } catch (SQLException e) {
	        	   funzioni.StampaErrore("Operazione non eseguita!");
	        	   }
	    }
	 

}
