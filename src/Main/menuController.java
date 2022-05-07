package Main;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import appuntamenti.agendaController;
import appuntamenti.appuntamentiDB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class menuController implements Initializable {

	    private YearMonth meseAnnoCalendario;  
		private ArrayList<Button> giorniCalendario = new ArrayList<>(35);
		public static Calendar c = Calendar.getInstance();
		public static  String giornoAtt;
		public static String meseAtt;
		public static String annoAtt;

		  @FXML
		    private ImageView b_Appuntamenti;
		    @FXML
		    private MenuItem barra_altriDoc;
		    @FXML
		    private ImageView b_insta;
		    @FXML
		    private Button b_magazzino;
		    @FXML
		    private MenuItem barra_fidelity;

		    @FXML
		    private ImageView b_facebook;

		    @FXML
		    private BorderPane pannello;

		    @FXML
		    private AnchorPane sinistra;

		    @FXML
		    private GridPane calendario;

		    @FXML
		    private Text nomeMese;

		    @FXML
		    private MenuItem barra_forn;

		    @FXML
		    private MenuItem chiudi;

		    @FXML
		    private MenuItem barra_prodotti;

		    @FXML
		    private MenuItem barra_pag;

		    @FXML
		    private MenuItem barra_incassi;

		    @FXML
		    private MenuBar barra;

		    @FXML
		    private Button b_anagrafiche;

		    @FXML
		    private AnchorPane centro;

		    @FXML
		    private AnchorPane destra;

		    @FXML
		    private MenuItem barra_Dip;

		    @FXML
		    private BorderPane pannelloCalendario;

		    @FXML
		    private Button b_documenti;

		    @FXML
		    private Menu File;

		    @FXML
		    private MenuItem barra_inventario;

		    
		    
		    //RESTITUISCE IL GIORNO CORRENTE 
            public static LocalDate RestituisciGiornoCorrente() {
            	giornoAtt= String.valueOf(c.get(Calendar.DAY_OF_MONTH));
				meseAtt=String.valueOf(c.get(Calendar.MONTH)+1);
				annoAtt=String.valueOf(c.get(Calendar.YEAR));
	            
				LocalDate l= LocalDate.of(Integer.parseInt(annoAtt),Integer.parseInt(meseAtt), Integer.parseInt(giornoAtt));
            
				
				return l;
            }

            @Override
			public void initialize(URL arg0, ResourceBundle arg1) {
				
				giornoAtt= String.valueOf(c.get(Calendar.DAY_OF_MONTH));
				meseAtt=String.valueOf(c.get(Calendar.MONTH)+1);
				annoAtt=String.valueOf(c.get(Calendar.YEAR));
				meseAnnoCalendario=YearMonth.now();
				
				String g,m;
				//COLTORLLO SE SONO A UNA CIFRA PERCHE' HO BISOGNO DI AGGIUNGERE UNO ZERO
	            if(Integer.parseInt(giornoAtt)<=9)
	            	g="0"+giornoAtt;
	            else
	            	g=giornoAtt;
	            
	            if(Integer.parseInt(meseAtt)<=9)
	            	m="0"+meseAtt;
	            else
	            	m=meseAtt;
	            ////
				appuntamentiDB.Elimina(g+"/"+m+"/"+annoAtt);
				
				 //INSERISCO IL PANNELLO IN OGNI SINGOLA CELLA DELLA GRIGLIA
				for (int i =1 ; i <6  ;i++) {
		            for (int j = 0; j < 7; j++) {
		                Button ap = new Button();
		                ap.setPrefSize(30,20);
		                giorniCalendario.add(ap);
		                calendario.add(ap,j,i);
		               
		            }
		        }
				
				popolaCalendario(meseAnnoCalendario);
				
				
				Tooltip tana=new Tooltip("Visuallizza menu Anagrafiche");
				Tooltip tdoc=new Tooltip("Visualizza menu Documenti");
				Tooltip tmag=new Tooltip("Visualizza menu Magazzino");
				Tooltip tface=new Tooltip("Apri pagina Facebook");
				Tooltip tinsta=new Tooltip("Apri pagina Instagram ");
				Tooltip tcal=new Tooltip("Visualizza appuntamenti giornalieri");
				Tooltip tapp=new Tooltip("Visualizza e aggiungi appuntamento");
				
				b_anagrafiche.setTooltip(tana);
				b_magazzino.setTooltip(tmag);
				b_documenti.setTooltip(tdoc);
				Tooltip.install(b_Appuntamenti, tapp);
				Tooltip.install(b_facebook, tface);
				Tooltip.install(b_insta, tinsta);
				Tooltip.install(calendario, tcal);
				
				
				chiudi.setOnAction(new EventHandler<ActionEvent>() {
					
					

					@Override
					public void handle(ActionEvent arg0) {
						
						Main.primaryStage.close();
						
					}
				});
				
				
				//IMPOSTO L'AZIONE ALLE VARIE BARRE PRESENTI IN HOME
				barra_prodotti.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {
						cambiaFx("/prodotti/GraficaProdotti.fxml");				
					}
				});
				 		
				barra_altriDoc.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						cambiaFx("/altriDocumenti/graficaAltriDocumenti.fxml");
						
					}
				});

				barra_Dip.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					cambiaFx("/dipendenti/GraficaDipendenti.fxml");
					
				}
			});
			
				barra_fidelity.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					cambiaFx("/fidelitycard/graficaFidelityCard.fxml");
					
				}
			});
			
				barra_incassi.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					cambiaFx("/incassi/GraficaIncassi.fxml");
					
				}
			});
			
				barra_inventario.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					cambiaFx("/prodotti/graficaInventario.fxml");
					
				}
			});

				barra_pag.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					cambiaFx("/pagamenti/graficaPagamenti.fxml");
					
				}
			});
			
				barra_forn.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					cambiaFx("/fornitori/graficaFornitori.fxml");
					
				}
			});
			//
			//IMPOSTO I BOTTONI DELLE FINESTRE 	
			chiudi.setAccelerator(new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN));
			barra_prodotti.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN));
			barra_altriDoc.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
			barra_Dip.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
			barra_fidelity.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));
			barra_incassi.setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN));
			barra_inventario.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));
			barra_pag.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN));
			barra_forn.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
			}

    //SE PREMO SUL BOTTONE MAGAZZINO
	@FXML
    void magazzino(MouseEvent event)  {
		 cambiaFx("menuMagazzino.fxml");
    }
    //SE PREMO SUL BOTTONE DOCUMENTI
    @FXML
    void documenti(MouseEvent event)  {
         cambiaFx("menuDocumenti.fxml");
    }
   //SE PREMO SUL BOTTONE MAGAZZINO
    @FXML
     public  void  anagrafiche(MouseEvent event) throws IOException {
    	cambiaFx("MenuAnagrafiche.fxml");	
    }
    //SE PREMO SUL BOTTONE FACEBOOK
    @FXML
    void facebook(MouseEvent e) {
       Desktop desktop = Desktop.getDesktop();
    	 try {
    	     URI uri = new URI("https://www.facebook.com/novanta6/");
    	     desktop.browse(uri);
    	 } catch (IOException ex) {
    	    StampaErrore("");
    	 } catch (URISyntaxException ex) {
    	    ex.printStackTrace();
         }  
    }
    //SE PREMO SUL BOTTONE INSTA
    @FXML
    void instagram(MouseEvent event) {
    	Desktop desktop = Desktop.getDesktop();
   	 try {
   	     URI uri = new URI("https://www.instagram.com/novanta6official/?hl=it");
   	     desktop.browse(uri);
   	 } catch (IOException ex) {
   	    ex.printStackTrace();
   	 } catch (URISyntaxException ex) {
   	    ex.printStackTrace();
        }
   	  
   }
    
    //SE PREMO SULLA FRECCIA < VA AL MESE PRECEDENTE
    @FXML
    void mesePrecedente(MouseEvent event) {
    	meseAnnoCalendario = meseAnnoCalendario.minusMonths(1);
        popolaCalendario(meseAnnoCalendario);    
    }
    //SE PREMO SULLA FRECCIA > VA AL MESE PRECEDENTE
    @FXML
    void meseSuccessivo(MouseEvent event) {
    	meseAnnoCalendario = meseAnnoCalendario.plusMonths(1);
        popolaCalendario(meseAnnoCalendario);
      

    }
    
    
    private void popolaCalendario(YearMonth meseAnnoCalendario) {
    	
		nomeMese.setText(meseAnnoCalendario.getMonth().getDisplayName(TextStyle.FULL, Locale.ITALIAN) + " " + String.valueOf(meseAnnoCalendario.getYear()));
		LocalDate dataCalendario = LocalDate.of(meseAnnoCalendario.getYear(), meseAnnoCalendario.getMonthValue(), 1);
		
	        while (!dataCalendario.getDayOfWeek().toString().equals("SUNDAY") ) {  
	            dataCalendario = dataCalendario.minusDays(1);
	        }
	     
	     
	    	  for(int  i=0; i<giorniCalendario.size(); i++) {
	    		Button ap=giorniCalendario.get(i);
	    	  
	    	    String giorno, mese;
	            String  txt = String.valueOf(dataCalendario.getDayOfMonth());
	            String  txt1 = String.valueOf(dataCalendario.getMonthValue());
	            String anno= String.valueOf(dataCalendario.getYear());
	            
	            
	            ap.setText(txt);  //INSERISCI IL GIORNO NEL CALENDARIO
	 
	            //MODIFICO IL COLORE E LA DIMENSIONE DEL TESTO ALL'INTERNO DEL BOTTONE
	            ap.setFont(Font.font("System",10));  
	            ap.setStyle("-fx-background-color: #99e699;");
	            
	            
	            
	            //COLTORLLO SE SONO A UNA CIFRA PERCHE' HO BISOGNO DI AGGIUNGERE UNO ZERO
	            if(Integer.parseInt(txt)<=9)
	            	giorno="0"+txt;
	            else
	            	giorno=txt;
	            
	            if(Integer.parseInt(txt1)<=9)
	            	mese="0"+txt1;
	            else
	            	mese=txt1;
	            ////
	            
	            
	            //COLORO IL GIORNO CORRENTE 
	            if(txt.equals(giornoAtt) && txt1.equals(meseAtt) && anno.equals(annoAtt)) {
		         	
		         	ap.setStyle ("-fx-background-color: #ff0000;");
		         }
	            
	            LocalDate l= LocalDate.of(Integer.parseInt(anno),Integer.parseInt(mese), Integer.parseInt(giorno));	//SERVIRA' PER SETTARE IL TEXTFIELD DI APPUNTAMENTI QUANDO PREMO SUL CALENDARIO            
	            ap.setOnAction(new EventHandler<ActionEvent>() {
					
				   //AZIONE DEL CALENDARIO
	            	@Override
					public void handle(ActionEvent arg0) {
						
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/appuntamenti/agenda.fxml"));
						Parent parent = null;
						
						try {
							parent = fxmlLoader.load();
						} catch (IOException e) {
							e.printStackTrace();
						}
						 agendaController dialogController = fxmlLoader.<agendaController>getController();
						 try {
							 dialogController.data.setValue(l);
							dialogController.VisualizzaPerData(giorno+"/"+mese+"/"+anno);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						 
						 creaFinestraAppuntamenti(parent);
					
						
					}
				});
	            
	            dataCalendario = dataCalendario.plusDays(1);
	        }
     		
	}

	//SE PREMO SUL BOTTONE APPUNTAMENTI
	@FXML
    void visualizzaAppunt(MouseEvent event) throws IOException {
		
		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/appuntamenti/agenda.fxml"));
		 Parent parent = fxmlLoader.load();
		 creaFinestraAppuntamenti(parent);

    }

	public void creaFinestraAppuntamenti(Parent p) {
		 Scene scene = new Scene(p, 700, 400);
		 Stage stage = new Stage();
		 stage.initModality(Modality.APPLICATION_MODAL);
		 stage.setTitle("Appuntamenti");
		 stage.setScene(scene);
		 stage.showAndWait();
	
	}


    //VISUALIZZA SUL PANNELLO CENTRALE LA GRAFICA PASSATA
	public void cambiaFx(String s)  {
		Parent root = null;
		try {
			root = (Parent)FXMLLoader.load(getClass().getResource(s));
		} catch (IOException e) {
			StampaErrore("errore caricamento file: FXML");

		} 
         pannello.setCenter(root);
         AnchorPane.setBottomAnchor(root, 0.0);
         AnchorPane.setTopAnchor(root, 0.0);
         AnchorPane.setRightAnchor(root, 0.0);
         AnchorPane.setLeftAnchor(root, 0.0);
	}
	
     //STAMPA ERRORE
	   public static void StampaErrore(String Err) {
		   Alert alert = new Alert(AlertType.ERROR);
		   alert.setTitle("Error Dialog");
		   alert.setContentText(Err);
		   alert.showAndWait();
	     }
	
    
}

