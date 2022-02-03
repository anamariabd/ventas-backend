package com.springbootdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootdata.entities.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{

	Perfil findByNombre(String nombre);
}
