package Main;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class menuMagazzinoController {
	

    @FXML
    private Text titolo;
    @FXML
    private AnchorPane pannello;    
    @FXML
    private Button prodotti;
    @FXML
    private Button inventario;

    @FXML
    void premiInventario(MouseEvent event) throws IOException {
     funzioni.CambiaFx("/prodotti/GraficaInventario.fxml",pannello);

    }

    @FXML
    void premiProdotto(MouseEvent event) throws IOException {
     funzioni.CambiaFx("/prodotti/GraficaProdotti.fxml",pannello);
    }
    
    

}