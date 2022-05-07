package prodotti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Main.funzioni;


public class inventarioDB {
	
	public inventarioDB() {}

	public void CreaInventario() {
		String url="jdbc:sqlite:novanta6.db";   
		String sql = "CREATE TABLE IF NOT EXISTS Inventario (\n"
		                + "	id_prodotto varchar(70) PRIMARY KEY,\n"
		                + "	nome varchar(70) NOT NULL,\n"
		                + "	qta_utilizzata int\n"
		                + ");";
		try (Connection conn = DriverManager.getConnection(url)) {
	        if (conn != null) { 
	        	  PreparedStatement pstmt = conn.prepareStatement(sql);
	              pstmt.executeUpdate();
	        }
		}  catch (SQLException e) {
               System.out.println("Tabella Inventario non creata");
		}
}
	 //INSERISCE I VALORI NELLA TABELLA
	  public static void AggiungiNellInventario( String id, String n, String qta) {

			String url="jdbc:sqlite:novanta6.db";       
			String sql = "INSERT INTO Inventario(id_prodotto,nome,qta_utilizzata) VALUES(?,?,?)";

	
			    try (Connection conn = DriverManager.getConnection(url)) {
			    	if (conn != null) {
					   PreparedStatement pstmt = conn.prepareStatement(sql); 
					   pstmt.setString(1,id );
					   pstmt.setString(2, n);
					   pstmt.setInt(3, Integer.parseInt(qta));
					   pstmt.executeUpdate();
					   }
			    } catch (SQLException e) {
			    	funzioni.StampaErrore("Operarione non eseguita!");
		        }
	  }
	  
	  //SVUOTA LA TABELLA INVENTARIO
		public static void Svuota() {
			String url="jdbc:sqlite:novanta6.db";   
			String sql = "DELETE FROM Inventario";
			try (Connection conn = DriverManager.getConnection(url)) {
		        if (conn != null) { 
		        	  PreparedStatement pstmt = conn.prepareStatement(sql);
		              pstmt.executeUpdate();
		        }
			}  catch (SQLException e) {
				funzioni.StampaErrore("l'inventario è gia vuoto!");
 	        }
	     }
		
		//VERIFICA SE IL PRODOTTO CHE GLI PASSO E' GIA NELLA TABELLA
		//SE E'PRESENTE RESTITUISCE LA QUANTITA ALTRIMENTI 0
		public static int verificaSePresente(String id) {
			Connection conn=null;
		    int q=0;
		    try{
		    	conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
			    ResultSet rs=conn.createStatement().executeQuery("select * from Inventario where id_prodotto='"+ id +"' ");
		        q= rs.getInt("qta_utilizzata");
		  	    conn.close();
		       } catch (SQLException e) {
               }
		       return q;
		   }
		
		
		//AGGIORNA LA QUANTITA DEL PRODOTTO
		public static void Aggiorna_quantita( String id_prodotto,String qta) {
			   String url="jdbc:sqlite:novanta6.db";       
				String sql = "UPDATE Inventario\n" + 
						"SET qta_utilizzata = ? \r\n" + 
						"WHERE id_prodotto =?;"; 
			   
				   try (Connection conn = DriverManager.getConnection(url)) {
	                    if (conn != null) {
	                    	PreparedStatement pstmt = conn.prepareStatement(sql); 
					        pstmt.setInt(1, Integer.parseInt(qta));
				            pstmt.setString(2,id_prodotto );
				            pstmt.executeUpdate();
				            conn.close();
				            }
	                    } catch (SQLException e) {
	                    	funzioni.StampaErrore("Operarione non eseguita!");
						      }
				 }
		
		//QUESTA FUNZIONE SERVIRA' A FAR SI CHE CHE IO POSSA SOMMARE 
		//LA QUANTIA DA AGGIUNGERE A QUELLA GIA PRESENTE
		public static int restituisci_quantita(String id) {
			Connection conn=null;
	    	int q=0;
		       try{
		    	   conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
					ResultSet rs=conn.createStatement().executeQuery("select * from Inventario where id_prodotto='"+ id +"' ");
		    	    q= rs.getInt("qta_utilizzata");
		    	    conn.close();
		       } catch (SQLException e) {
		    	   funzioni.StampaErrore("Operarione non eseguita!");
		    	  }
		       return q;
		   }
		

}
