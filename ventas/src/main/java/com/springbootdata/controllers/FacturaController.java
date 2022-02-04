package com.springbootdata.controllers;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootdata.entities.Factura;
import com.springbootdata.services.FacturaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api")
public class FacturaController {

	
	      @Autowired
		  private FacturaService facturaService;
	
		  @PostMapping( value = "/facturas")
		  public ResponseEntity<?> createFactura(@RequestBody Factura factura) {
			return facturaService.createFactura(factura); 
		  }
	  
		  @GetMapping("/facturas")
		  public List<Factura> getAllFacturas(@RequestParam(required = false) String title) {
			  return facturaService.getAll(title);
		  }
		  
		  @GetMapping("/facturas/cliente/{id}")
		  public ResponseEntity<List<Factura>> getFacturaById(@PathVariable("id") Long id) {
			  
			  List<Factura> fact = facturaService.getFacturByCliente(id);
			  
		     return ResponseEntity.ok(fact);
		  }		  
		  /*
		  
		  @GetMapping("/facturas/{id}")
		  public Facturas getFacturaById(@PathVariable("id") long id) {
			  
			  Optional <Facturas> fact = FacturasRepository.findById(id);
			  
			  if(!tuto.isPresent()) {
				  return null; 
			  }
					  
		     return fact.get();
		  } */

}
