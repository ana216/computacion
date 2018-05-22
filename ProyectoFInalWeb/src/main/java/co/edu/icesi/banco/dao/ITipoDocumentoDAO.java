package co.edu.icesi.banco.dao;

import java.util.List;

import co.edu.icesi.banco.modelo.TiposDocumentos;



public interface ITipoDocumentoDAO {
	
	public void save(TiposDocumentos entity);
	public void update(TiposDocumentos entity);
	public void delete(TiposDocumentos entity);
	public TiposDocumentos findById(Long tdocCodigo);
	public List<TiposDocumentos> findAll();

}
