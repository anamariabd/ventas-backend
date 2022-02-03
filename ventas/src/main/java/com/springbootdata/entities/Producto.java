package com.springbootdata.entities;

import java.io.Serializable;
import java.util.Set;

import com.springbootdata.entities.FacturaDetalle;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="productos")
public class Producto implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre")
	private String nombre;

	@Column(name="estado")
	private String estado;
	
	@Column(name="valor_unitario")
	private int valorUnitario;
	
	@OneToMany(mappedBy="idProducto") 
	private Set<FacturaDetalle> factura_detalles;
	
	public Producto(){
		super();
	}
	
	public Producto(String nombre, String estado, int valor_unitario) {
		super();
		this.nombre = nombre;
		this.estado = estado;
		this.valorUnitario = valor_unitario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(int valor_unitario) {
		this.valorUnitario = valor_unitario;
	}
	
}
