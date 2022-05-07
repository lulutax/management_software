package fidelitycard;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EliminaController {

    @FXML
    public TextField codice_carta;
	private fidelityCardController g;

    @FXML
    void EliminaFidelity(ActionEvent event) throws SQLException {
    	
    	FidelityCardDB.EliminaFidelity(codice_carta.getText());
        if(g != null)
		   g.Aggiorna("select * from FidelityCard"); 
          
     codice_carta.setText("");
    }

	public void setGraficaController(fidelityCardController fidelityCardController) {
		this.g=fidelityCardController;
	}
	
	

}
