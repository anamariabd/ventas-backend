package com.springbootdata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springbootdata.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	//List<CLiente> findClientByTipoIdent_ident();
	Cliente findClientByIdentificacion(String indentificacion);
	
	@Query(value="SELECT cli.razon_social,\r\n"
			+ "count(cli.factura_id) AS cantidad,\r\n"
			+ "cli.fecha,\r\n"
			+ "(cli.valor_unitario*cantidad) AS total\r\n"
			+ "FROM \r\n"
			+ "(SELECT * FROM clientes AS c\r\n"
			+ "INNER JOIN facturas AS f ON f.cliente_id = c.cliente\r\n"
			+ "INNER JOIN factura_detalle AS fd ON fd.factura_id = f.cliente_id) AS cli\r\n"
			+ "GROUP BY YEAR(cli.fecha)",nativeQuery = true)
	List<?> CompraClientesPorAño();
	
}
