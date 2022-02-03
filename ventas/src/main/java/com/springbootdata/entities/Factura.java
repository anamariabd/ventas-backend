package com.springbootdata.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="facturas")
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="consecutivo")
	private int consecutivo;
	
	@Column(name="fecha")
	private Date fecha;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="consecutivo", fetch = FetchType.LAZY ) 
	private Set<FacturaDetalle> factura_detalles;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;

	public Factura() {
		super();
	}
	
	public Factura(Cliente cliente, Date fecha) {
		super();
		this.cliente = cliente;
		this.fecha = fecha;
	}

	public int getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(int consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
