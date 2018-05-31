package co.edu.icesi.banco.dao;

import java.util.Date;
import java.util.List;

import co.edu.icesi.banco.modelo.Consignaciones;

public interface IConsignacionesDAO {
	
	public void save(Consignaciones entity);
	public void update(Consignaciones entity);
	public void delete(Consignaciones entity);
	public Consignaciones findById(Long conId);
	public List<Consignaciones> findAll();
	public List<Consignaciones> findConsignacionesPorClienteYFecha(Long cliId, Date fechaIni, Date fechaFIn);

}
