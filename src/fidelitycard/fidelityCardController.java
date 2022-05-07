package fidelitycard;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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

public class fidelityCardController implements Initializable {

    @FXML
    private TableColumn<FidelityCard, String> ColonnaDataRilascio;
    @FXML
    private TableView<FidelityCard> TabellaFidelity;
    @FXML
    private TableColumn<FidelityCard, String> ColonnaCodiceCarta;
    @FXML
    private TableColumn<FidelityCard, String> ColonnaNome;
    @FXML
    private TableColumn<FidelityCard, String> ColonnaCognome;
    @FXML
    private TableColumn<FidelityCard, Integer> ColonnaNumTimbri;
    @FXML
    private AnchorPane pannelloFidelity;
    ObservableList<FidelityCard> oblist =FXCollections.observableArrayList();

    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Aggiorna("select * from FidelityCard");
	}
    @FXML
    void AggiungiFidelity(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaAggiungi.fxml"));
    	Parent root = (Parent) loader.load();
    	pannelloFidelity.getChildren().setAll(root);
        aggiungiController controller = loader.getController();
    	controller.setGraficaController(this);
		Aggiorna("select * from FidelityCard");

    }

    @FXML
    void EliminaFidelity(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaElimina.fxml"));
    	Parent root = (Parent) loader.load();
    	pannelloFidelity.getChildren().setAll(root);
        EliminaController controller = loader.getController();
    	controller.setGraficaController(this);
    	
    	//SE CLICCO SU UNA RIGA DELLA TABELLA METTE IL CODICE CARTA NEL TEXTFIELD DI ELIMINA
    	TabellaFidelity.setOnMouseClicked(new EventHandler<MouseEvent>() {
     	    @Override
     	    public void handle(MouseEvent event) {
     	    	try {
     	    	    String a = TabellaFidelity.getSelectionModel().getSelectedItems().get(0).codice;
     	    	     controller.codice_carta.setText(a); 
     	    	} catch (NullPointerException e) {
     	    	     controller.codice_carta.setText(""); 
     	    	}
     	    }
     	});
    }

    @FXML
    void CercaFidelity(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaCerca.fxml"));
    	Parent root = (Parent) loader.load();
    	pannelloFidelity.getChildren().setAll(root);
        cercaController controller = loader.getController();
    	controller.setGraficaController(this);
		Aggiorna("select * from FidelityCard");

    }

    @FXML
    void AggiornaNumeroTimbri(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaAggiornaTimbri.fxml"));
    	Parent root = (Parent) loader.load();
    	pannelloFidelity.getChildren().setAll(root);
        aggiornaTimbriController controller = loader.getController();
    	controller.setGraficaController(this);

    	//SE CLICCO SU UNA RIGA DELLA TABELLA METTE IL CODICE CARTA NEL TEXTFIELD DI AGGIORNA
    	TabellaFidelity.setOnMouseClicked(new EventHandler<MouseEvent>() {
     	    @Override
     	    public void handle(MouseEvent event) {
     	    	
     	    	try {
     	    	    String a = TabellaFidelity.getSelectionModel().getSelectedItems().get(0).codice;
     	    	     controller.codice_carta.setText(a); 
     	    	} catch (NullPointerException e) {
     	    	     controller.codice_carta.setText(""); 
     	    	}
     	    }
     	});

    }


	
	  public void Aggiorna(String s) {
		  oblist.clear();
		  Connection conn=null;
			 try {
				conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
				ResultSet rs=conn.createStatement().executeQuery(s);
				while(rs.next()) {
			          oblist.add(new FidelityCard(rs.getString("codice"),rs.getString("nome"),rs.getString("cognome"),rs.getInt("numero_timbri"),rs.getString("data_rilascio")));
				}
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			ColonnaCodiceCarta.setCellValueFactory(new PropertyValueFactory<>("codice"));
			ColonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
			ColonnaCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
			ColonnaNumTimbri.setCellValueFactory(new PropertyValueFactory<>("numero_timbri"));
			ColonnaDataRilascio.setCellValueFactory(new PropertyValueFactory<>("data_rilascio"));
			TabellaFidelity.setItems(oblist);
		  
		   
	   }
}
