package incassi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Main.funzioni;

public class incassiDB {
	
	public incassiDB() {}
	
	
	public void CreaIncassiDB() {
		String url="jdbc:sqlite:novanta6.db";   
		String sql = "CREATE TABLE IF NOT EXISTS Incassi (\n"
		                + "	data date PRIMARY KEY ,\n"
		                + "	incasso float(40)\n"
		                + ");";
		try (Connection conn = DriverManager.getConnection(url)) {
	        if (conn != null) { 
	        	  PreparedStatement pstmt = conn.prepareStatement(sql);
	              pstmt.executeUpdate();
	        }
	        conn.close();
		}  catch (SQLException e) {
			funzioni.StampaErrore("Tabella incassi non creata!");
        }
		}
	
	public static void AggiungiIncasso( String data,String incasso) {
		
		String url="jdbc:sqlite:novanta6.db";       
		String sql = "INSERT INTO Incassi(data,incasso) VALUES(?,?)";
    try {
    	float value= Float.parseFloat(incasso);
    
	    try (Connection conn = DriverManager.getConnection(url)) {
	        if (conn != null) {
		       PreparedStatement pstmt = conn.prepareStatement(sql); 
		            pstmt.setString(1,data );
		            pstmt.setFloat(2, Float.parseFloat(incasso));
                     pstmt.executeUpdate();
	               }
	        conn.close();

		        } catch (SQLException e) {
					funzioni.StampaErrore("Operazione non eseguita!");
		        }
    }catch(Exception e){
        funzioni.StampaErrore(" Non hai inserito un numero");
    }
	}
	
	public static void Elimina( String data) {
 		String url="jdbc:sqlite:novanta6.db";       
		String sql = "DELETE FROM Incassi WHERE data =?;";
			    try (Connection conn = DriverManager.getConnection(url)) {
			        if (conn != null) {
				       PreparedStatement pstmt = conn.prepareStatement(sql); 
    				       pstmt.setString(1,data );
		                     pstmt.executeUpdate();
			               }
			        conn.close();

				        } catch (SQLException e) {
							funzioni.StampaErrore("Operazione non eseguita!");
				        }
		  
		  }
	
	
}
