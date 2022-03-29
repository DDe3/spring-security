package com.example.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.UsuarioEntidad;
import com.example.demo.security.Usuario;

@Repository
@Transactional
public class UsuarioRepositoryImpl implements IUsuarioRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public UsuarioEntidad getUser(String nombre) {
		TypedQuery<UsuarioEntidad> mq = em.createQuery("SELECT u FROM UsuarioEntidad u WHERE u.nombre=:nombre", UsuarioEntidad.class);
		mq.setParameter("nombre", nombre);
		return mq.getSingleResult();
	}
	
	public void guardarUsuario(Usuario usuario) {
		em.persist(usuario);
	}
	

}
