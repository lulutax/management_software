package fornitori;

import Main.funzioni;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class aggiungiController {

    @FXML
    private TextField descrizione;
    @FXML
    private TextField mail;
    @FXML
    private TextField PartitaIva;
    @FXML
    private TextField indirizzo;
    @FXML
    private TextField Nome;
    @FXML
    private TextField telefono;
	private fornitoriController g;
    
    @FXML
    void Aggiungi(ActionEvent event) {
    	
      String tel,ind,em,desc,nome,p_iva;
      tel=telefono.getText();
      nome=Nome.getText();
   	  p_iva=PartitaIva.getText();
   	  em=mail.getText();
      ind=indirizzo.getText();
   	  desc=descrizione.getText();
   	  
   	  String espressione="[0-9]{11}";
   	  String espressioneEmail="[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
   	  
   	if(!funzioni.confronta(espressione, p_iva)) {
  		 funzioni.StampaErrore("Attenzione inserire partita Iva valida!");
   	 }
   	
   	else if(em.length()>0 &&!funzioni.confronta(espressioneEmail, em)) {
   	 funzioni.StampaErrore("Attenzione inserire e-mail valida!");
   	}
    else if(funzioni.verificaSeVuota(nome)==false) {
    	
    	
   		   if(funzioni.verificaSeVuota(tel)==true)
   		     tel="Non specificato";
   	       if(funzioni.verificaSeVuota(em)==true)
   		      em="Non specificato";
   	       if(funzioni.verificaSeVuota(ind)==true)
   		       ind="Non specificato";
   	       if(funzioni.verificaSeVuota(desc)==true)
   		      desc="Non specificato";
     FornitoriDB.AggiungiFornitore(p_iva, nome, desc, tel, em, ind);
     g.Aggiorna("select * from Fornitori");
   	   
     PartitaIva.setText("");
     descrizione.setText("");
     mail.setText("");
     Nome.setText("");
     indirizzo.setText("");
     telefono.setText("");
	}
   
    	
    	else 
      	  funzioni.StampaErrore("Manca qualcosa!");
    	
  }
    
	public void setGraficaController(fornitoriController fornitoriController) {
		this.g=fornitoriController;
	}
	
	
}