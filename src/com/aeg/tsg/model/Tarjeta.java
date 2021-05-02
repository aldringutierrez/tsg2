package com.aeg.tsg.model;

import java.util.Date;

public class Tarjeta {
	protected Long idTarjeta;
	protected Long idCliente;
	protected Long numero;    
	protected Date fecVence; 
	protected String banco;     
	protected String predeterminada;     
	
	public Long getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(Long idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getFecVence() {
		return fecVence;
	}

	public void setFecVence(Date fecVence) {
		this.fecVence = fecVence;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getPredeterminada() {
		return predeterminada;
	}

	public void setPredeterminada(String predeterminada) {
		this.predeterminada = predeterminada;
	}

	
}
