package fidelitycard;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import Main.funzioni;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


public class aggiungiController {

    @FXML
    private DatePicker insersciData;
    @FXML
    private TextField cognome;
    @FXML
    private TextField nome;
    @FXML
    private TextField codiceCarta;   
    private fidelityCardController g;

    @FXML
    void Aggiungi(ActionEvent event) throws SQLException {
    	
    	
    	String n=nome.getText();
    	String c=cognome.getText();
    	String id=codiceCarta.getText();
    	String data="01/01/2019";//SE LA DATA NON VINE INSERITA NE VIENE INSERITA UNA DI DEFAULT
    	String n_timbri= "0"; 
    	//APPENA UN CLIENTE FA UNA FIDELITY I TIMBRI SONO IMPOSTATI A 0 
    	
        if(funzioni.verificaSeVuota(id)==false && funzioni.verificaSeVuota(n)==false &&funzioni.verificaSeVuota(c)==false ) {
    		
    		if(insersciData.getValue()!=null) 
    	      data =insersciData.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));            
    		FidelityCardDB.AggiungiFidelity(id, n,c, n_timbri,data);
    		
    		// TEXTFIELD VUOTI	
        	codiceCarta.setText("");
        	nome.setText("");	
        	cognome.setText("");	
        	insersciData.setValue(null);
        	if(g != null) 
        		g.Aggiorna("select * from FidelityCard");
        	
      }else {
    	  funzioni.StampaErrore("Manca qualcosa!");
      }
    }

	public void setGraficaController(fidelityCardController fidelityCardController) {
		this.g=fidelityCardController;
	}
	

	   
}
