package com.springbootdata.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cliente;

	@Column(name="identificacion")
	private String identificacion;
	
	@Column(name="razonSocial")
	private String razonSocial;
	
	@Column(name="fechaRegistro")
	private Date fechaRegistro;
	
	@OneToMany(mappedBy="cliente")
	private Set<Factura> facturas;	
	
	@ManyToOne
	@JoinColumn(name="t_identificacion")
	private tipos_identificaciones t_identificacion;
	
	public Cliente() {
		super();
	}
	
	public Cliente( tipos_identificaciones tIdentificacion , String identificacion, String razon_social, Date fecha ) {
		super();
		this.t_identificacion = tIdentificacion;
		this.identificacion = identificacion;
		this.razonSocial = razon_social;
		this.fechaRegistro = fecha;
	}

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razon_social) {
		this.razonSocial = razon_social;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date razonSocial) {
		this.fechaRegistro = razonSocial;
	}

	
	public tipos_identificaciones getT_identificacion() {
		return t_identificacion;
	}

}
