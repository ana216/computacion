package co.edu.icesi.banco.logic;

import java.util.List;


import co.edu.icesi.banco.modelo.Retiros;

public interface IRetirosLogic {
	public void save (Retiros entity)throws Exception;
	public void update(Retiros entity)throws Exception;
	public void delete(Retiros entity)throws Exception;
	public Retiros findById(Long retId)throws Exception;
	public List<Retiros> findAll()throws Exception;

}
