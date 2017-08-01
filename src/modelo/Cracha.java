package modelo;

import java.sql.Date;

public class Cracha {

	private Long id;
	private Portaria portaria;
	private Date horaEntrada;
	private Date horaSaida;
	private Long numeroCracha;
	private Long tipoCracha;	
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Portaria getPortaria() {
		return portaria;
	}
	public void setPortaria(Portaria portaria) {
		this.portaria = portaria;
	}
	public Date getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public Date getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}
	public Long getNumeroCracha() {
		return numeroCracha;
	}
	public void setNumeroCracha(Long numeroCracha) {
		this.numeroCracha = numeroCracha;
	}
	public Long getTipoCracha() {
		return tipoCracha;
	}
	public void setTipoCracha(Long tipoCracha) {
		this.tipoCracha = tipoCracha;
	}		
		
}
