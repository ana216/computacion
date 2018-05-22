package co.edu.icesi.banco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.banco.modelo.Cuentas;
import co.edu.icesi.banco.modelo.TiposDocumentos;


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
		String jpql="Select cli from Cuentas cli";
		return entitymanager.createQuery(jpql).getResultList();
	}

}
