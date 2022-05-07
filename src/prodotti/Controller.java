package prodotti;

import java.time.format.DateTimeFormatter;
import Main.funzioni;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Controller {
        @FXML
	    private TextField inserisciId;
	    @FXML
	    private DatePicker insersciData;
	    @FXML
	    private TextField inserisciNome;
	    @FXML
	    private TextField inserisciPrezzo;
	    @FXML
	    private TextField inserisciMarca;
	    @FXML
	    private TextField inserisciQta_tot;
	    @FXML
	    private TextField inserisciQta_utilizzata;      
	    private graficaController g;
	   
    @FXML
    void Aggiungi(ActionEvent event) {
    	
    	
    	String marca=inserisciMarca.getText();
    	String qtatot=inserisciQta_tot.getText();
    	String qtaut=inserisciQta_utilizzata.getText();
    	String prezzo=inserisciPrezzo.getText();
    	String id=inserisciId.getText();
    	String nome=inserisciNome.getText();
    	String data="";
    	
    	  String espressione="[0-9]+";
    	  
    	  if(!funzioni.confronta(espressione, id)) {
    		  funzioni.StampaErrore("Id non valido"); 
    	  }
    	
    	  else	if( funzioni.verificaSeVuota(inserisciId.getText())==false && funzioni.verificaSeVuota(inserisciNome.getText())==false) {
    		
    		//IMPOSTO I CAMPI VUOTI CON DEI VALORI DI DEFAULT
    		if(funzioni.verificaSeVuota(marca)==true) 
  			    marca= " ";
    		if(prezzo.length()==0)
  			   prezzo="0.0";
    		if(insersciData.getValue()!=null)
  	    	  data =insersciData.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
  	     	if (data.length()==0) 
               data="01/01/2019";
		    if(qtaut.length()==0)
			   qtaut="0";
	    	if(qtatot.length()==0)
			   qtatot="1";
	    	
	    	//SE I CAMPI DELLE QUANTITA NON SONO VUOTI
	    	//VADO A VERIFICARE CHE SIANO INTERI E CHE
	    	//QUANTITA_UT SIA MINORE DI QUANTITA_TOT
    		if(!qtatot.isEmpty() && !qtaut.isEmpty()) {
    		 try {
    			int value =Integer.parseInt(qtaut);
    			int value2= Integer.parseInt(qtatot);
    			float value3=Float.parseFloat(prezzo);
    			if(value>value2) {
        			funzioni.StampaErrore("Errore! \n La quantità utilizzata non può essere maggiore di quella totale!"); 
    		    	}
    			else {
    	    		prodottiDB.AggiungiProdotto(id, nome,marca, prezzo,qtaut ,qtatot, data);
    			}
    		}catch (Exception e) {
    			funzioni.StampaErrore("Campo non valido!");
    		   }
		     }
    		else //AGGIUNGO IL PRODOTTO
    		 prodottiDB.AggiungiProdotto(id, nome,marca, prezzo,qtaut ,qtatot, data);
     	    
    		//AGGIORNO LA VISUALIZZAZIONE DELLA TABELLA
        	if(g != null)
        		g.Aggiorna("select * from Prodotti");
        	
        	
        	 //TEXTFIELD VUOTI	
        	inserisciId.setText("");
        	inserisciPrezzo.setText("");	
        	inserisciMarca.setText("");	
        	inserisciQta_utilizzata.setText("");	
        	inserisciQta_tot.setText("");	
        	inserisciNome.setText("");
        	insersciData.setValue(null);
    	
    	}else {
    		funzioni.StampaErrore("Manca qualcosa!");
    	}
    	}
    
    
	public void setGraficaController(graficaController controller) {
		this.g = controller;
	}

	
}
