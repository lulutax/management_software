package pagamenti;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import Main.funzioni;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class pagamentiController implements Initializable{

    @FXML
    private TableColumn<Pagamenti, String> colonnaDescrizione;

    @FXML
    private TableColumn<Pagamenti, String> colonnaTipoPagamento;

    @FXML
    private TableColumn<Pagamenti, Float> colonnaImporto;

    @FXML
    private TableColumn<Pagamenti, String> colonnaData;

    @FXML
    private AnchorPane pannello;

    @FXML
    private TableView<Pagamenti> tabellaPagamenti;
    ObservableList<Pagamenti> oblist =FXCollections.observableArrayList();

    
    
    @FXML
    void svuotaPagamenti(ActionEvent event) {
    	  Alert alert =new Alert(AlertType.CONFIRMATION);
          alert.setTitle("Confirmation Dialog");
          alert.setHeaderText(null);
          alert.setContentText(" Sei sicuro di voler svuotare Pagamenti ?");
          Optional <ButtonType> action =alert.showAndWait();
           if(action.get()==ButtonType.OK) {
           	PagamentiDB.Svuota();
               Aggiorna("select * from Pagamenti");
           }
    }
    @FXML
    void AggiungiPagamento(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaAggiungi.fxml"));
    	Parent root = (Parent) loader.load();
        pannello.getChildren().setAll(root);
        aggiungiPagamentoController controller = loader.getController();
    	controller.setGraficaController(this);
        Aggiorna("select * from Pagamenti");

    }


    @FXML
    void CercaPagamento(ActionEvent event) throws IOException {
      	FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaCerca.fxml"));
    	Parent root = (Parent) loader.load();
        pannello.getChildren().setAll(root);
        cercaController controller = loader.getController();
    	controller.setGraficaController(this);
        Aggiorna("select * from Pagamenti");

    }

	
    public void Aggiorna(String q) {
 	   
 	   oblist.clear();
 	   Connection conn=null;
 		 try {
 			conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
 			ResultSet rs=conn.createStatement().executeQuery(q);
 			while(rs.next()) {
 				oblist.add(new Pagamenti(rs.getString("data"),rs.getString("descrizione"),rs.getFloat("importo"),rs.getString("tipo")));

 			}
 		} catch (SQLException e) {
 			funzioni.StampaErrore("Operazione non eseguita!");
 		}
 		 
 		colonnaData.setCellValueFactory(new PropertyValueFactory<>("data"));
 		colonnaDescrizione.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
 		colonnaImporto.setCellValueFactory(new PropertyValueFactory<>("importo"));
 		colonnaTipoPagamento.setCellValueFactory(new PropertyValueFactory<>("tipo"));
 	    tabellaPagamenti.setItems(oblist);
 	   
 	   
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Aggiorna("select * from Pagamenti");
	}

}
