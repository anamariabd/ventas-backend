package com.springbootdata.services;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootdata.entities.Factura;
import com.springbootdata.entities.Producto;
import com.springbootdata.entities.Cliente;
import com.springbootdata.repositories.FacturaRepository;

@Service
public class FacturaService {
	
    	@Autowired
		private FacturaRepository facturasRepository;

		  public ResponseEntity<?> createFactura(@RequestBody Factura factura) {		
			
			  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   
		      java.sql.Date fecha = java.sql.Date.valueOf(simpleDateFormat.format(new Date()));
		      
			  factura.setFecha(fecha);
			  
			    try {
			    	Factura _factura = facturasRepository.save(factura);
			        return new ResponseEntity<>(_factura, HttpStatus.CREATED);
			      } catch (Exception e) {
			        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			      }
		  }
		  
		
		  public List<Factura> getAll(@RequestParam(required = false) String title) {
			  return facturasRepository.findAll();
		  }
		  

		  public Factura getFacturaById(@PathVariable("id") long id) {
			  
			  Optional <Factura> fact = facturasRepository.findById(id);
			  
			  if(!fact.isPresent()) {
				  return null; 
			  }
					  
		     return fact.get();
		  } 
		  
		  public List<Factura> getFacturByCliente(@PathVariable("cliente") Long cliente){
			  return facturasRepository.findByCliente(cliente);
		  }
		  
}
