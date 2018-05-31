package co.edu.icesi.banco.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	@Override
	public List<Consignaciones> findConsignacionesPorClienteYFecha(Long cliId, Date fechaIni, Date fechaFIn) {
		String jpql="Select con from Consignaciones con "
				+ "where con.cuentas.clientes.cliId = ?1 "
				+ "AND con.conFecha BETWEEN ?2 AND ?3";
		Query query =entitymanager.createQuery(jpql);
		query.setParameter(1,cliId);
		query.setParameter(2,fechaIni);
		query.setParameter(3,fechaFIn);
		return query.getResultList();
	
	
	}
	
	


}
