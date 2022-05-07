package incassi;

import java.time.format.DateTimeFormatter;
import Main.funzioni;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


public class aggiungiIncassoController {

    @FXML
    private DatePicker data_incasso;
    @FXML
    private TextField incasso;
	private incassiController g;

    @FXML
    void aggiungi(ActionEvent event) {
    	if(data_incasso.getValue()!=null ) {
    	   incassiDB.AggiungiIncasso( data_incasso.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), incasso.getText());
           g.Aggiorna("select * from Incassi");
    	}
    	else {
    		funzioni.StampaErrore("Non fare l'evasore! \n Inserisci la data.");
    	}
    }

	public void setGraficaController(incassiController incassiController) {
      this.g=incassiController;		
	}
	
}
