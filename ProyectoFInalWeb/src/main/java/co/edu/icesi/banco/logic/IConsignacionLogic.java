package co.edu.icesi.banco.logic;

import java.util.List;


import co.edu.icesi.banco.modelo.Consignaciones;

public interface IConsignacionLogic {
	public void save (Consignaciones entity)throws Exception;
	public void update(Consignaciones entity)throws Exception;
	public void delete(Consignaciones entity)throws Exception;
	public Consignaciones findById(Long cliId)throws Exception;
	public List<Consignaciones> findAll()throws Exception;
}
