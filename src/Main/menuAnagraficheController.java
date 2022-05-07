package Main;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class menuAnagraficheController {

	
    @FXML
    private Text titolo;
    @FXML
    private Button fornitori;
    @FXML
    private AnchorPane pannello;
    @FXML
    private Button dipendenti;

    @FXML
    void premeDipendenti(MouseEvent event) throws IOException {
    	funzioni.CambiaFx("/dipendenti/GraficaDipendenti.fxml",pannello);
    }
    
    @FXML
    void premoFornitori(MouseEvent event) throws IOException  {
    	funzioni.CambiaFx("/fornitori/graficaFornitori.fxml",pannello);
    }
    
   
}


