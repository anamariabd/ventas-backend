package com.springbootdata.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootdata.entities.Producto;
import com.springbootdata.repositories.ProductoRepository;

@Service
public class ProductoService {

	 @Autowired
	 ProductoRepository productoRepository;
	 
	  public ResponseEntity<?> createProducto(@RequestBody Producto producto) {		
					  
		    try {
		    	Producto _producto = productoRepository.save(producto);
		        return new ResponseEntity<>(_producto, HttpStatus.CREATED);
		      } catch (Exception e) {
		        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		      }
	  }
	  
	  public List<Producto> getAllProducts(@RequestParam(required = false) String Nombre) {
		  return productoRepository.findAll();
	  }
	  
	  public Producto getProductoById(@PathVariable("id") long id) {
		  
		  Optional <Producto> product = productoRepository.findById(id);
		  
		  if(!product.isPresent()) {
			  return null; 
		  }
				  
	     return product.get();
	  }
	  
	  public ResponseEntity<Producto> updateProducto(@PathVariable("id") long id, @RequestBody Producto producto) {
		  
		  Optional <Producto> prod = productoRepository.findById(id);
		  
	    if(prod.isPresent()) {
	    	
	    	Producto _producto = prod.get();
	    	_producto.setNombre(producto.getNombre());
	    	_producto.setEstado(producto.getEstado());
	    	_producto.setValorUnitario(producto.getValorUnitario());
	    	
	    	Producto _prod = productoRepository.save(_producto);
	    	
	    	return new ResponseEntity<>(_prod, HttpStatus.OK);
	    	
	    }else {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	  }	  
	  
	  public ResponseEntity<HttpStatus> deleteProducto(@PathVariable("id") long id) {
			try {
				productoRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }
	  
	  
}
