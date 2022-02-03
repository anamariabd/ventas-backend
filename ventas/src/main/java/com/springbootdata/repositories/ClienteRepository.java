package com.springbootdata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootdata.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	//List<CLiente> findClientByTipoIdent_ident();
	Cliente findClientByIdentificacion(String indentificacion);
}
