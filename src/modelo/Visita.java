package modelo;

import java.util.Calendar;
import java.util.Date;

public class Visita {
	
	private Integer id;
	private Visitante visitante;
	private Pessoa pessoa;
	private Funcionario funcionario;
	private Usuario usuario;	
	private Cracha cracha;	
	private Setor setor;
//	private Calendar dataEntrada;
	private Date dataEntrada;
	private Date horaEntrada;
	private Date dataEntradaVisualizacao;	
	private Date dataSaida;
	private Local local;
	private Empresa empresa;
	private Motivo motivo;
		
	public Motivo getMotivo() {
		return motivo;
	}
	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Visitante getVisitante() {
		return visitante;
	}
	public void setVisitante(Visitante visitante) {
		this.visitante = visitante;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Cracha getCracha() {
		return cracha;
	}
	public void setCracha(Cracha cracha) {
		this.cracha = cracha;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public Date setDataEntrada(Date dataEntrada) {
		return this.dataEntrada = dataEntrada;
	}
	public Date getDataEntradaVisualizacao() {
		return dataEntradaVisualizacao;
	}
	public void setDataEntradaVisualizacao(Date dataEntradaVisualizacao) {
		this.dataEntradaVisualizacao = dataEntradaVisualizacao;
	}
	public Date getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
}
