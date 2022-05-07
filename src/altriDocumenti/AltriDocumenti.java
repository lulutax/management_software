package altriDocumenti;

import javafx.scene.control.Button;

public class AltriDocumenti {
	
	
	
	String nome;
	String file;
	Button button;
	public AltriDocumenti(String nomeFile, String file, Button b) {
		super();
		this.nome = nomeFile;
		this.file = file;
		this.button = b;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Button getButton() {
		return button;
	}
	public void setButton(Button button) {
		this.button = button;
	}
	
	
	

}
