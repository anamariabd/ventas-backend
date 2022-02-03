package com.springbootdata.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootdata.services.ClienteService;
import com.springbootdata.entities.Cliente;
import com.springbootdata.entities.tipos_identificaciones;
import com.springbootdata.repositories.TipoIdentificacionRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api")
public class ClienteController {
	 private static final Logger log = LoggerFactory.getLogger(ClienteController.class);	
	 
	   @Autowired
		private ClienteService clienteService;
	   
	   @Autowired
	   private TipoIdentificacionRepository IdentRepository;

		  @PostMapping( value = "/clientes")
		  public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
				try {
					return clienteService.createCliente(cliente);
				}catch (Exception e) {
					return null;
				}
		  }
		  
		  @GetMapping("/clientes")
		  public List<Cliente> getAllCliente(@RequestParam(required = false) String Nombre) {
				try {
					return clienteService.getAllCliente(Nombre);
				}catch (Exception e) {
					return null;
				}
		  }
		  
		  @GetMapping("/clientes/tipos")
		  public List<tipos_identificaciones> getTipos(@RequestParam(required = false) String Nombre) {
				try {
					return IdentRepository.findAll();
				}catch (Exception e) {
					return null;
				}
		  }
		  
		  @GetMapping("/clientes/{id}")
		  public Cliente getClienteById(@PathVariable("id") Long id) {
			  
			  log.info( "el id:"+id );
					  
		     return clienteService.getClienteById(id);
		  }
		  
		  @PutMapping("/clientes/{id}")
		  public ResponseEntity<Cliente> updateCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {

			  return clienteService.updateCliente(id, cliente);
		  }

}
