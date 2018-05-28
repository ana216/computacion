package co.edu.icesi.banco.logic;

import java.util.List;

import co.edu.icesi.banco.modelo.Clientes;

public interface IClienteLogic {

	public void save (Clientes entity)throws Exception;
	public void update(Clientes entity)throws Exception;
	public void delete(Clientes entity)throws Exception;
	public Clientes findById(Long cliId)throws Exception;
	public List<Clientes> findAll()throws Exception;
	public List<Clientes> findAllActiveClients()throws Exception;
}
