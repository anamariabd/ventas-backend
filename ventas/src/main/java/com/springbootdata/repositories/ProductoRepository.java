package com.springbootdata.repositories;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springbootdata.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	@Query(value="SELECT \r\n"
			+ "	(SELECT vt.nombre\r\n"
			+ "		FROM (SELECT \r\n"
			+ "	p.nombre,\r\n"
			+ "	sum((fd.valor_unitario)) AS valor,\r\n"
			+ "	fd.cantidad AS cantidad,\r\n"
			+ "	f.fecha AS fecha\r\n"
			+ "	FROM productos AS p \r\n"
			+ "	INNER JOIN factura_detalle AS fd ON p.id = fd.producto_id \r\n"
			+ "	INNER JOIN facturas AS f ON fd.factura_id = f.consecutivo\r\n"
			+ "	GROUP BY p.id, fecha ORDER BY fecha ASC) vt\r\n"
			+ "		WHERE vt.cantidad = MAX(DISTINCT v.cantidad) \r\n"
			+ "	) AS nombre,\r\n"
			+ "	MAX(DISTINCT v.cantidad) AS valormayor,\r\n"
			+ "	v.valor,\r\n"
			+ "	v.fecha\r\n"
			+ "	FROM (SELECT \r\n"
			+ "	p.nombre,\r\n"
			+ "	sum((fd.valor_unitario)) AS valor,\r\n"
			+ "	fd.cantidad AS cantidad,\r\n"
			+ "	f.fecha AS fecha\r\n"
			+ "	FROM productos AS p \r\n"
			+ "	INNER JOIN factura_detalle AS fd ON p.id = fd.producto_id \r\n"
			+ "	INNER JOIN facturas AS f ON fd.factura_id = f.consecutivo\r\n"
			+ "	GROUP BY p.id, fecha ORDER BY fecha ASC) AS v\r\n"
			+ "	GROUP BY v.fecha",nativeQuery = true)
	List<?> productosMasVendidos();
	

	
	String jpql = "SELECT"
			+ "	p.nombre,"
			+ "	sum(fd.valorUnitario) AS valor,"
			+ "	fd.cantidad AS cantidad,"
			+ "	f.fecha AS fecha"
			+ "	FROM Producto AS p"
			+ "	INNER JOIN FacturaDetalle AS fd ON p.id = fd.idProducto"
			+ "	INNER JOIN Factura AS f ON fd.factura_id = f.consecutivo"
	        + "	GROUP BY p.id, fecha ORDER BY fecha ASC";
	
	String consulta = "SELECT"
			+ "	(SELECT vt.nombre"
			+ "	FROM" + jpql + " AS nombre,"
			+ "	MAX(DISTINCT v.cantidad) AS valormayor,"
			+ "	v.valor,"
			+ "	v.fecha"
			+ "	FROM (SELECT"
			+ "	p.nombre,"
			+ "	sum(fd.valorUnitario) AS valor,"
			+ "	fd.cantidad AS cantidad,"
			+ "	f.fecha AS fecha"
			+ "	FROM Producto AS p"
			+ "	INNER JOIN " +jpql+ "AS fd ON p.id = fd.idProducto"
			+ "	INNER JOIN Factura AS f ON fd.factura_id = f.consecutivo"
			+ "	GROUP BY p.id, fecha ORDER BY fecha ASC) AS v"
			+ "	GROUP BY v.fecha";		
	/*
	
	@Query(value = "SELECT"
			+ "	p.nombre,"
			+ "	sum(fd.valorUnitario) AS valor,"
			+ "	fd.cantidad AS cantidad,"
			+ "	f.fecha AS fecha"
			+ "	FROM Producto AS p"
			+ "	INNER JOIN FacturaDetalle AS fd ON p.id = fd.idProducto"
			+ "	INNER JOIN Factura AS f ON fd.consecutivo = f.consecutivo"
	        + "	GROUP BY p.id, fecha ORDER BY fecha ASC", nativeQuery = true)
	List<?> productosMasVendidos(); */
	
  /*  @Query("select mv.nombre from (select p.nombre, "
    		+ "sum(fd.valorUnitario) as valor,"
    		+ "f.fecha as fecha "
    		+ "from Producto p "
    		+ "inner join FacturaDetalle as fd on p.id = fd.idProducto "
    		+ "inner join Factura as f on fd.consecutivo = f.consecutivo "
    		+ "group by p.id, fecha order by fecha asc) mv")	
	Set<?> MasVendidosPorMes(@Param("masvendido") Set<?> masVendido); */

	
	//List<?> MasVendidosPorMes(Set<?> masVendido);
}
