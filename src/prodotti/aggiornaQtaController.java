package prodotti;


import Main.funzioni;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class aggiornaQtaController {

    @FXML
    public TextField id_prodotto;
    @FXML
    private TextField quantita_consumata;
    private graficaController g;

    @FXML
    void Aggiorna_quantita(ActionEvent event)  {
    	
    	
    if(funzioni.verificaSeVuota(id_prodotto.getText())==false && funzioni.verificaSeVuota(quantita_consumata.getText())==false){
    	
    	if(prodottiDB.restituisci_quantita_totale(id_prodotto.getText())!=0){
    		int qta_aggiornata=Integer.parseInt(quantita_consumata.getText());
    		int qta_ut=prodottiDB.restituisci_quantita_utilizzata(id_prodotto.getText());
    	    int qta_totale=prodottiDB.restituisci_quantita_totale(id_prodotto.getText());
    	    int somma=qta_ut+qta_aggiornata;
            //VERIFICO SE LA QUANTITA CHE VOGLIO MODIFICARE E' MINORE RISPETTO A QUELLA IN MAGAZZINO
    	    //SE E' COSI POSSO AGGIORNARLA
    	    if(somma<qta_totale) {
              prodottiDB.Aggiorna_quantita(id_prodotto.getText(),Integer.toString(somma));
             }
    	
            //SE LA QUANTITA CHE VOGLIO AGGIORNARE E' UGUALE A QUELLA DEL MAGAZZINO
    	    //VUOL DIRE CHE HO FINITO IL PRODOTTO QUINDI LO ELIMINO DALLA TABELLA PRODOTTI E LO
    	    //INSERISCO NELL'INVENTARIO
            else  if(somma==qta_totale) {
        	   String nome=prodottiDB.restituisci_nome(id_prodotto.getText());
        	   //CONTROLLO NELL'INVENTARIO CHE NON CI SIA UN PRODOTTO CON LO STESSO CODICE
               if(inventarioDB.verificaSePresente(id_prodotto.getText())==0) {
        	       inventarioDB.AggiungiNellInventario(id_prodotto.getText(), nome,Integer.toString(qta_totale));
        	    }
               //ALTRIMENTI SOMMO LE QUANTITA E AGGIORNO
                else {
        		 int quantitaTotInventario=inventarioDB.restituisci_quantita(id_prodotto.getText());
        		 quantitaTotInventario+=qta_totale;
        		 inventarioDB.Aggiorna_quantita(id_prodotto.getText(), Integer.toString(quantitaTotInventario));
        	     }
              prodottiDB.EliminaProdotto(id_prodotto.getText());
              funzioni.StampaAvviso("Quantita in magazzino esaurita !\n" 
		    	   		+ "Aggiunto Nell'inventario");

             
              }
    	
        else if(somma>qta_totale) {
        	funzioni.StampaErrore("Non puoi aver consumato piu di quanto possiedi!");
        }
        
       
    	 if(g != null)
		      g.Aggiorna("select * from Prodotti");
    	}
        }
 //TEXTFIELD VUOTI
    id_prodotto.setText("");
    quantita_consumata.setText("");

    }
    

	public void setGraficaController(graficaController graficaController) {
		this.g = graficaController;
	}
	
	        
}
