package fornitori;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class cancellaFornitoreController {

    @FXML
    public TextField piva;
	private fornitoriController g;

    @FXML
    void Elimina(ActionEvent event) {
        FornitoriDB.EliminaFornitore(piva.getText());
        g.Aggiorna("select * from Fornitori ");
        piva.setText("");
    }

	public void setGraficaController(fornitoriController fornitoriController) {
		this.g=fornitoriController;
	}

    
    
}

