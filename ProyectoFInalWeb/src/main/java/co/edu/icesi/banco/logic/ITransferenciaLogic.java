package co.edu.icesi.banco.logic;

import java.util.List;


import co.edu.icesi.banco.modelo.Transferencias;

public interface ITransferenciaLogic {
	
	public void save (Transferencias entity)throws Exception;
	public void update(Transferencias entity)throws Exception;
	public void delete(Transferencias entity)throws Exception;
	public Transferencias findById(Long tranId)throws Exception;
	public List<Transferencias> findAll()throws Exception;

}
