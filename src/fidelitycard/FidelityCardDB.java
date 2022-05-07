package fidelitycard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Main.funzioni;


public class FidelityCardDB {
public FidelityCardDB() {}
	
	public void CreaFidelityCard() {
		String url="jdbc:sqlite:novanta6.db";   
		String sql ="CREATE TABLE IF NOT EXISTS FidelityCard (\n"
				+ "	codice varchar(70) PRIMARY KEY,\n"
				+ "	nome varchar(70) NOT NULL,\n"
				+ "	cognome varchar(40),\n"
				+ "	numero_timbri int,\n"
				+ "	data_rilascio date\n"
				+ ");";
		try (Connection conn = DriverManager.getConnection(url)) {
	        if (conn != null) { 
	        	  PreparedStatement pstmt = conn.prepareStatement(sql);
	              pstmt.executeUpdate();

	        }
		}  catch (SQLException e) {
			funzioni.StampaErrore("Tabella fidelity non creata");        }
} 

	
	public static void AggiungiFidelity( String cod, String nome, String cognome,String  n_timbri, String drilascio) {

	String url="jdbc:sqlite:novanta6.db";       
	String sql = "INSERT INTO FidelityCard(codice,nome,cognome,numero_timbri,data_rilascio) VALUES(?,?,?,?,?)";
  
	  try (Connection conn = DriverManager.getConnection(url)) {
		        if (conn != null) {
			       PreparedStatement pstmt = conn.prepareStatement(sql); 
			            pstmt.setString(1,cod );
			            pstmt.setString(2, nome);
			            pstmt.setString(3, cognome);
			            pstmt.setInt(4, Integer.parseInt(n_timbri));
			            pstmt.setString(5,drilascio);
			           pstmt.executeUpdate();

		               }
                conn.close();

			        } catch (SQLException e) {
			        	funzioni.StampaErrore("Operazione non eseguita!");
			        }

}
	
	
	
	
	 public static void AggiornaNumeroTimbri( String cod,String nT) {
		   String url="jdbc:sqlite:novanta6.db";       
			String sql = "UPDATE FidelityCard\r\n" + 
					"SET numero_timbri = ? \r\n" + 
					"WHERE codice =?;"; 
		   
		   try {
		       int valore2 = Integer.parseInt( nT );
			       try (Connection conn = DriverManager.getConnection(url)) {
				        if (conn != null) {
					       PreparedStatement pstmt = conn.prepareStatement(sql); 
				           pstmt.setInt(1, Integer.parseInt(nT));
			               pstmt.setString(2,cod );
			               pstmt.executeUpdate();
			                conn.close();
			                }
				        } catch (SQLException e) {
				        	funzioni.StampaErrore("Operazione non eseguita!");
								 }
			 }catch (Exception e) {
				 funzioni.StampaErrore("Non hai inserito un numero nel codice carta "
				 		+ "o nel numero timbri");
			    }
	
		}
	 
	 
	//RESTITUISCI NUM TIMBRI
	    public static int restituisciNumTimbri(String n) {
		  Connection conn=null;
		  int q= 0;
		  try{
		     conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
		     ResultSet rs=conn.createStatement().executeQuery("select * from FidelityCard where codice='"+n+"' ");
	          q= rs.getInt("numero_timbri");
	          conn.close();
	          } catch (SQLException e) {
	        	  funzioni.StampaErrore("Operazione non eseguita!");
		       }
		  return q;
		  }
	    
	    
	    
	 public static  void EliminaFidelity(String cod) {
		   String url="jdbc:sqlite:novanta6.db";      
	       String sql = "DELETE FROM FidelityCard WHERE  codice= ?";
	       
	       try (Connection conn = DriverManager.getConnection(url)){
	    	   if (conn != null) {
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	           pstmt.setString(1,cod);
	           pstmt.executeUpdate();
	           
	    	   }
               conn.close();

	       } catch (SQLException e) {
	    	   funzioni.StampaErrore("Operazione non eseguita!");
	    	  }
	       }
 }
	
	 
