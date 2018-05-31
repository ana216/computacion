package co.edu.icesi.banco.logic;

import java.util.List;

import co.edu.icesi.banco.modelo.Cuentas;

public interface ICuentasLogic {
	
	public void save (Cuentas entity)throws Exception;
	public void update(Cuentas entity)throws Exception;
	public void delete(Cuentas entity)throws Exception;
	public Cuentas findById(String cueNum)throws Exception;
	public List<Cuentas> findAll()throws Exception;
	public List<Cuentas> findAllActiveCuentas() throws Exception;
	public List<Cuentas> findAllActiveCuentasPorCliente(long cli) throws Exception;

}
