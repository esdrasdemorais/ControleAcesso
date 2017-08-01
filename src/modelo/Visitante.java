package modelo;

//import java.util.Calendar;

public class Visitante extends Pessoa {
	
	private Long telefone;
	private Empresa empresa;
	private pessoaFoto pessoafoto;
	private Situacao situacao;
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}		
	 
	public pessoaFoto getPessoafoto() {
		return pessoafoto;
	}

	public void setPessoafoto(pessoaFoto pessoafoto) {
		this.pessoafoto = pessoafoto;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
}
