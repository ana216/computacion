package co.edu.icesi.banco.logic;

import java.util.List;


import co.edu.icesi.banco.modelo.TiposDocumentos;

public interface ITiposDocumentosLogic {
	
	public void save (TiposDocumentos entity)throws Exception;
	public void update(TiposDocumentos entity)throws Exception;
	public void delete(TiposDocumentos entity)throws Exception;
	public TiposDocumentos findById(Long cliId)throws Exception;
	public List<TiposDocumentos> findAll()throws Exception;

}
