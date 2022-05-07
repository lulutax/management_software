package prodotti;


public class Prodotti  {

    public String id;
	int quantita_utilizzata;
	int quantita_totale;
	String nome,marca,datascadenza;
	Float prezzo;
	
	
	public Prodotti() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Prodotti(String string,String nome, String marca, Float prezzo, int quantita_ut, int quantita_tot, String datascadenza	) {
		super();
		this.id = string;
		this.quantita_utilizzata = quantita_ut;
		this.quantita_totale = quantita_tot;
		this.nome = nome;
		this.marca = marca;
		this.datascadenza = datascadenza;
		this.prezzo = prezzo;
	}
	
	

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getDatascadenza() {
		return datascadenza;
	}
	public void setDatascadenza(String datascadenza) {
		this.datascadenza = datascadenza;
	}
	public Float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}
	public int getQuantita_utilizzata() {
		return quantita_utilizzata;
	}
	public void setQuantita_utilizzata(int quantita_utilizzata) {
		this.quantita_utilizzata = quantita_utilizzata;
	}
	public int getQuantita_totale() {
		return quantita_totale;
	}
	public void setQuantita_totale(int quantita_totale) {
		this.quantita_totale = quantita_totale;
	}

	
}