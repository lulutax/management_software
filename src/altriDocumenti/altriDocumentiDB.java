package altriDocumenti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Main.funzioni;


public class altriDocumentiDB {

	
	public altriDocumentiDB() {}

	//CREA TABELLA ALTRIDOCUMETI
    public void CreaAltriDocumenti() {
		String url="jdbc:sqlite:novanta6.db";   
		String sql ="CREATE TABLE IF NOT EXISTS AltriDocumenti (\n"
	            + "	nome varchar(100) ,\n"
	            + "file varchar(200) \n"
	            + ");";
		try (Connection conn = DriverManager.getConnection(url)) {
	        if (conn != null) { 
	        	  PreparedStatement pstmt = conn.prepareStatement(sql);
	              pstmt.executeUpdate();
	        }
		}  catch (SQLException e) {
			funzioni.StampaErrore("Tabella Altridocumenti non creata!");
			}
   }
    
    //AGGIUNGI DOCUMENTO
    public static void AggiungiDocumento( String n,String f)  {
		
		String url="jdbc:sqlite:novanta6.db";       
		String sql = "INSERT INTO AltriDocumenti(nome,file) VALUES(?,?)";		
	    try (Connection conn = DriverManager.getConnection(url)) {
	        if (conn != null) {
		       PreparedStatement pstmt = conn.prepareStatement(sql); 
		       pstmt.setString(1,n);
		       pstmt.setString(2,f);
               pstmt.executeUpdate();
       	       conn.close();
       	       }
	        } catch (SQLException e) {
		        	funzioni.StampaErrore("Operazione non eseguita!");
		        	}
		    
	}
   
    //ELIMINA DOCUMENTO
    public static  void EliminaDocumento(String n) {
    	String url="jdbc:sqlite:novanta6.db";      
        String sql = "DELETE FROM AltriDocumenti WHERE nome=?";
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
    
    //RESTITUISCI NOME FILE
    public static String restituisci_file(String n) {
	  Connection conn=null;
	  String q= " ";
	  try{
	     conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
	     ResultSet rs=conn.createStatement().executeQuery("select * from AltriDocumenti where nome='"+n+"' ");
          q= rs.getString("file");
          conn.close();
          } catch (SQLException e) {
        	  funzioni.StampaErrore("Operazione non eseguita!");
        	  }
	  return q;
	  }
  
}
