package co.edu.icesi.banco.dao;

import java.util.List;


import co.edu.icesi.banco.modelo.TiposUsuarios;

public interface ITiposUsuariosDAO {
	
	public void save(TiposUsuarios entity);
	public void update(TiposUsuarios entity);
	public void delete(TiposUsuarios entity);
	public TiposUsuarios findById(Long tusuCodigo);
	public List<TiposUsuarios> findAll();

}
