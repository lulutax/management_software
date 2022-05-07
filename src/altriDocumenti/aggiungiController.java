package altriDocumenti;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import Main.funzioni;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;


public class aggiungiController implements Initializable{

    @FXML
    private TextField nomeFile;
    @FXML
    private TextField file;
	private altriDocumentiController g;
	@FXML
    private Button b_allega;
    @FXML
    private Button b_aggiungi;
	
	//AGGIUNGO NEL DB
    @FXML
    void aggiungi(ActionEvent event) throws FileNotFoundException {
    	
    	if(funzioni.verificaSeVuota(file.getText())==false && funzioni.verificaSeVuota(nomeFile.getText())==false) {
    	  altriDocumentiDB.AggiungiDocumento(nomeFile.getText(), file.getText());
          g.Aggiorna("select * from AltriDocumenti ");
          //SVUOTA I TEXT FIELT
          nomeFile.setText("");
          file.setText("");
    	}else 
    		funzioni.StampaErrore("Manca qualcosa!");
    }

   //SCELGO IL FILE DA ALLEGARE E LO SALVO IN UNA CARTELLA A MIA SCELTA 
    @FXML
    void Allega(ActionEvent event) throws IOException {
    	
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("Text file ", " *.txt"), new FileChooser.ExtensionFilter("PDF", " *.pdf"));
    	fileChooser.setTitle("Scegli");
        File fileSelezionato = fileChooser.showOpenDialog(null);
    	

    	if (fileSelezionato!=null) {
        	fileChooser.setTitle("Scegli dove salvarlo");
    		File fileSalvato = fileChooser.showSaveDialog(null);
    		if(fileSalvato!=null) {
                file.setText(fileSalvato.getAbsolutePath());            
    		   salvaFileScelto(fileSelezionato, fileSalvato);
            }	 
    	}
    }
   
    
    //COPIO IL FILE SCELTO NEL PATH SELEZIONATO
	private void salvaFileScelto(File fileSelezionato, File fileSalvato) throws IOException {
		Files.copy(fileSelezionato.toPath(), fileSalvato.toPath());
		
	}
	
	public void setGraficaController(altriDocumentiController altriDocumentiController) {
          this.g=altriDocumentiController;		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Tooltip t_all=new Tooltip("Allega");
		Tooltip t_agg=new Tooltip("Aggiungi");
		Tooltip.install(b_allega, t_all);
		Tooltip.install(b_aggiungi, t_agg);
	}
        

}
