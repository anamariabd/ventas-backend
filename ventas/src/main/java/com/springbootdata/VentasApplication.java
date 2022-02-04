package com.springbootdata;


import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springbootdata.repositories.TipoIdentificacionRepository;
import com.springbootdata.entities.tipos_identificaciones;
import com.springbootdata.repositories.PerfilRepository;
import com.springbootdata.entities.Perfil;

@SpringBootApplication
public class VentasApplication {

	@Autowired 
	private TipoIdentificacionRepository tiposIdRepository;
	
	@Autowired 
	private PerfilRepository perfilRepository;	
	
	final static Logger log = LoggerFactory.getLogger(VentasApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(VentasApplication.class, args);
		
	}
	
	@PostConstruct
	public void addTipoIdentificaciones() {
		tipos_identificaciones T_Id = tiposIdRepository.findByAbreviatura("C.C.");
		
		if(T_Id == null) {
			log.info("Creando tipos");
			tiposIdRepository.save(new tipos_identificaciones("C.C.", "cedula de ciudadania"));
			tiposIdRepository.save(new tipos_identificaciones("T.I.", "Tarjeta de identidad"));
			tiposIdRepository.save(new tipos_identificaciones("NIT.", "Numero de identificacion tributaria"));
		}else {
			log.info("Ya existen");
		}		
	}
	
	@PostConstruct
	public void addPerfiles() {
		Perfil perfil = perfilRepository.findByNombre("Cajero");
		
		if(perfil == null) {
			log.info("Creando perfiles");
			perfilRepository.save(new Perfil("Cajero"));
			perfilRepository.save(new Perfil("Administrador"));
		}else {
			log.info("Ya existen");
		}		
	}
}
