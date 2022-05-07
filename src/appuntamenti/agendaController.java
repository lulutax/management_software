package appuntamenti;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import Main.funzioni;
import Main.menuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


	public class agendaController implements Initializable {
		
		
	    @FXML
	    private TableColumn<Appuntamenti, String> colonnaInizio;
	    @FXML
	    private TextField descrizione;
	    @FXML
	    private ChoiceBox<String> ora_inizio;
        @FXML
	    private ChoiceBox<String> ora_fine;
	    @FXML
	    public DatePicker data;
	    @FXML
	    private TableColumn<Appuntamenti, String> colonnaDesc;
	    @FXML
	    private TableColumn<Appuntamenti, String> colonnaData;
	    @FXML
	    private TableColumn<Appuntamenti, String> colonnaFine;
	    @FXML
	    private ChoiceBox<String> min_fine;
	    @FXML
	    private TableView<Appuntamenti> tabella;
	    @FXML
	    private ChoiceBox<String> min_inizio;
	    ObservableList<Appuntamenti> oblist =FXCollections.observableArrayList();
	    ObservableList<String> listOre=FXCollections.observableArrayList();
	    ObservableList<String> listMin=FXCollections.observableArrayList();
		  
		
		String ora_in,ora_fi;
	    
		
		//AGGIUNGI APPUNTAMENTO
	    @FXML
	    void Aggiungi(MouseEvent event) {
	    	
	    	LocalDate dataCorrente=menuController.RestituisciGiornoCorrente();
	    	LocalDate dataDaAggiungere=data.getValue();
	    	
			if( funzioni.verificaSeVuota(descrizione.getText())==false && data.getValue()!=null) {
				
				
			   if( dataCorrente.getYear() > dataDaAggiungere.getYear()) {
		    		funzioni.StampaErrore("Non puoi aggiungere appuntamenti in questa data");

			   }
			   else if(dataCorrente.getYear() == dataDaAggiungere.getYear() && dataCorrente.getMonthValue() > dataDaAggiungere.getMonthValue()) {
		    		funzioni.StampaErrore("Non puoi aggiungere appuntamenti in questa data");
			   }
			   
			   else if(dataCorrente.getYear() == dataDaAggiungere.getYear() && dataCorrente.getMonthValue() == dataDaAggiungere.getMonthValue() && dataCorrente.getDayOfMonth() > dataDaAggiungere.getDayOfMonth() ) {
               
				   funzioni.StampaErrore("Non puoi aggiungere appuntamenti in questa data");
                }
	    		else {
	    			
	    			if(ora_inizio.getValue()==null)
	    				ora_inizio.setValue("00");
	    			if(min_inizio.getValue()==null)
	    				min_inizio.setValue("00");
	    			if(ora_fine.getValue()==null)
	    				ora_fine.setValue("00");
	    			if(min_fine.getValue()==null)
	    				min_fine.setValue("00");
	    			
	    			ora_in=ora_inizio.getValue()+" : "+min_inizio.getValue();
	    			ora_fi=ora_fine.getValue()+" : "+min_fine.getValue();
	    			
	    	        appuntamentiDB.AggiungiAppuntamento(descrizione.getText(), data.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), ora_in, ora_fi);
	    	        Aggiorna();
	    	        }
	    		}
		 }
	  

	    
	    private void Aggiorna() {
			oblist.clear();
			   Connection conn=null;
				 try {
					conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
					ResultSet rs=conn.createStatement().executeQuery("select * from Appuntamenti ");
					while(rs.next()) {
						oblist.add(new Appuntamenti( rs.getString("descrizione"), rs.getString("data"), rs.getString("ora_inizio"), rs.getString("ora_fine")));
					}
				} catch (SQLException e) {
					funzioni.StampaErrore("Operazione non eseguita!");
				}
				colonnaData.setCellValueFactory(new PropertyValueFactory<>("data"));
				colonnaInizio.setCellValueFactory(new PropertyValueFactory<>("ora_inizio"));
				colonnaFine.setCellValueFactory(new PropertyValueFactory<>("ora_fine"));
				colonnaDesc.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
			   tabella.setItems(oblist);
			   
			
		}
	

		@Override
		public void initialize(URL location, ResourceBundle resources) {
         
			
			   for(int i=0; i<=59; i++) {
				 if(i<=9) {
			      listOre.add("0"+String.valueOf(i));
				  listMin.add("0"+String.valueOf(i));
				 }
				 else if(i<=23) {
					 listOre.add(String.valueOf(i)); 
					 listMin.add(String.valueOf(i)); }
				 else
					 listMin.add(String.valueOf(i)); 
				 
			   
			ora_inizio.setItems(listOre);
			ora_fine.setItems(listOre);
			min_inizio.setItems(listMin);
			min_fine.setItems(listMin);
		    }
			
			   Aggiorna();
		}




//CLICCANDO SUL CALENDARIO UNA DATA SPECIFICA NELLA TABELLA SI VISUALIZZERANNO
//TUTTI GLI APPUNTAMENTI DI QUELLA DATA
		public void VisualizzaPerData(String temp) throws SQLException {
		   oblist.clear();
			Connection conn = DriverManager.getConnection("jdbc:sqlite:novanta6.db");
			ResultSet rs = conn.createStatement().executeQuery("select * from Appuntamenti where data='"+temp+"'");
			while(rs.next()) {
				oblist.add(new Appuntamenti( rs.getString("descrizione"), rs.getString("data"), rs.getString("ora_inizio"), rs.getString("ora_fine")));
			}

		   colonnaData.setCellValueFactory(new PropertyValueFactory<>("data"));
		   colonnaInizio.setCellValueFactory(new PropertyValueFactory<>("ora_inizio"));
		   colonnaFine.setCellValueFactory(new PropertyValueFactory<>("ora_fine"));
		   colonnaDesc.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
    	   tabella.setItems(oblist);
			
		}


}
