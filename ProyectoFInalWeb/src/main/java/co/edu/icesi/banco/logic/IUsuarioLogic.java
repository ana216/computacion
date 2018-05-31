package co.edu.icesi.banco.logic;

import java.util.List;

import co.edu.icesi.banco.modelo.Usuarios;

public interface IUsuarioLogic {
	
	public void save (Usuarios entity)throws Exception;
	public void update(Usuarios entity)throws Exception;
	public void delete(Usuarios entity)throws Exception;
	public Usuarios findById(Long usuCedula)throws Exception;
	public List<Usuarios> findAll()throws Exception;

}
