package modelo;

import java.util.Calendar;

public class Situacao extends Pessoa {
	private Long id;
	private Calendar dataSaida;
	private Calendar dataRetorno;
	private Long tipo;
	private String tipoSituacao;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Calendar getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Calendar dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Calendar getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(Calendar dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
	public Long getTipo() {
		return tipo;
	}
	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}
	public String getTipoSituacao() {
		return tipoSituacao;
	}
	public void setTipoSituacao(String tipoSituacao) {
		this.tipoSituacao = tipoSituacao;
	}
}
