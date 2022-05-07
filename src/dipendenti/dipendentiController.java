package dipendenti;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class dipendentiController implements Initializable {


	    @FXML
	    private TableColumn<Dipendente,Float> ColonnaStipendio;
         @FXML
	    private TableColumn<Dipendente,String> ColonnaDataAssunzione;
	    @FXML
	    private TableColumn<Dipendente,String> ColonnaStato;
	    @FXML
	    private TableColumn<Dipendente,String> ColonnaCognome;
	    @FXML
	    private TableView<Dipendente> TabellaDipendenti;
	    @FXML
	    private TableColumn<Dipendente,String> ColonnaCodicefiscale;
	    @FXML
	    private TableColumn<Dipendente,String> ColonnaSesso;
	    @FXML
	    private TableColumn<Dipendente,String> ColonnaMansione;
	    @FXML
	    private AnchorPane pannelloDipendenti;
	    @FXML
	    private TableColumn<Dipendente,String> ColonnaNome;
	    @FXML
	    private TableColumn<Dipendente,String> ColonnaDatadinascita;
         ObservableList<Dipendente> oblist =FXCollections.observableArrayList();
    

	@Override
	public void initialize(URL url, ResourceBundle arg1) {
		  Aggiorna("select * from Dipendenti ");
	}
	
   public void Aggiorna(String s) {
	   oblist.clear();
		 
		  Connection conn=null;
			 try {
				conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
				ResultSet rs=conn.createStatement().executeQuery(s);
				while(rs.next()) {
					 oblist.add(new Dipendente(rs.getString("codiceFiscale"),rs.getString("nome"),rs.getString("cognome"),rs.getString("datadiNascita"),rs.getString("dataAssunzione"),rs.getString("sesso"),rs.getString("mansione"),rs.getString("stato"),rs.getFloat("stipendio")));
				}
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		ColonnaCodicefiscale.setCellValueFactory(new PropertyValueFactory<>("codiceFiscale"));
		ColonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		ColonnaCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
		ColonnaDatadinascita.setCellValueFactory(new PropertyValueFactory<>("datadiNascita"));
		ColonnaDataAssunzione.setCellValueFactory(new PropertyValueFactory<>("dataAssunzione"));
		ColonnaSesso.setCellValueFactory(new PropertyValueFactory<>("sesso"));
		ColonnaMansione.setCellValueFactory(new PropertyValueFactory<>("mansione"));
		ColonnaStato.setCellValueFactory(new PropertyValueFactory<>("stato"));
		ColonnaStipendio.setCellValueFactory(new PropertyValueFactory<>("stipendio"));
	   TabellaDipendenti.setItems(oblist);
	  
	   
   }

   @FXML
   void AggiungiDipendente(ActionEvent event) throws IOException {
	  
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GraficaAggiungi.fxml"));
    	Parent root = (Parent) loader.load();
        pannelloDipendenti.getChildren().setAll(root);
        AggiungiController controller = loader.getController();
    	controller.setGraficaController(this);
		  Aggiorna("select * from Dipendenti ");

	}

   
   @FXML
   void CercaDipendente(ActionEvent event) throws IOException {
	   FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaCerca.fxml"));
       Parent root = (Parent) loader.load();
       pannelloDipendenti.getChildren().setAll(root);
       cercaDipendenteController controller = loader.getController();
   	   controller.setGraficaController(this);
		  Aggiorna("select * from Dipendenti ");

   }
 
   @FXML
   void Aggiorna_dipendente(ActionEvent event) throws IOException {
	   FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaAggiorna.fxml"));
       Parent root = (Parent) loader.load();
       pannelloDipendenti.getChildren().setAll(root);
       aggiornaDipendenteController controller = loader.getController();
   	   controller.setGraficaController(this);
	   
   	   
   	   //INSERISCE IL CODICE FISCALE CLICCATO SULLA TABELLA NEL TEXTFIEL CODF
   	   TabellaDipendenti.setOnMouseClicked(new EventHandler<MouseEvent>() {
  	    @Override
  	    public void handle(MouseEvent event) {
  	    	
  	    	try {
  	    	    String a = TabellaDipendenti.getSelectionModel().getSelectedItems().get(0).CodiceFiscale;
  	    	     controller.codiceFiscale.setText(a); 
  	    	} catch (NullPointerException e) {
  	    	     controller.codiceFiscale.setText(""); 
  	    	}
  	    }
  	});

   }
   @FXML
   void svuotaDipendenti(ActionEvent event) {
	   Alert alert =new Alert(AlertType.CONFIRMATION);
       alert.setTitle("Confirmation Dialog");
       alert.setHeaderText(null);
       alert.setContentText(" Sei sicuro di voler svuotare Dipendenti ?");
    	Optional <ButtonType> action =alert.showAndWait();
        if(action.get()==ButtonType.OK) {
        	dipendentiDB.Svuota();
            Aggiorna("select * from Dipendenti");
        }
   }

   

}