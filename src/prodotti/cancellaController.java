package prodotti;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class cancellaController {

    @FXML
    public TextField inserisciId;

    private graficaController g;
    @FXML
    void Elimina(ActionEvent event) {
    	
    	
 	if(inserisciId.getLength() != 0) {

    	prodottiDB.EliminaProdotto(inserisciId.getText());
    	if(g != null)
    		g.Aggiorna("select * from Prodotti");
    }
 	inserisciId.setText("");
    }
	public void setGraficaController(graficaController graficaController) {
		this.g = graficaController;

	}

}
