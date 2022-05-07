package appuntamenti;

public class Appuntamenti {
	
	int codice;
	String descrizione;
	String data;
	String ora_inizio;
	String ora_fine;
	
	public Appuntamenti(String descrizione, String data, String ora_inizio, String ora_fine) {
		super();
		
		this.descrizione = descrizione;
		this.data = data;
		this.ora_inizio = ora_inizio;
		this.ora_fine = ora_fine;
	}
	
	public Appuntamenti() {
		// TODO Auto-generated constructor stub
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getOra_inizio() {
		return ora_inizio;
	}
	public void setOra_inizio(String ora_inizio) {
		this.ora_inizio = ora_inizio;
	}
	public String getOra_fine() {
		return ora_fine;
	}
	public void setOra_fine(String ora_fine) {
		this.ora_fine = ora_fine;
	}
	
	

}
