package com.springbootdata.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.springbootdata.entities.Producto;
import com.springbootdata.entities.Usuario;
import com.springbootdata.services.UsuarioService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api")
public class UsuarioController {

	  @Autowired
	  UsuarioService usuarioService;
	  
	  @PostMapping("/usuario")
	  public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
			try {
				return usuarioService.createUsuario(usuario);
			}catch (Exception e) {
				return null;
			}
	  }
	  
	  @GetMapping("/usuario")
	  public List<Usuario> getAllUsuarios(@RequestParam(required = false) String name) {
		  return usuarioService.getAllUsuarios(name);
	  }
	
	  @GetMapping("/usuario/{user}")
	  public Usuario getUsuarioByUsuario(@PathVariable("user") String usuario) throws Exception {
				  
	     return usuarioService.findByUsuario(usuario);
	  }
	  
	  @PutMapping("/usuario/{id}")
	  public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") long id, @RequestBody Usuario usuario) {
		 try {
			return usuarioService.updateUsuario(id, usuario);    		    
	      } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	      } 
	  }
}
