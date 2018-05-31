package co.edu.icesi.banco.dao;

import java.util.List;


import co.edu.icesi.banco.modelo.Retiros;

public interface IRetirosDAO {
	public void save(Retiros entity);
	public void update(Retiros entity);
	public void delete(Retiros entity);
	public Retiros findById(Long retId);
	public List<Retiros> findAll();

}
