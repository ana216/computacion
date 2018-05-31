package co.edu.icesi.banco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.banco.modelo.Retiros;


@Repository
@Scope("singleton")
public class RetirosDAO implements IRetirosDAO {
	@PersistenceContext
	private EntityManager entitymanager;
	
	@Override
	public void save(Retiros entity) {

		entitymanager.persist(entity);
	}

	@Override
	public void update(Retiros entity) {
		entitymanager.merge(entity);
		
	}

	@Override
	public void delete(Retiros entity) {
		entitymanager.remove(entity);
		
	}

	@Override
	public Retiros findById(Long retId) {
		return entitymanager.find(Retiros.class, retId);
	}

	@Override
	public List<Retiros> findAll() {
		String jpql="Select ret from Retiros ret";
		return entitymanager.createQuery(jpql).getResultList();
	}

}
