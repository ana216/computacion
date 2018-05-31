package co.edu.icesi.banco.dao;

import java.util.List;


import co.edu.icesi.banco.modelo.Transferencias;

public interface ITransferenciasDAO {
	
	public void save(Transferencias entity);
	public void update(Transferencias entity);
	public void delete(Transferencias entity);
	public Transferencias findById(Long transId);
	public List<Transferencias> findAll();

}
