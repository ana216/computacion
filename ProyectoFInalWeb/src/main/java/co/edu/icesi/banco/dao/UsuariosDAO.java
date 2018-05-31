package co.edu.icesi.banco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.banco.modelo.TiposDocumentos;
import co.edu.icesi.banco.modelo.Usuarios;


@Repository
@Scope("singleton")
public class UsuariosDAO implements IUsuariosDAO{
	
	@PersistenceContext
	private EntityManager entitymanager;

	@Override
	public void save(Usuarios entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Usuarios entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Usuarios entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuarios findById(Long usuCedula) {
		return entitymanager.find(Usuarios.class, usuCedula);
	}

	@Override
	public List<Usuarios> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
