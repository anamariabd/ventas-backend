package com.springbootdata.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootdata.controllers.ClienteController;
import com.springbootdata.entities.Producto;
import com.springbootdata.entities.Usuario;
import com.springbootdata.repositories.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	

	 private static final Logger log = LoggerFactory.getLogger(ClienteController.class);	
	  
	  @Autowired
	  private BCryptPasswordEncoder bCryptPasswordEncoder;
 	
	  public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
  
		    try {
		    			    	 
			    String aux = bCryptPasswordEncoder.encode(usuario.getContraseña());
			    
			    usuario.setContraseña(aux);	
			    log.info(aux);
		    	Usuario _usuario = usuarioRepository.save(usuario);
		        return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
		      } catch (Exception e) {
		        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		      }
	  }
	  
	 public List<Usuario> getAllUsuarios(@RequestParam(required = false) String Nombre) {
		  return usuarioRepository.findAll();
		  
	  }
	 
	  public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") long id, @RequestBody Usuario usuario) {
		  
		  Optional <Usuario> user = usuarioRepository.findById(id);
		  
	    if(user.isPresent()) {
	    	
		    String aux = bCryptPasswordEncoder.encode(usuario.getContraseña());
		    
		    usuario.setContraseña(aux);	
		    Usuario _usuario = user.get();
		    _usuario.setPerfil(usuario.getPerfil());
	    	_usuario.setNombre(usuario.getNombre());
	    	_usuario.setApellido(usuario.getApellido());
	    	_usuario.setUsuario(usuario.getUsuario());
	    	
	    	Usuario _user = usuarioRepository.save(_usuario);
	    	
	    	return new ResponseEntity<>(_user, HttpStatus.OK);
	    	
	    }else {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	  }	  
	
	public Usuario findByUsuario(String usuario) throws Exception{
	
		
    	Optional<Usuario> us = usuarioRepository.findByUsuario(usuario);
		if (us.isPresent() ) {
			return us.get();
		}else {
			throw new Exception("usuario no encontrado");
		}
		
	}
}
