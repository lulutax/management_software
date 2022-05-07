package incassi;

import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class eliminaController {

    @FXML
    public DatePicker dataIncasso;
	private incassiController g;

    @FXML
    void Elimina(ActionEvent event) {
    	if(dataIncasso.getValue()!=null) {
         incassiDB.Elimina(dataIncasso.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
         g.Aggiorna("select * from Incassi");
    	}
    }

	public void setGraficaController(incassiController incassiController) {
     this.g=incassiController;
	}

}
