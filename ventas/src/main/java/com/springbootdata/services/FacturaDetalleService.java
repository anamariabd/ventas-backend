package com.springbootdata.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootdata.entities.Factura;
import com.springbootdata.entities.FacturaDetalle;
import com.springbootdata.repositories.FacturaDetalleRepository;

@Service
public class FacturaDetalleService {
	
	 @Autowired
	 FacturaDetalleRepository facturaDetalleRepository;

		private static Logger log = LoggerFactory.getLogger(FacturaDetalleService.class);
	 
	  public ResponseEntity<?> createFacturaDetalle(@RequestBody FacturaDetalle facturaDetalle) {	
		  
		  log.info(" y :"+facturaDetalle.getValorUnitario()+"y"+ facturaDetalle.getCantidad());
		  
		  log.info("id_: "+ facturaDetalle.getConsecutivo().getConsecutivo());
		  log.info("id2__:"+ facturaDetalle.getIdProducto().getId());
		    try {
		    	FacturaDetalle _facturaDetalle = facturaDetalleRepository.save(facturaDetalle);

		        return new ResponseEntity<>(_facturaDetalle, HttpStatus.CREATED);
		      } catch (Exception e) {
		        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		      }
	  }
	  
	  public List<FacturaDetalle> getAll(@RequestParam(required = false) String title){ 
		  return facturaDetalleRepository.findAll();
	  }

}
