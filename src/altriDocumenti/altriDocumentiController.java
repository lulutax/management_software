package altriDocumenti;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class altriDocumentiController implements Initializable {

    @FXML
    private TableColumn<AltriDocumenti, String> ColonnaFile;
    @FXML
    private TableColumn<AltriDocumenti, String> ColonnaNomefile;
    @FXML
    private TableColumn<AltriDocumenti, Button> ColonnaImg;
    @FXML
    private AnchorPane pannelloDocumenti;
    @FXML
    private TableView<AltriDocumenti> TabellaDocumenti;   
    ObservableList<AltriDocumenti> oblist =FXCollections.observableArrayList();
    ArrayList<Button> b = new ArrayList<>();
  
    
    
    //AZIONE APRI DOCUMENTI
    private void handleButtonAction(ActionEvent e) throws IOException  {
    	for(int i=0;i<b.size();i++) {
    		if(e.getSource()==b.get(i)) {
    			String f=restituisciFile(i);
 			    File file = new File( f );

 		        //Open PDF file
 		         Desktop desktop = Desktop.getDesktop();
 	            if(file.exists()) {
 	            	desktop.open(file);
 	            	}
 		     }
    		}
    	}


    @FXML
    void AggiungiDocumento(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaAggiungiDocumento.fxml"));
    	Parent root = (Parent) loader.load();
        pannelloDocumenti.getChildren().setAll(root);
        aggiungiController controller = loader.getController();
    	controller.setGraficaController(this);
        Aggiorna("select * from AltriDocumenti");

    }

    @FXML
    void cercaDocumento(ActionEvent event) throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaCerca.fxml"));
    	Parent root = (Parent) loader.load();
        pannelloDocumenti.getChildren().setAll(root);
        cercaController controller = loader.getController();
    	controller.setGraficaController(this);
        Aggiorna("select * from AltriDocumenti");

    }

    @FXML
    void CancellaDocumento(ActionEvent event) throws IOException  {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("graficaCancella.fxml"));
    	Parent root = (Parent) loader.load();
        pannelloDocumenti.getChildren().setAll(root);
        eliminaController controller = loader.getController();
    	controller.setGraficaController(this);
    	
        TabellaDocumenti.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent event) {
	    	try {
	    	    String a = TabellaDocumenti.getSelectionModel().getSelectedItems().get(0).nome;
	    	     controller.nomeFile.setText(a); 
	    	} catch (NullPointerException e) {
	    	     controller.nomeFile.setText(""); 
	    	}
	    	}
	    });
        }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
       Aggiorna("select * from AltriDocumenti");
	}
	
    public void Aggiorna(String q) {
    	//CREO I BOTTONI CHE SERVIRANNO PER APRIRE I FILE
    	for(int j=0;j<=restituisciNumeroFile(); j++) {
			 
		    b.add(new Button("open"));
			b.get(j).setOnAction(event -> {
				try {
					handleButtonAction(event);
				} catch (IOException e) {
					funzioni.StampaErrore("Operazione non eseguita!");
					}
			});
			}
    	
    	int i=0;//SERVE PER INDICIZZARE I BOTTONI
    	oblist.clear();
    	Connection conn=null;
    	try {
    		conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
			ResultSet rs=conn.createStatement().executeQuery(q);
			while(rs.next()) {
     			oblist.add(new AltriDocumenti(rs.getString("nome"),rs.getString("file").toString(), b.get(i) ));
				i++;
				}
				conn.close();
				} catch (SQLException e) {
					funzioni.StampaErrore("Operazione non eseguita!");
					}
    	ColonnaNomefile.setCellValueFactory(new PropertyValueFactory<>("nome"));
        ColonnaFile.setCellValueFactory(new PropertyValueFactory<>("file"));
	    ColonnaImg.setCellValueFactory(new PropertyValueFactory<>("button"));
	    TabellaDocumenti.setItems(oblist);
	    }
    
    //RESTITUISCE LA STRINGA CHE CONTIENE IL PATH DEL FILE NELLA POSIZIONE DEL BOTTONE IN CUI HO PREMUTO
	public String restituisciFile(int i) {
		   
		int cont=0;	
		String f=" ";
	    Connection conn=null;
			 try {
				 conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
				 ResultSet rs=conn.createStatement().executeQuery("select * from AltriDocumenti");
			     while(rs.next()) {
			    	 if(cont==i)
			    		 f= rs.getString("file");
                    cont++;
                    }
				conn.close();
			} catch (SQLException e) {
				funzioni.StampaErrore("Operazione non eseguita!");
			}
		return f; 
	   }
	
	//RESTITUISCE IL NUMERO DEI FILE 
	public int restituisciNumeroFile() {
		int cont=0;	
		   Connection conn=null;
			 try {
				conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
				ResultSet rs=conn.createStatement().executeQuery("select * from AltriDocumenti");
				while(rs.next()) {
                    cont++;
				}
				conn.close();
			} catch (SQLException e) {
				funzioni.StampaErrore("Operazione non eseguita!");
			}
		return cont-1; 
	}
	
	
}
