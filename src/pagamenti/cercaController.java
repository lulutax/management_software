package pagamenti;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class cercaController implements Initializable{

 
    @FXML
    private TextField cerca;
    @FXML
    private ChoiceBox<String> seleziona;
    @FXML
    private ImageView bottone_cerca;

    private pagamentiController g;

    
    @FXML
    void trova(MouseEvent event) {
    	
    	if(seleziona.getValue()=="Cerca per Descrizione") {
    		if(g != null)
    			g.Aggiorna("select * from Pagamenti where descrizione like '%"+ cerca.getText() +"%' ");
          	cerca.setText("");
    	}
    	
    	if(seleziona.getValue()=="Cerca per Data") {
    		if(g != null)
    			g.Aggiorna("select * from Pagamenti where data='"+ cerca.getText() +"' ");
          	cerca.setText("");
    		
    	}

    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		seleziona.getItems().add("Cerca per Descrizione");
		seleziona.getItems().add("Cerca per Data");
		
		Tooltip c=new Tooltip("Cerca");
		Tooltip.install(bottone_cerca, c);		
	}
	
	public void setGraficaController(pagamentiController pagamentiController) {
	      this.g=pagamentiController;		
		}


	

}
