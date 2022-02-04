package com.springbootdata.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.springbootdata.entities.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

	@Query("select f from Factura f where f.cliente.cliente = :objId")
	List<Factura> findByCliente(@Param("objId") Long cliente);
	
}
