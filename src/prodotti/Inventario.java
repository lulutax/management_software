package prodotti;

public class Inventario {
	String nome,  id_prodotto;
	int qta_utilizzata;
	
	public Inventario(String id_prodotto, String nome, int qta_utilizzata) {
		super();
		this.id_prodotto = id_prodotto;
		this.nome = nome;
		this.qta_utilizzata = qta_utilizzata;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getId_prodotto() {
		return id_prodotto;
	}

	public void setId_prodotto(String id_prodotto) {
		this.id_prodotto = id_prodotto;
	}

	public int getQta_utilizzata() {
		return qta_utilizzata;
	}
	public void setQta_utilizzata(int qta_utilizzata) {
		this.qta_utilizzata = qta_utilizzata;
	}
	
	
}
