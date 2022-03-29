package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/obtenerToken")
public class AuthorizationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping
	public String returnToken(@RequestBody Usuario usuario) {
		authenticate(usuario.getNombre(), usuario.getPassword());
		return crearToken(usuario.getNombre());
	}
	
	@PostMapping
	public String insertarUsuario(@RequestBody Usuario usuario) {
		usuario.setPassword(encriptarPassword(usuario.getPassword()));
		return crearToken(usuario.getNombre());
	}
	
	private String encriptarPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}
	
	public String crearToken(String nombre) {
		String token = Jwts.builder().setIssuedAt(new Date()).setIssuer("http://localhost:8081")
				.setSubject(nombre)
				.setExpiration(new Date(System.currentTimeMillis()+100000L))
				.signWith(SignatureAlgorithm.HS512, "55as4daw4de8faefaw8d4asd4asdeadsasfaee11")
				.compact();
		//response.setHeader("Authorization", "Bearer "+token);
		return "Bearer "+token;
	}
	
	private void authenticate(String usuario, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario, password));
	}

}
