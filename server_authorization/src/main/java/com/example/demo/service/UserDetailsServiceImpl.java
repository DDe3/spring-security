package com.example.demo.service;

import java.util.Arrays;
import static java.util.Collections.emptyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IUsuarioRepository;
import com.example.demo.repository.modelo.UsuarioEntidad;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioEntidad ue = usuarioRepository.getUser(username);
		return new User(ue.getNombre(), ue.getPassword(), emptyList());
	}
	
	

}
