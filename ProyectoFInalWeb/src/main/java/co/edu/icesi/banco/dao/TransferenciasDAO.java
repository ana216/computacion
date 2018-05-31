package co.edu.icesi.banco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


import co.edu.icesi.banco.modelo.Transferencias;

@Repository
@Scope("singleton")
public class TransferenciasDAO implements ITransferenciasDAO {
	
	@PersistenceContext
	private EntityManager entitymanager;
	
	@Override
	public void save(Transferencias entity) {

		entitymanager.persist(entity);
	}

	@Override
	public void update(Transferencias entity) {
		entitymanager.merge(entity);
		
	}

	@Override
	public void delete(Transferencias entity) {
		entitymanager.remove(entity);
		
	}

	@Override
	public Transferencias findById(Long transId) {
		return entitymanager.find(Transferencias.class, transId);
	}

	@Override
	public List<Transferencias> findAll() {
		String jpql="Select tran from Transferencias tran";
		return entitymanager.createQuery(jpql).getResultList();
	}

}
