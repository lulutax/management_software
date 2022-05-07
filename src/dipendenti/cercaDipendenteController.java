package dipendenti;

import java.net.URL;
import java.util.ResourceBundle;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class cercaDipendenteController implements Initializable {

   
	
	    @FXML TextField cerca;
	    @FXML
	    private ChoiceBox<String> seleziona;
	    @FXML
	    private ImageView bottone_cerca;
		private dipendentiController g;
		

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			seleziona.getItems().add("cerca Per Nome");
			seleziona.getItems().add("cerca Per Cf");
			
			Tooltip c=new Tooltip("Cerca");
			Tooltip.install(bottone_cerca, c);		
		}

	    @FXML
	    void trova(MouseEvent event) {
	    	
	    	if(seleziona.getValue()=="cerca Per Cf") {
	    		if(g != null)
	        		 g.Aggiorna("select * from Dipendenti where codiceFiscale like'%"+ cerca.getText() +"%' " );
	          	cerca.setText("");
	    	}
	    	
	    	if(seleziona.getValue()=="cerca Per Nome") {
	    		if(g != null) 
	         		 g.Aggiorna("select * from Dipendenti where nome like'%"+ cerca.getText() +"%' " );
	          	cerca.setText("");
	    		
	    	}

	    }


		
		public void setGraficaController(dipendentiController dipendentiController) {
			// TODO Auto-generated method stub
			this.g = dipendentiController;

		}



}
