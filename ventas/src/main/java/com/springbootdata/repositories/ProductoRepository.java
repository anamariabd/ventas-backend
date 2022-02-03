package com.springbootdata.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootdata.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	

}
