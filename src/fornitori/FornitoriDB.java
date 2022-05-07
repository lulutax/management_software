package fornitori;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Main.funzioni;
public class FornitoriDB {
	
	
public FornitoriDB() {}
	
	public void CreaFornitoriDB() {
		String url="jdbc:sqlite:novanta6.db";   
		String sql ="CREATE TABLE IF NOT EXISTS Fornitori (\n"
				+ "	p_iva varchar(70) PRIMARY KEY,\n"
				+ "	nome varchar(70) NOT NULL,\n"
				+ "	descrizione varchar(100),\n"
				+ "	telefono varchar(30) ,\n"
				+ "	mail  varchar(100),\n"
				+ "	indirizzo  varchar(100)\n"
				+ ");";
		try (Connection conn = DriverManager.getConnection(url)) {
	        if (conn != null) { 
	        	  PreparedStatement pstmt = conn.prepareStatement(sql);
	              pstmt.executeUpdate();
	        }
		}  catch (SQLException e) {
            System.out.println("Tabella Fornitori non creata");
        }
		}
	
	

	//AGGIUNGE UN PRODOTTO NELLA TABELLA FORNITORI
	public static void AggiungiFornitore( String piva, String n, String descr,String  tel, String m,String ind) {

	  String url="jdbc:sqlite:novanta6.db";       
	  String sql = "INSERT INTO Fornitori(p_iva,nome,descrizione,telefono,mail,indirizzo) VALUES(?,?,?,?,?,?)";
   
		 try (Connection conn = DriverManager.getConnection(url)) {
		        if (conn != null) {
			       PreparedStatement pstmt = conn.prepareStatement(sql); 
			        pstmt.setString(1,piva);
			        pstmt.setString(2, n);
		            pstmt.setString(3, descr);
			        pstmt.setString(4, tel);
			        pstmt.setString(5, m);
			        pstmt.setString(6, ind);
			        pstmt.executeUpdate();
			        }
		        } catch (SQLException e) {
		        	funzioni.StampaErrore("Operazione non eseguita!");
		             }
		 }
	  
	
	//ELIMINA FORNITORE
		public static  void EliminaFornitore(String piva) {
		   String url="jdbc:sqlite:novanta6.db";      
	       String sql = "DELETE FROM Fornitori WHERE  p_iva= ?";
	       try (Connection conn = DriverManager.getConnection(url)){
	    	     if (conn != null) {
	           PreparedStatement pstmt = conn.prepareStatement(sql);
	           pstmt.setString(1,piva);
	           pstmt.executeUpdate();
	           
	    	   }
	       } catch (SQLException e) {
	    	   funzioni.StampaErrore("Operazione non eseguita!");
	             }
	       }
	       
}
