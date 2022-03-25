package com.example.demo.repository;

import com.example.demo.repository.modelo.UsuarioEntidad;

public interface IUsuarioRepository {
	
	UsuarioEntidad getUser(String nombre);

}
