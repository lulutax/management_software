package dipendenti;


import java.sql.SQLException;

import Main.funzioni;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class aggiornaDipendenteController {

	    @FXML
	    private TextField aggiorna;
	    @FXML
	    public TextField codiceFiscale;
	    private dipendentiController g;
       
	    @FXML
	    void aggiornaStipendio(ActionEvent event) throws SQLException {
	    	if(codiceFiscale.getLength()!=0 && funzioni.verificaSeVuota(aggiorna.getText())==false) {
        	   dipendentiDB.Aggiorna_stipendio(codiceFiscale.getText(), aggiorna.getText());
		        g.Aggiorna("select * from Dipendenti");
             }
	    }

	    @FXML
	    void aggiornaStato(ActionEvent event) throws SQLException {
	    	if(codiceFiscale.getLength()!=0 && funzioni.verificaSeVuota(aggiorna.getText())==false) {
	        	   dipendentiDB.Aggiorna_stato(codiceFiscale.getText(), aggiorna.getText());
	    		   g.Aggiorna("select * from Dipendenti");
	         }
	      }

	    @FXML
	    void aggiornaMansione(ActionEvent event) throws SQLException {
	    	if(codiceFiscale.getLength()!=0 && funzioni.verificaSeVuota(aggiorna.getText())==false){
	        	   dipendentiDB.Aggiorna_mansione(codiceFiscale.getText(), aggiorna.getText());
	    		   g.Aggiorna("select * from Dipendenti");
            }
	    }

	   public void setGraficaController(dipendentiController dipendentiController) {
			this.g = dipendentiController;
		}

       
	}


