package pagamenti;

import java.time.format.DateTimeFormatter;

import Main.funzioni;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class aggiungiPagamentoController {

    @FXML
    private TextField descrizione;
    @FXML
    private DatePicker dataPagamento;
    @FXML
    private TextField importo;
    @FXML
    private TextField tipoPagamento;
	private pagamentiController g;

    @FXML
    void Aggiungi(ActionEvent event) {
    	
    	if(importo.getLength()!=0 && dataPagamento.getValue()!=null) {
    	String data=dataPagamento.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    	String descr ="Pagamento";
    	String imp=importo.getText();
    	String tipo="Non specificato";
    	if(funzioni.verificaSeVuota(descrizione.getText())==false)
    		descr=descrizione.getText();
    	if(funzioni.verificaSeVuota(tipoPagamento.getText())==false)
    		tipo=tipoPagamento.getText();
    	
       PagamentiDB.AggiungiPagamento(data,descr,imp,tipo);
       g.Aggiorna("select * from Pagamenti");
    	}
    	else 
    	  funzioni.StampaErrore("Inserisci importo!");
    	
    	descrizione.setText("");
    	dataPagamento.setValue(null);
    	importo.setText("");
    	tipoPagamento.setText("");
    }

	public void setGraficaController(pagamentiController pagamentiController) {
       this.g=pagamentiController;		
	}

	
}
