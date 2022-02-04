package com.springbootdata.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootdata.security.services.UserDetailsImpl;
import com.springbootdata.security.jwt.JwtUtils;
import com.springbootdata.services.UsuarioService;

import payload.JwtResponse;
import payload.LoginRequest;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api/auth")
public class IndexController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioService userServices;
	
	//@Autowired
	//private PerfilService perfil;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		String rol = roles.get(0);

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(),userDetails.getUsername(),rol));
	}

}
