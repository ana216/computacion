package co.edu.icesi.banco.dao;

import java.util.List;

import co.edu.icesi.banco.modelo.Clientes;
import co.edu.icesi.banco.modelo.Consignaciones;

public interface IConsignacionesDAO {
	
	public void save(Consignaciones entity);
	public void update(Consignaciones entity);
	public void delete(Consignaciones entity);
	public Consignaciones findById(Long conId);
	public List<Consignaciones> findAll();

}
