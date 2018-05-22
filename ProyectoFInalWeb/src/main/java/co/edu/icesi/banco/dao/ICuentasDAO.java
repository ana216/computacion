package co.edu.icesi.banco.dao;

import java.util.List;

import co.edu.icesi.banco.modelo.Cuentas;

public interface ICuentasDAO {
	
	public void save(Cuentas entity);
	public void update(Cuentas entity);
	public void delete(Cuentas entity);
	public Cuentas findById(String numCuenta);
	public List<Cuentas> findAll();


}
