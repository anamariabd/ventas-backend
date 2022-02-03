package com.springbootdata.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootdata.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByUsuario(String usuario);

}
