package dipendenti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Main.funzioni;


public class dipendentiDB {

	public dipendentiDB() {}
    
	public void CreaDipendentiDB() {
		String url="jdbc:sqlite:novanta6.db";  
		
		String sql =  "CREATE TABLE IF NOT EXISTS Dipendenti (\n"
			     + "	codiceFiscale varchar(30) PRIMARY KEY,\n"
			     + "	nome varchar(30) NOT NULL,\n"
			     + "	cognome varchar(30) NOT NULL,\n"
			     + "	datadiNascita date ,\n"
			     + "	dataAssunzione date,\n"
			     + "	sesso varchar(10),\n"
			     + "	mansione varchar(40),\n"
			     + "	stato varchar(20),\n"
			     + "	stipendio float(40)\n"
			     + ");";
		try (Connection conn = DriverManager.getConnection(url)) {
	        if (conn != null) { 
	        	  PreparedStatement pstmt = conn.prepareStatement(sql);
	              pstmt.executeUpdate();
	        }
		}  catch (SQLException e) {
			funzioni.StampaErrore(" Tabella Dipendenti non creata");        }
}
	
	
	
	 public static void AggiungiDipendente( String cf, String nome, String cognome, String  dataN,String dataA, String sesso,String mansione,String stato,String stipendio) {
			
			String url="jdbc:sqlite:novanta6.db";       
			String sql = "INSERT INTO Dipendenti(codiceFiscale,nome,cognome,datadiNascita,dataAssunzione,sesso,mansione,stato,stipendio) VALUES(?,?,?,?,?,?,?,?,?)";
           try {
        	   float value = Float.parseFloat(stipendio);
		    try (Connection conn = DriverManager.getConnection(url)) {
		        if (conn != null) {
			       PreparedStatement pstmt = conn.prepareStatement(sql); 
			            pstmt.setString(1,cf );
			            pstmt.setString(2, nome);
			            pstmt.setString(3, cognome);
			            pstmt.setString(4,dataN);
			            pstmt.setString(5,dataA);
			            pstmt.setString(6,sesso);
			            pstmt.setString(7,mansione);
			            pstmt.setString(8, stato);
			            pstmt.setFloat(9, Float.parseFloat(stipendio));
			           pstmt.executeUpdate();
		               }
                conn.close();

			        } catch (SQLException e) {
			        	funzioni.StampaErrore("Operazione non eseguita!");
			        }
           }catch(Exception e) {
		        funzioni.StampaErrore("Non hai inserito uno stipendio!");

           }
			    
		}
	
	 public static void Aggiorna_stipendio( String cf,String s) {
		   String url="jdbc:sqlite:novanta6.db";       
			String sql = "UPDATE Dipendenti\r\n" + 
					"SET stipendio = ? \r\n" + 
					"WHERE codiceFiscale =?;"; 
		   
		   try {
			       int valore = (int) Float.parseFloat( s);
			       try (Connection conn = DriverManager.getConnection(url)) {
				        if (conn != null) {
					       PreparedStatement pstmt = conn.prepareStatement(sql); 
				           pstmt.setFloat(1, Float.parseFloat( s));
			               pstmt.setString(2,cf);
			               pstmt.executeUpdate();
			                 conn.close();
				               }
					        } catch (SQLException e) {
					        	funzioni.StampaErrore("Operazione non eseguita!");
			
					            }
			 }catch (Exception e) {
				 funzioni.StampaErrore("Non hai inserito uno stipendio!");
			    }
	
		}
	 
	 
	 
	 public static void Aggiorna_stato( String cf,String s) {
		   String url="jdbc:sqlite:novanta6.db";       
			String sql = "UPDATE Dipendenti\r\n" + 
					"SET stato = ? \r\n" + 
					"WHERE codiceFiscale =?;"; 
		   
			   try (Connection conn = DriverManager.getConnection(url)) {
				   if (conn != null) {
					       PreparedStatement pstmt = conn.prepareStatement(sql); 
				           pstmt.setString(1, s);
			               pstmt.setString(2,cf);
			               pstmt.executeUpdate();
			                 conn.close();
				               }
					        } catch (SQLException e) {
					        	funzioni.StampaErrore("Operazione non eseguita!");		
					            }
			 }

    public static void Aggiorna_mansione( String cf,String m) {
	   String url="jdbc:sqlite:novanta6.db";       
       String sql = "UPDATE Dipendenti\r\n" + 
					"SET mansione = ? \r\n" + 
					"WHERE codiceFiscale =?;"; 
		
       try (Connection conn = DriverManager.getConnection(url)) {
			 if (conn != null) {
				 PreparedStatement pstmt = conn.prepareStatement(sql); 
				 pstmt.setString(1, m);
				 pstmt.setString(2,cf);
				 pstmt.executeUpdate();
				 conn.close();
				}
			 } catch (SQLException e) {
				 funzioni.StampaErrore("Operazione non eseguita!");			
			}
		}
    
    
    public static void Svuota() {
		String url="jdbc:sqlite:novanta6.db";   
		String sql = "DELETE FROM Dipendenti";
		try (Connection conn = DriverManager.getConnection(url)) {
	        if (conn != null) { 
	        	  PreparedStatement pstmt = conn.prepareStatement(sql);
	              pstmt.executeUpdate();
	        }
		}  catch (SQLException e) {
			funzioni.StampaErrore("Dipendenti è gia vuoto");
	        }
     }
 
 
}
