package com.springbootdata.entities;

import java.sql.Date;

import com.springbootdata.entities.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FacturaDetalle")
public class FacturaDetalle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idFacturaDetalle;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="factura_id")
	private Factura consecutivo;
	
	@ManyToOne
	@JoinColumn(name="producto_id")
	private Producto idProducto;

	@Column(name="cantidad")
	private int cantidad;
	
	@Column(name="valor_unitario")
	private int valorUnitario;
	
	public FacturaDetalle() {
		super();
	}
	
	public FacturaDetalle(int cantidad, int valor_unitario, Factura IdConsecutivo, Producto IdProducto) {
		super();
		this.cantidad = cantidad;
		this.valorUnitario = valor_unitario;
		this.consecutivo = IdConsecutivo;
		this.idProducto = IdProducto;
	}

	public Producto getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Producto id_producto) {
		this.idProducto = id_producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(int valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Factura getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Factura consecutivo) {
		this.consecutivo = consecutivo;
	}

}
