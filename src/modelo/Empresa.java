package modelo;

//import java.util.Calendar;

public class Empresa {

	private Long id;
	private String nome;

	public String getNome() {
		return nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}		

}
