package prodotti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Main.funzioni;



public class prodottiDB{

	public prodottiDB() {}
	
	public void CreaProdottiDB() {
		String url="jdbc:sqlite:novanta6.db";   
		String sql ="CREATE TABLE IF NOT EXISTS Prodotti (\n"
				+ "	id  varchar(70) PRIMARY KEY,\n"
				+ "	nome varchar(70) NOT NULL,\n"
				+ "	marca varchar(40),\n"
				+ "	prezzo float (40) ,\n"
				+ "	quantita_utilizzata int,\n"
				+ "	quantita_totale int,\n"
				+ "	datascadenza date\n"
				+ ");";
		try (Connection conn = DriverManager.getConnection(url)) {
	        if (conn != null) { 
	        	  PreparedStatement pstmt = conn.prepareStatement(sql);
	              pstmt.executeUpdate();

	        }
		}  catch (SQLException e) {
			funzioni.StampaErrore("Tabella prodotti non creata!");
        }
		}
	
	//AGGIUNGE UN PRODOTTO NELLA TABELLA PRODOTTI
	public static void AggiungiProdotto( String id, String nome, String marca,String  prezzo, String quantita_ut,String quantita_tot, String dataScadenza) {

	  String url="jdbc:sqlite:novanta6.db";       
	  String sql = "INSERT INTO Prodotti(id,nome,marca,prezzo,quantita_utilizzata,quantita_totale,dataScadenza) VALUES(?,?,?,?,?,?,?)";
      
	  
		 try (Connection conn = DriverManager.getConnection(url)) {
		        if (conn != null) {
			       PreparedStatement pstmt = conn.prepareStatement(sql); 
			        pstmt.setString(1, id );
			        pstmt.setString(2, nome);
		            pstmt.setString(3, marca);
                    pstmt.setFloat(4, Float.parseFloat(prezzo));
     	            pstmt.setInt(5,Integer.parseInt( quantita_ut));
		            pstmt.setInt(6,Integer.parseInt( quantita_tot));
			        pstmt.setString(7,dataScadenza);
			        pstmt.executeUpdate();
			        }
		        conn.close();

		        } catch (SQLException e) {
		        	funzioni.StampaErrore("Operarione non eseguita!");
		             }
		 
	  }
	
	
	//ELIMINA PRODOTTO
	public static  void EliminaProdotto(String id) {
	   String url="jdbc:sqlite:novanta6.db";      
       String sql = "DELETE FROM Prodotti WHERE  id= ?";
      
       try (Connection conn = DriverManager.getConnection(url)){
    	   if (conn != null) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
           pstmt.setString(1,id);
           pstmt.executeUpdate();
           
    	   }
	        conn.close();

       } catch (SQLException e) {
    	   funzioni.StampaErrore("Operarione non eseguita!");
             }
       }
	

	//RESTITUISCI QUANTITA UTILIZZATA
	public static int restituisci_quantita_utilizzata(String id) {
		Connection conn=null;
		int q=0;
       try{
    	   conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
			ResultSet rs=conn.createStatement().executeQuery("select * from Prodotti where id='"+ id +"' ");
    	    q= rs.getInt("quantita_utilizzata");
    	    conn.close();
       } catch (SQLException e) {
    	   funzioni.StampaErrore("Operazione non eseguita!");
       }
       return q;
       }
	
	//RESTITUISCI QUANTITA TOTALE
	public static int restituisci_quantita_totale(String id) {
		Connection conn=null;
		int q=0;
       try{
    	   conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
			ResultSet rs=conn.createStatement().executeQuery("select * from Prodotti where id='"+ id +"' ");
    	    q= rs.getInt("quantita_totale");
    	    conn.close();
       } catch (SQLException e) {
    	   funzioni.StampaErrore("Operazione non eseguita!");
       }
       return q;
       }
	
	//RESTITUISCI NOME
	public static String restituisci_nome(String id) {
		Connection conn=null;
		String q="";
     try{
  	   conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
			ResultSet rs=conn.createStatement().executeQuery("select * from Prodotti where id='"+ id +"' ");
  	    q= rs.getString("nome");
  	    conn.close();
     } catch (SQLException e) {
    	 funzioni.StampaErrore("Operarione non eseguita!");  }
     return q;
     }
	
	//MODIFICA LA QUANTITA
	public static void Aggiorna_quantita( String id_prodotto,String qta) {
	   String url="jdbc:sqlite:novanta6.db";       
		String sql = "UPDATE Prodotti\r\n" + 
				"SET quantita_utilizzata = ? \r\n" + 
				"WHERE id =?;"; 
	   
	  
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
   

	
}

