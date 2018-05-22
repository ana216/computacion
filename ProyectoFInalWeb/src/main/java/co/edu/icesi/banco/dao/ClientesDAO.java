package co.edu.icesi.banco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.banco.modelo.Clientes;


@Repository
@Scope("singleton")
public class ClientesDAO implements IClientesDAO {
	
	@PersistenceContext
	private EntityManager entitymanager;

	
	
	@Override
	public void save(Clientes entity) {
		entitymanager.persist(entity);
		
	}

	@Override
	public void update(Clientes entity) {
		// TODO Auto-generated method stub
		entitymanager.merge(entity);
		
	}

	@Override
	public void delete(Clientes entity) {
		entitymanager.remove(entity);
		
	}

	@Override
	public Clientes findById(Long cliId) {
		// TODO Auto-generated method stub
		return entitymanager.find(Clientes.class, cliId);
	}

	@Override
	public List<Clientes> findAll() {
		String jpql="Select cli from Clientes cli";
		return entitymanager.createQuery(jpql).getResultList();
	}

}
