package altriDocumenti;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class cercaController {

    @FXML
    private TextField cerca;
	private altriDocumentiController g;

    @FXML
    void cercaFile(ActionEvent event) {
       g.Aggiorna("select * from AltriDocumenti where nome like'%"+ cerca.getText() +"%' " );
    }

	public void setGraficaController(altriDocumentiController altriDocumentiController) {
		this.g=altriDocumentiController;
	}

}
