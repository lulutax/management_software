package fornitori;

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

public class fornitoriController implements Initializable{

    @FXML
    private TableColumn<Fornitori, String> colonnaTelefono;
    @FXML
    private TableColumn<Fornitori, String> colonnaNome;
    @FXML
   private TableColumn<Fornitori, String> colonnaEmail;
    @FXML
    private TableColumn<Fornitori, String> colonnaDescrizione;
    @FXML
    private AnchorPane pannelloFornitori;
    @FXML
    private TableView<Fornitori> tabellaFornitori;
    @FXML
    private TableColumn<Fornitori, String> colonnaPiva;
    @FXML
    private TableColumn<Fornitori, String>colonnaIndirizzo;
    ObservableList<Fornitori> oblist =FXCollections.observableArrayList();

    @FXML
    void AggiungiFornitore(ActionEvent event) throws IOException {
    	  FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaAggiungi.fxml"));
      	Parent root = (Parent) loader.load();
         pannelloFornitori.getChildren().setAll(root);
         aggiungiController controller = loader.getController();
     	controller.setGraficaController(this);  
		Aggiorna("select * from Fornitori ");

    }

    @FXML
    void EliminaFornitore(ActionEvent event) throws IOException {
    	 FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaCancella.fxml"));
       	 Parent root = (Parent) loader.load();
          pannelloFornitori.getChildren().setAll(root);
          cancellaFornitoreController controller = loader.getController();
      	controller.setGraficaController(this);  
      	
      	//SE CLICCO SU UNA RIGA DELLA TABELLA INSERISCO LA P.IVA NEL TEXTFIEL DI ELIMINA
      	tabellaFornitori.setOnMouseClicked(new EventHandler<MouseEvent>() {
     	    @Override
     	    public void handle(MouseEvent event) {
     	    	
     	    	try {
     	    	    String a = tabellaFornitori.getSelectionModel().getSelectedItems().get(0).p_iva;
     	    	     controller.piva.setText(a); 
     	    	} catch (NullPointerException e) {
     	    	     controller.piva.setText(""); 
     	    	}
     	    }
     	});
    }

    @FXML
    void cercaFornitore(ActionEvent event) throws IOException {
    	 FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaCerca.fxml"));
        	Parent root = (Parent) loader.load();
           pannelloFornitori.getChildren().setAll(root);
           cercaController controller = loader.getController();
       	controller.setGraficaController(this);  
		Aggiorna("select * from Fornitori ");

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Aggiorna("select * from Fornitori ");
	}
  public void Aggiorna(String s) {
	   
	   oblist.clear();
	   Connection conn=null;
		 try {
			conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
			ResultSet rs=conn.createStatement().executeQuery(s);
			while(rs.next()) {
				oblist.add(new Fornitori(rs.getString("p_iva"),rs.getString("nome"),rs.getString("descrizione"),rs.getString("telefono"),rs.getString("mail"),rs.getString("indirizzo")));

			}
		} catch (SQLException e) {
			funzioni.StampaErrore("Connessione DB mancante!");
		}
		 colonnaPiva.setCellValueFactory(new PropertyValueFactory<>("p_iva"));
		 colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		 colonnaDescrizione.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
		 colonnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		 colonnaEmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		 colonnaIndirizzo.setCellValueFactory(new PropertyValueFactory<>("indirizzo"));
	     tabellaFornitori.setItems(oblist);
	   
  }
  
  
  
}
