package prodotti;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class cercaController implements Initializable {

	
	    @FXML
	    private TextField cerca;
	    @FXML
	    private ChoiceBox<String> seleziona;
	    @FXML
	    private ImageView bottone_cerca;
		private graficaController g;

		
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			seleziona.getItems().add("cerca per ID");
			seleziona.getItems().add("cerca per Nome");
			
			Tooltip c=new Tooltip("Cerca");
			Tooltip.install(bottone_cerca, c);		
		}
		
	    @FXML
	    void trova(MouseEvent event) {
	    	
	    	if(seleziona.getValue()=="cerca per ID") {
	    		if(g != null)
	        		g.Aggiorna("select * from Prodotti where id like '%" + cerca.getText()+"%' ");
	          	cerca.setText("");
	    	}
	    	
	    	if(seleziona.getValue()=="cerca per Nome") {
	    		if(g != null)
	        		g.Aggiorna("select * from Prodotti where nome like '%" + cerca.getText()+"%' ");
	          	cerca.setText("");
	    		
	    	}

	    }


	  public void setGraficaController(graficaController graficaController) {
				this.g = graficaController;

			}



	

}
