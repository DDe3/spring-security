package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		Usuario user;
		try {
			user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
			return this.authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getNombre(), user.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		String token = Jwts.builder().setIssuedAt(new Date()).setIssuer("http://localhost:8081")
				.setSubject(user.getUsername())
				.setExpiration(new Date(System.currentTimeMillis()+100000L))
				.signWith(SignatureAlgorithm.HS512, "55as4daw4de8faefaw8d4asd4asdeadsasfaee11")
				.compact();
		response.setHeader("Authorization", "Bearer "+token);
	}
	
	

}
