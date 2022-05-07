package dipendenti;

import Main.funzioni;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;

public class AggiungiController implements Initializable {

    @FXML
    private DatePicker DataAssunzione;
    @FXML
    private TextField Stipendio;
    @FXML
    private TextField Mansione;
    @FXML
    private DatePicker Datadinascita;
    @FXML
    private TextField Nome;
    @FXML
    private ChoiceBox<String> Sesso;
    @FXML
    private TextField CodiceFiscale;
    @FXML
    private TextField Stato;

    @FXML
    private TextField Cognome;
    private dipendentiController g;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	Sesso.getItems().add("M");
	Sesso.getItems().add("F");
	}
    
    @FXML
    void Aggiungi(ActionEvent event) {
     	
    	String cf=CodiceFiscale.getText();
    	String nome=Nome.getText();
    	String stato="Non specificato";
    	String cogn=Cognome.getText();
    	String stip="0.0";
    	String sess="Non specificato";
    	String datan="";
    	String dataA="";
    	String mans="Non specificata";
    	
    	String espressione= "[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
    
    	
    	
    	if(!funzioni.confronta(espressione,cf) ) {
    		 funzioni.StampaErrore("Attenzione inserire codice fiscale valido!");
    	}
    	else if(funzioni.verificaSeVuota(cf)==false && funzioni.verificaSeVuota(nome)==false && funzioni.verificaSeVuota(cogn)==false  && DataAssunzione.getValue()!=null && Datadinascita.getValue()!=null) {
    		
    		if(funzioni.confrontaData(Datadinascita,DataAssunzione)) {
        		datan= Datadinascita.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        		dataA=DataAssunzione.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        	if(funzioni.verificaSeVuota(Stipendio.getText())==false)
        		stip=Stipendio.getText();
        	if(funzioni.verificaSeVuota(Stato.getText())==false)
        		stato=Stato.getText();
            if(Sesso.getValue()!=null)
        		sess=Sesso.getValue();
        	if(funzioni.verificaSeVuota(Mansione.getText())==false)
        		mans=Mansione.getText();
        	
           dipendentiDB.AggiungiDipendente(cf,nome,cogn,datan,dataA , sess, mans,stato, stip);
           g.Aggiorna("select * from Dipendenti");
    		
           CodiceFiscale.setText("");
           Nome.setText("");
           Cognome.setText("");
           Datadinascita.setValue(null);
           DataAssunzione.setValue(null);
           Stipendio.setText("");
           Sesso.setValue(null);
           Mansione.setText("");
           Stato.setText("");
    		}
    		else {
    	    	  funzioni.StampaErrore("La data inserita non è valida!");

    		}
    	}
    	else 
    	  funzioni.StampaErrore("Manca qualcosa!");
    }
    
    
    
	public void setGraficaController(dipendentiController g) {
		this.g = g;
	}
	
	




}