package co.edu.icesi.banco.dao;

import java.util.List;

import co.edu.icesi.banco.modelo.Clientes;

public interface IClientesDAO {
	
	public void save(Clientes entity);
	public void update(Clientes entity);
	public void delete(Clientes entity);
	public Clientes findById(Long cliId);
	public List<Clientes> findAll();

}
