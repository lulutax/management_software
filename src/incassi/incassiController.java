package incassi;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Main.funzioni;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class incassiController implements Initializable{

    @FXML
    private TableColumn<Incasso, String> ColonnaData;

   
    @FXML
    private TableColumn<Incasso, Float> ColonnaIncasso;


    @FXML
    private AnchorPane pannelloIncassi;

    @FXML
    private TableView<Incasso> tabellaIncassi;

    
    ObservableList<Incasso> oblist =FXCollections.observableArrayList();


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Aggiorna("select * from Incassi");
	}
    
    @FXML
    void haipremutoAggiungi(ActionEvent event) throws IOException {

    	FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaAggiungiIncasso.fxml"));
    	Parent root = (Parent) loader.load();
        pannelloIncassi.getChildren().setAll(root);
        aggiungiIncassoController controller = loader.getController();
    	controller.setGraficaController(this);

    }


    @FXML
    void EliminaIncasso(ActionEvent event) throws IOException {

    	FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaElimina.fxml"));
    	Parent root = (Parent) loader.load();
        pannelloIncassi.getChildren().setAll(root);
        eliminaController controller = loader.getController();
    	controller.setGraficaController(this);
    	
    
    }
    
    
    
	   public void Aggiorna(String q) {
		   
		   oblist.clear();
		 	   Connection conn=null;
		 		 try {
		 			conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
		 			ResultSet rs=conn.createStatement().executeQuery(q);
		 			while(rs.next()) {
		 		
		 				oblist.add(new Incasso(rs.getString("data"),rs.getFloat("incasso")));
		 			}
		 		} catch (SQLException e) {
		 			funzioni.StampaErrore("Connessione DB mancante!");
		 		}
		 		 
		 		ColonnaData.setCellValueFactory(new PropertyValueFactory<>("data"));
		 		ColonnaIncasso.setCellValueFactory(new PropertyValueFactory<>("incasso"));
		 		tabellaIncassi.setItems(oblist);
		 	  
		 	   
		    }
	   
	   

	   @FXML
	    void haiPremutoGrafico(ActionEvent event) throws IOException {
		   FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("chart.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Grafico incassi");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	 
	         chartController controller = loader.getController();
	         controller.initialize();
	         List <Incasso> inc = new ArrayList<Incasso>();
	         Connection conn=null;
	 		 try {
	 			conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
	 			ResultSet rs=conn.createStatement().executeQuery("select * from Incassi");
	 			while(rs.next()) {
	 		
	 				inc.add(new Incasso(rs.getString("data"),rs.getFloat("incasso")));
	 			}
	 		} catch (SQLException e) {
	 			funzioni.StampaErrore("Operazione non eseguita!");
	 		}
	         controller.setIncassi(inc);	       
	        dialogStage.show();
	    }
    
}

