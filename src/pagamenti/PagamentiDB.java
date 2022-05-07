package pagamenti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Main.funzioni;


public class PagamentiDB {
	
public PagamentiDB() {}
	
	public void CreaPagamentiDB() {
		String url="jdbc:sqlite:novanta6.db";   
		String sql ="CREATE TABLE IF NOT EXISTS Pagamenti (\n"
				+ "	data date,\n"
				+ "	descrizione varchar(40),\n"
				+ "	importo float (40) ,\n"
				+ "	tipo varchar(40)\n"
				+ ");";
		try (Connection conn = DriverManager.getConnection(url)) {
	        if (conn != null) { 
	        	  PreparedStatement pstmt = conn.prepareStatement(sql);
	              pstmt.executeUpdate();

	        }
	        conn.close();
		}  catch (SQLException e) {
			funzioni.StampaErrore("Tabella pagamenti non creata!");
        }
		}
	//AGGIUNGE UN PRODOTTO NELLA TABELLA PRODOTTI
		public static void AggiungiPagamento( String d, String descr, String imp,String  tip) {

		  String url="jdbc:sqlite:novanta6.db";       
		  String sql = "INSERT INTO Pagamenti(data,descrizione,importo,tipo) VALUES(?,?,?,?)";
	      
		  try {
			 //VERIFICO CHEI DATI PASSATI SIANO INTERI
			 Float valore=Float.parseFloat(imp);
			 try (Connection conn = DriverManager.getConnection(url)) {
			        if (conn != null) {
				       PreparedStatement pstmt = conn.prepareStatement(sql); 
				        pstmt.setString(1, d);
			            pstmt.setString(2, descr);
	                    pstmt.setFloat(3, Float.parseFloat(imp));
				        pstmt.setString(4,tip);
				        pstmt.executeUpdate();
				        }
			        conn.close();

			        } catch (SQLException e) {
			        	funzioni.StampaErrore("Operazione non eseguita!");
			             }
			 }catch (Exception e) {
				 funzioni.StampaErrore("Non hai inserito un numero!");
			  }
		  }
		
		public static void Svuota() {
			String url="jdbc:sqlite:novanta6.db";   
			String sql = "DELETE FROM Pagamenti";
			try (Connection conn = DriverManager.getConnection(url)) {
		        if (conn != null) { 
		        	  PreparedStatement pstmt = conn.prepareStatement(sql);
		              pstmt.executeUpdate();
		        }
			}  catch (SQLException e) {
				funzioni.StampaErrore("Pagamenti è gia vuoto");
 	        }
	     }
	
}
