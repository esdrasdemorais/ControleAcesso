package modelo;

import java.util.Calendar;

public class Pessoa {
	private Long id;
	private String rg;
	private String nome;	
	private Calendar dataNasc;
	private Long cargo;
	private Integer drt;
	private String cpf; 
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getDrt() {
		return drt;
	}

	public void setDrt(Integer drt) {
		this.drt = drt;
	}

	

	public Long getCargo() {
		return cargo;
	}
	
	public void setCargo(Long cargo) {
		this.cargo = cargo;
	}
	
	
	

	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Calendar getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Calendar dataNasc) {
		this.dataNasc = dataNasc;
	}
}
