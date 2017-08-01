package modelo;

public class Funcionario extends Pessoa {

	private int drt;
	private Situacao situacao;

	public Integer getDrt() {
		return drt;
	}
	public void setDrt(int drt) {
		this.drt = drt;
	}
	
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getFoto() {
		//00002 / 0000102
		String nomeFoto = "0000" + this.drt;
		//System.out.println(nomeFoto);

		return nomeFoto.substring(nomeFoto.length() - 5, nomeFoto.length());
	}
}


