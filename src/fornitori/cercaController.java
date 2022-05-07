package fornitori;


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

		private fornitoriController g;

	    @FXML
	    void trova(MouseEvent event) {
	    	
	    	if(seleziona.getValue()=="cerca per P_IVA") {
	    		if(g != null)
	    			g.Aggiorna("select * from Fornitori where p_iva%'"+ cerca.getText() +"%' ");
	          	cerca.setText("");
	    	}
	    	
	    	if(seleziona.getValue()=="cerca per Nome") {
	    		if(g != null)
	    			g.Aggiorna("select * from Fornitori where nome like'%"+ cerca.getText() +"%' ");
	          	cerca.setText("");
	    		
	    	}

	    }


		@Override
		public void initialize(URL location, ResourceBundle resources) {
			seleziona.getItems().add("cerca per P_IVA");
			seleziona.getItems().add("cerca per Nome");
			
			Tooltip c=new Tooltip("Cerca");
			Tooltip.install(bottone_cerca, c);		
		}
		
		public void setGraficaController(fornitoriController fornitoriController) {
			// TODO Auto-generated method stub
			this.g=fornitoriController;
		}


}
