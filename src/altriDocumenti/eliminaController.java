package altriDocumenti;

import java.io.File;
import java.util.Optional;
import Main.funzioni;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class eliminaController{

    @FXML
    public TextField nomeFile;
	private altriDocumentiController g;

	@FXML
    void Elimina(ActionEvent event) {
    
		String n=nomeFile.getText();
        
		if(funzioni.verificaSeVuota(n)==false) {
			
			//PRENDO IL FILE CHE HO SALVATO NEL PC
    		String f= altriDocumentiDB.restituisci_file(n);
  	        File file = new File(f);
  	        
  	        if(!file.delete() ) {
  	        	 Alert alert =new Alert(AlertType.CONFIRMATION);
  	              alert.setTitle("Confirmation Dialog");
  	              alert.setHeaderText(null);
  	              alert.setContentText("File non trovato o aperto.Vuoi eliminare lo stesso il file dal db?");
  	              Optional <ButtonType> action =alert.showAndWait();
  	             
  	              if(action.get()==ButtonType.OK) {
  	            	   altriDocumentiDB.EliminaDocumento(n);
  	     			  nomeFile.setText("");
  	     			  g.Aggiorna("select * from AltriDocumenti");
  	               }
        		}else {
        			  altriDocumentiDB.EliminaDocumento(n);
        			  nomeFile.setText("");
        			  g.Aggiorna("select * from AltriDocumenti");
        			  }
        			}
    }

	public void setGraficaController(altriDocumentiController altriDocumentiController) {
     this.g=altriDocumentiController;		
	}

}
