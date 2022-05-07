package fidelitycard;

public class FidelityCard {
  String codice,nome,cognome,data_rilascio;
  int  numero_timbri;
public FidelityCard() {
	super();
	// TODO Auto-generated constructor stub
}
public FidelityCard(String codice, String nome, String cognome,int numero_timbri ,String data_rilascio) {
	super();
	this.codice = codice;
	this.nome = nome;
	this.cognome = cognome;
	this.data_rilascio = data_rilascio;
	this.numero_timbri = numero_timbri;
}
public String getCodice() {
	return codice;
}
public void setCodice(String codice) {
	this.codice = codice;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getCognome() {
	return cognome;
}
public void setCognome(String cognome) {
	this.cognome = cognome;
}
public String getData_rilascio() {
	return data_rilascio;
}
public void setData_rilascio(String data_rilascio) {
	this.data_rilascio = data_rilascio;
}
public int getNumero_timbri() {
	return numero_timbri;
}
public void setNumero_timbri(int numero_timbri) {
	this.numero_timbri = numero_timbri;
}
  
}
