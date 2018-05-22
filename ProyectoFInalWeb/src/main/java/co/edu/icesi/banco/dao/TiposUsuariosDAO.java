package co.edu.icesi.banco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.banco.modelo.TiposUsuarios;


@Repository
@Scope("singleton")
public class TiposUsuariosDAO implements ITiposUsuariosDAO {

	@PersistenceContext
	private EntityManager entitymanager;
	
	@Override
	public void save(TiposUsuarios entity) {
		entitymanager.persist(entity);
		
	}

	@Override
	public void update(TiposUsuarios entity) {
		entitymanager.merge(entity);
		
	}

	@Override
	public void delete(TiposUsuarios entity) {
		entitymanager.remove(entity);
		
	}

	@Override
	public TiposUsuarios findById(Long tusuCodigo) {

		return entitymanager.find(TiposUsuarios.class,tusuCodigo);
	}

	@Override
	public List<TiposUsuarios> findAll() {
		String jpql="Select cli from TiposUsuarios cli";
		return entitymanager.createQuery(jpql).getResultList();
	}

}
