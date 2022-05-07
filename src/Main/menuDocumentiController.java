package Main;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class menuDocumentiController {

    @FXML
    private AnchorPane Documenti;


    @FXML
    void premiIncassi(ActionEvent event) throws IOException {
    	funzioni.CambiaFx("/incassi/GraficaIncassi.fxml",Documenti);
    }

    @FXML
    void premiPagamenti(ActionEvent event) throws IOException {
    	funzioni.CambiaFx("/pagamenti/graficaPagamenti.fxml",Documenti);
    }


    @FXML
    void premiAltriDocum(ActionEvent event) throws IOException {
    	funzioni.CambiaFx("/altriDocumenti/graficaAltriDocumenti.fxml",Documenti);
    }

    @FXML
    void premiFidelity(ActionEvent event) throws IOException {
    	funzioni.CambiaFx("/fidelitycard/graficaFidelityCard.fxml",Documenti);
    }

}
