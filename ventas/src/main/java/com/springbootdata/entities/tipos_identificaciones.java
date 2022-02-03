package com.springbootdata.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tipos_identificaciones")
public class tipos_identificaciones {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tipo_identificacion")
	private int tipo_identificacion;

	@Column(name="abreviatura")
	private String abreviatura;
	
	@Column(name="descripcion")	
	private String descripcion;
	
	@OneToMany(mappedBy="t_identificacion")
	private Set<Cliente> clientes;

	public tipos_identificaciones() {
		super();
	}

	public tipos_identificaciones(String abreviatura, String descripcion) {
		super();
		this.abreviatura = abreviatura;
		this.descripcion = descripcion;
	}

	public int getTipo_identificacion() {
		return tipo_identificacion;
	}

	public void setTipo_identificacion(int tipo_identificacion) {
		this.tipo_identificacion = tipo_identificacion;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
	
}
