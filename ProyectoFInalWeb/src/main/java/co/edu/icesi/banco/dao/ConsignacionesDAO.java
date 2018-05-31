package co.edu.icesi.banco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.banco.modelo.Clientes;
import co.edu.icesi.banco.modelo.Consignaciones;


@Repository
@Scope("singleton")
public class ConsignacionesDAO implements IConsignacionesDAO {

	@PersistenceContext
	private EntityManager entitymanager;
	
	@Override
	public void save(Consignaciones entity) {

		entitymanager.persist(entity);
	}

	@Override
	public void update(Consignaciones entity) {
		entitymanager.merge(entity);
		
	}

	@Override
	public void delete(Consignaciones entity) {
		entitymanager.remove(entity);
		
	}

	@Override
	public Consignaciones findById(Long conId) {
		return entitymanager.find(Consignaciones.class, conId);
	}

	@Override
	public List<Consignaciones> findAll() {
		String jpql="Select con from Consignaciones con";
		return entitymanager.createQuery(jpql).getResultList();
	}
	


}
