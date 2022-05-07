package dipendenti;


public class Dipendente {
     
	String CodiceFiscale,Nome,Cognome,Sesso,Mansione,Stato;
	String DatadiNascita,DataAssunzione;
	Float Stipendio;
	
	public Dipendente(String codiceFiscale, String nome, String cognome,String datadiNascita, String dataAssunzione, String sesso, String mansione, String stato,
			 Float stipendio) {
		super();
		CodiceFiscale = codiceFiscale;
		Nome = nome;
		Cognome = cognome;
		Sesso = sesso;
		Mansione = mansione;
		Stato = stato;
		DatadiNascita = datadiNascita;
		DataAssunzione = dataAssunzione;
		Stipendio = stipendio;
	}
	public String getCodiceFiscale() {
		return CodiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		CodiceFiscale = codiceFiscale;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getCognome() {
		return Cognome;
	}
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	public String getSesso() {
		return Sesso;
	}
	public void setSesso(String sesso) {
		Sesso = sesso;
	}
	public String getMansione() {
		return Mansione;
	}
	public void setMansione(String mansione) {
		Mansione = mansione;
	}
	public String getStato() {
		return Stato;
	}
	public void setStato(String stato) {
		Stato = stato;
	}
	public String getDatadiNascita() {
		return DatadiNascita;
	}
	public void setDatadiNascita(String datadiNascita) {
		DatadiNascita = datadiNascita;
	}
	public String getDataAssunzione() {
		return DataAssunzione;
	}
	public void setDataAssunzione(String dataAssunzione) {
		DataAssunzione = dataAssunzione;
	}
	public Float getStipendio() {
		return Stipendio;
	}
	public void setStipendio(Float stipendio) {
		Stipendio = stipendio;
	}
	
	
}