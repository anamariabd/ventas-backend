package com.springbootdata.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootdata.controllers.ClienteController;
import com.springbootdata.entities.Cliente;
import com.springbootdata.repositories.ClienteRepository;

@Service
public class ClienteService {

	 private static final Logger log = LoggerFactory.getLogger(ClienteController.class);	
	   @Autowired
		private ClienteRepository clientesRepository;
		
			  public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
				 
				  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   
			      java.sql.Date fechaRegistro = java.sql.Date.valueOf(simpleDateFormat.format(new Date()));
			      
			      cliente.setFechaRegistro(fechaRegistro);
			      log.info( cliente.getT_identificacion().getAbreviatura() );
	      
				    try {
				    	 Cliente _cliente = clientesRepository.save(cliente);
				    	 
				        return new ResponseEntity<>(_cliente, HttpStatus.CREATED);
				      } catch (Exception e) {
				        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				      }
			  }
			  

			  public List<Cliente> getAllCliente(@RequestParam(required = false) String Nombre) {
				  return clientesRepository.findAll();
			  }
			  
	
			  public Cliente getClienteById(@PathVariable("id") Long id) {
				  
				  Optional <Cliente> cli = clientesRepository.findById(id);
				  
				  if(!cli.isPresent()) {
					  return null; 
				  }
						  
			     return cli.get();
			  }
			  
			  public ResponseEntity<Cliente> updateCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
				  
				  Optional <Cliente> cli = clientesRepository.findById(id);
				  
			    if(cli.isPresent()) {
			    	
			    	Cliente _cliente = cli.get();
			    	_cliente.setIdentificacion(cliente.getIdentificacion());
			    	_cliente.setRazonSocial(cliente.getRazonSocial());
			    	
			    	Cliente _cli = clientesRepository.save(_cliente);
			    	
			    	return new ResponseEntity<>(_cli, HttpStatus.OK);
			    	
			    }else {
			    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			    }
			    
			  }	   
	   
}
