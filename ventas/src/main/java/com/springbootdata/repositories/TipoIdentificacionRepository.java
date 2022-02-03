package com.springbootdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootdata.entities.tipos_identificaciones;

@Repository
public interface TipoIdentificacionRepository extends JpaRepository<tipos_identificaciones, Long>{

	tipos_identificaciones findByAbreviatura(String abreviatura);
}

