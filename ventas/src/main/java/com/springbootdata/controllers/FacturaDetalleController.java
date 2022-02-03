package com.springbootdata.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootdata.entities.FacturaDetalle;
import com.springbootdata.services.FacturaDetalleService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api")
public class FacturaDetalleController {

    @Autowired
	  private FacturaDetalleService facturaDetalleService;
    
	  @PostMapping("/detalles")
	  public ResponseEntity<?> createFactura(@RequestBody FacturaDetalle factura) {
		return facturaDetalleService.createFacturaDetalle(factura); 
	  }
	  
	  @GetMapping("/detalles")
	  public List<FacturaDetalle> getAllFacturas(@RequestParam(required = false) String name) {
		  return facturaDetalleService.getAll(name);
	  }
}
