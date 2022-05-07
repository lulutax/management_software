package prodotti;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class inventarioController implements Initializable{

    @FXML
    private TableColumn<Inventario, String> colonnaNomeProdotto;

    @FXML
    private TableView<Inventario> tabellaInventario;

    @FXML
    private TableColumn<Inventario, Integer> colonnaQta;

    @FXML
    private TableColumn<Inventario, String> colonna_Id;
    ObservableList<Inventario> oblist =FXCollections.observableArrayList();
   
    @FXML
    void svuotaInventario(ActionEvent event) {
       Alert alert =new Alert(AlertType.CONFIRMATION);
       alert.setTitle("Confirmation Dialog");
       alert.setHeaderText(null);
       alert.setContentText(" Sei sicuro di voler svuotare l'inventario ?");
    	Optional <ButtonType> action =alert.showAndWait();
        if(action.get()==ButtonType.OK) {
        	inventarioDB.Svuota();
            Aggiorna();
        }
    	
    }
 @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Aggiorna();  
	}
    
	
	//VISUALIZZA I DATI SULLA TABELLA INVENTARIO
    public void Aggiorna() {
     oblist.clear();
     Connection conn=null;
     try {
    	 conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
    	 ResultSet rs=conn.createStatement().executeQuery("select * from Inventario");
	     while(rs.next()) {
	    	 oblist.add(new Inventario(rs.getString("id_prodotto"),rs.getString("nome"),rs.getInt("qta_utilizzata")));
             }
			} catch (SQLException e) {
				funzioni.StampaErrore("Operazione non eseguita!");
			}
			 colonna_Id.setCellValueFactory(new PropertyValueFactory<>("id_prodotto"));
			 colonnaNomeProdotto.setCellValueFactory(new PropertyValueFactory<>("nome"));
			 colonnaQta.setCellValueFactory(new PropertyValueFactory<>("qta_utilizzata"));
			 tabellaInventario.setItems(oblist);
    }

}

