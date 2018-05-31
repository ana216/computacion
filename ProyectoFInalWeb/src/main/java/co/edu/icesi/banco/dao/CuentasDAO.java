package co.edu.icesi.banco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.banco.modelo.Cuentas;


@Repository
@Scope("singleton")
public class CuentasDAO implements ICuentasDAO{

	@PersistenceContext
	private EntityManager entitymanager;
	
	@Override
	public void save(Cuentas entity) {
		entitymanager.persist(entity);
		
	}

	@Override
	public void update(Cuentas entity) {
		entitymanager.merge(entity);
		
	}

	@Override
	public void delete(Cuentas entity) {
		entitymanager.remove(entity);
		
	}

	@Override
	public Cuentas findById(String numCuenta) {
		return entitymanager.find(Cuentas.class, numCuenta);
	}

	@Override
	public List<Cuentas> findAll() {
		String jpql="Select cuen from Cuentas cuen";
		return entitymanager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Cuentas> findAllActiveCuentasDeUnCliente(Long cliId) {
		String jpql="Select cuen from Cuentas cuen"
				+ "where cuen.cliId = ?1"
				+ "AND cuen.cue_activa";
		Query query =entitymanager.createQuery(jpql);
		query.setParameter(1,cliId);
		return query.getResultList();
	}

}
