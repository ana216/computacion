package co.edu.icesi.banco.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.banco.modelo.TiposDocumentos;

@Repository
@Scope("singleton")
public class TipoDocumentoDAO implements ITipoDocumentoDAO{
	
	@PersistenceContext
	private EntityManager entitymanager;

	@Override
	public void save(TiposDocumentos entity) {
		entitymanager.persist(entity);
		
	}

	@Override
	public void update(TiposDocumentos entity) {
		entitymanager.merge(entity);
		
	}

	@Override
	public void delete(TiposDocumentos entity) {
		entitymanager.remove(entity);
		
	}

	@Override
	public TiposDocumentos findById(Long tdocCodigo) {
		return entitymanager.find(TiposDocumentos.class, tdocCodigo);
	}

	@Override
	public List<TiposDocumentos> findAll() {
		String jpql="Select cli from TiposDocumentos cli";
		return entitymanager.createQuery(jpql).getResultList();
	}

}
