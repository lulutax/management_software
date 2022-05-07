package fornitori;

public class Fornitori {
  String p_iva,nome,descrizione,mail,telefono,indirizzo;

public Fornitori(String p_iva, String nome, String descrizione, String telefono, String mail, String indirizzo) {
	super();
	this.p_iva = p_iva;
	this.nome = nome;
	this.descrizione = descrizione;
	this.telefono = telefono;
	this.mail = mail;
	this.indirizzo = indirizzo;
}

public String getP_iva() {
	return p_iva;
}

public void setP_iva(String p_iva) {
	this.p_iva = p_iva;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getDescrizione() {
	return descrizione;
}

public void setDescrizione(String descrizione) {
	this.descrizione = descrizione;
}

public String getMail() {
	return mail;
}

public void setMail(String mail) {
	this.mail = mail;
}

public String getTelefono() {
	return telefono;
}

public void setTelefono(String telefono) {
	this.telefono = telefono;
}

public String getIndirizzo() {
	return indirizzo;
}

public void setIndirizzo(String indirizzo) {
	this.indirizzo = indirizzo;
}



}
