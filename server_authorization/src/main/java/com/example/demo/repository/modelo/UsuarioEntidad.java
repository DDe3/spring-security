package com.example.demo.repository.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class UsuarioEntidad {
	@Id
	@Column(name = "usua_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usario")
	@SequenceGenerator(name = "seq_usario", sequenceName = "seq_usario", allocationSize = 1)
	private Integer id;

	@Column(name = "usua_nombre")
	private String nombre;
	@Column(name = "usua_password")
	private String password;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	

}
