package fidelitycard;


import java.sql.SQLException;
import Main.funzioni;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class aggiornaTimbriController {

    @FXML
    public TextField codice_carta;

    @FXML
    private TextField numero_timbri;

	private fidelityCardController g;

    @FXML
    void AggiornaNumeroTimbri(ActionEvent event) throws SQLException {
    
      if(codice_carta.getLength()!=0 && numero_timbri.getLength()!=0 ) {
    	  try {
    		 int n_timbri=Integer.parseInt(numero_timbri.getText()); 
    		 if(FidelityCardDB.restituisciNumTimbri(codice_carta.getText()) <= n_timbri) {
    		        FidelityCardDB.AggiornaNumeroTimbri(codice_carta.getText(), numero_timbri.getText());
    		        }
    		 else 
    			 funzioni.StampaErrore("Non possono diminuire i timbri!");
    		    	  
    	  }catch(Exception e) {
    		  funzioni.StampaErrore("Campo non valido!");

    	  }
    	 
      if(g != null) 
    	g.Aggiorna("select * from FidelityCard");
      codice_carta.setText("");
      numero_timbri.setText("");
      }
      else
    	  funzioni.StampaErrore("Manca qualcosa!");
    }

	public void setGraficaController(fidelityCardController fidelityCardController) {
		this.g=fidelityCardController;
	}

}
