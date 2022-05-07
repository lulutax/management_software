package prodotti;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Main.funzioni;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class graficaController implements Initializable {


    @FXML
    private AnchorPane pannello;
    @FXML
    private TableView<Prodotti> tabellaProdotti;
    @FXML
    private TableColumn<Prodotti, String> tabId;
    @FXML
    private TableColumn<Prodotti, String> tabNome;
    @FXML
    private TableColumn< Prodotti, String> tabMarca;
    @FXML
    private TableColumn<Prodotti, String> tabDataScadenza;
    @FXML
    private TableColumn<Prodotti, Float> tabPrezzo;
    @FXML
    private TableColumn<Prodotti, Integer> tabQta_utilizzata;
    @FXML
    private TableColumn<Prodotti, Integer> tabQta_totale;
    ObservableList<Prodotti> oblist =FXCollections.observableArrayList();
    
   
   public graficaController() {}

    @FXML
    void AggiungiProdotto(ActionEvent event) throws SQLException, IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaAggiungiProdotto.fxml"));
    	Parent root = (Parent) loader.load();
        pannello.getChildren().setAll(root);
        Controller controller = loader.getController();
    	controller.setGraficaController(this);
    	Aggiorna("select * from Prodotti");

    }

   @FXML
    void CancellaProdotto(ActionEvent event) throws IOException { 
      FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaEliminaProdotto.fxml"));
      Parent root = (Parent) loader.load();
      pannello.getChildren().setAll(root);
      cancellaController controller = loader.getController();
      controller.setGraficaController(this);  
    	Aggiorna("select * from Prodotti");

      //SE CLICCO SU UNA RIGA DELLA TABELLA INSERISCE L'ID DEL PRODOTTO NEL TEXT FIELD ID PRODOTTO
      tabellaProdotti.setOnMouseClicked(new EventHandler<MouseEvent>() {
 	    @Override
 	    public void handle(MouseEvent event) {
 	    	
 	    	try {
 	    	    String a = tabellaProdotti.getSelectionModel().getSelectedItems().get(0).id;
 	    	     controller.inserisciId.setText(a); 
 	    	} catch (NullPointerException e) {
 	    	     controller.inserisciId.setText(""); 
 	    	}
 	    }
 	});

    }

   @FXML
    void CercaProdotto(ActionEvent event) throws IOException {
	
	 FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaCercaProdotto.fxml"));
 	 Parent root = (Parent) loader.load();
     pannello.getChildren().setAll(root);
     cercaController controller = loader.getController();
 	 controller.setGraficaController(this);
 	 Aggiorna("select * from Prodotti");

   }
	
	
  
	@Override
	public void initialize(URL url, ResourceBundle arg1) {
	Aggiorna("select * from Prodotti");
	}
	
	
	
	
   public void Aggiorna(String s) {
	   
	   oblist.clear();
	   Connection conn=null;
		 try {
			conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
			ResultSet rs=conn.createStatement().executeQuery(s);
			while(rs.next()) {
				oblist.add(new Prodotti(rs.getString("id"),rs.getString("nome"),rs.getString("marca"),rs.getFloat("prezzo"),rs.getInt("quantita_utilizzata"),rs.getInt("quantita_totale"),rs.getString("datascadenza")));

			}
		} catch (SQLException e) {
			funzioni.StampaErrore("Operazione non eseguita!");
		}
		 
		tabId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tabNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tabMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		tabPrezzo.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
		tabQta_utilizzata.setCellValueFactory(new PropertyValueFactory<>("quantita_utilizzata"));
		tabQta_totale.setCellValueFactory(new PropertyValueFactory<>("quantita_totale"));
		tabDataScadenza.setCellValueFactory(new PropertyValueFactory<>("datascadenza"));
   	    tabellaProdotti.setItems(oblist);
	   
	   
   }
   @FXML
   void AggiornaQta(ActionEvent event) throws IOException {
	   
	   FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaAggiornaQuantita.fxml"));
	   Parent root = (Parent) loader.load();
       pannello.getChildren().setAll(root); 
       aggiornaQtaController controller = loader.getController();
	   controller.setGraficaController(this);
	   
	   
	    //SE CLICCO SU UNA RIGA DELLA TABELLA INSERISCE L'ID DEL PRODOTTO NEL TEXT FIELD ID PRODOTTO
	   tabellaProdotti.setOnMouseClicked(new EventHandler<MouseEvent>() {
		   @Override
		   public void handle(MouseEvent event) {
			   try {
	     	    	    String a = tabellaProdotti.getSelectionModel().getSelectedItems().get(0).id;
	     	    	     controller.id_prodotto.setText(a); 
	     	    	} catch (NullPointerException e) {
	     	    	     controller.id_prodotto.setText(""); 
	     	    	}
	     	    }
	     	});
	    	
	    }
	 

}