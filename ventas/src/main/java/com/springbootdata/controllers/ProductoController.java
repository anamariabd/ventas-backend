package com.springbootdata.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springbootdata.entities.Producto;
import com.springbootdata.services.ProductoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api")
public class ProductoController {
	
   @Autowired
	private ProductoService productoService;

	  @PostMapping("/productos")
	  public ResponseEntity<?> createProducto(@RequestBody Producto productos) {
		     try {
		        return productoService.createProducto(productos);
		      } catch (Exception e) {
		        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		      } 
	  }
	  
	  @GetMapping("/productos")
	  public List<Producto> getAllProductos(@RequestParam(required = false) String name) {
		  return productoService.getAllProducts(name);
	  }
	  
	  @GetMapping("/productos/{id}")
	  public Producto getProductoById(@PathVariable("id") long id) {
		return productoService.getProductoById(id);
	  }

	  @PutMapping("/productos/{id}")
	  public ResponseEntity<Producto> updateProducto(@PathVariable("id") long id, @RequestBody Producto producto) {
		 try {
			return productoService.updateProducto(id, producto);    		    
	      } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	      } 
	  }
	  
	  @DeleteMapping("/productos/{id}")
	  public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
			try {
				productoService.deleteProducto(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }

}
