package pagamenti;

public class Pagamenti {
 String data,descrizione,tipo;
 Float importo;
public Pagamenti(String data, String descrizione, Float importo, String tipo) {
	super();
	this.data = data;
	this.descrizione = descrizione;
	this.importo = importo;
	this.tipo = tipo;
}
public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}
public String getDescrizione() {
	return descrizione;
}
public void setDescrizione(String descrizione) {
	this.descrizione = descrizione;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}
public Float getImporto() {
	return importo;
}
public void setImporto(Float importo) {
	this.importo = importo;
}
 
 
}
