package modelo;

import java.util.Calendar;

public class Detalhe extends Pessoa {
	private Long id;
	private String funcao;
	private Calendar dataAdmissao;
	private Calendar dataDemissao;
	private String departamento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public Calendar getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Calendar dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public Calendar getDataDemissao() {
		return dataDemissao;
	}
	public void setDataDemissao(Calendar dataDemissao) {
		this.dataDemissao = dataDemissao;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	
}
