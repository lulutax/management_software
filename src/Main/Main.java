package Main;

import altriDocumenti.altriDocumentiDB;
import appuntamenti.appuntamentiDB;
import dipendenti.dipendentiDB;
import fidelitycard.FidelityCardDB;
import fornitori.FornitoriDB;
import incassi.incassiDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pagamenti.PagamentiDB;
import pizzeriaDB.PizzeriaDB;
import prodotti.inventarioDB;
import prodotti.prodottiDB;

public class Main extends Application {
	
	
 public static Stage primaryStage;
	

	@Override
	public void start(Stage primary)  throws Exception  {
		primaryStage=primary;
		
		try {
			
		
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("menu.fxml"));
			Scene menuScene = new Scene(root,1200,700);
			// primaryStage.setFullScreen(true);
			primaryStage.setTitle("Novanta6 ");
			primaryStage.setScene(menuScene);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		    
	}
	
	public static void main(String[] args) {
	
		//CREO IL DB
        PizzeriaDB Novanta6 = new PizzeriaDB();
		Novanta6.createNewDatabase();
		
		
		//CREO LE VARIE TABELLE
		prodottiDB prod=new prodottiDB();
		prod.CreaProdottiDB();
		
		inventarioDB inv;
		inv = new inventarioDB();
		
		inv.CreaInventario();
		
		dipendentiDB dip=new dipendentiDB();
		dip.CreaDipendentiDB();
		
		incassiDB inc=new incassiDB();
		inc.CreaIncassiDB();
		
		altriDocumentiDB doc=new altriDocumentiDB();
		doc.CreaAltriDocumenti();
		
		appuntamentiDB app=new appuntamentiDB();
		app.CreaAppuntamentiDB();
		
		
		FidelityCardDB fidelity=new FidelityCardDB();
		fidelity.CreaFidelityCard();
		
        FornitoriDB forn= new FornitoriDB();
        forn.CreaFornitoriDB();

        PagamentiDB pag= new PagamentiDB();
        pag.CreaPagamentiDB();


		   		launch(args);
	  
	}
}
