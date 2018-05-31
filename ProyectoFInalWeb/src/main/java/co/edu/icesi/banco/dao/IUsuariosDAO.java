package co.edu.icesi.banco.dao;

import java.util.List;


import co.edu.icesi.banco.modelo.Usuarios;

public interface IUsuariosDAO {
	public void save(Usuarios entity);
	public void update(Usuarios entity);
	public void delete(Usuarios entity);
	public Usuarios findById(Long usuCedula);
	public List<Usuarios> findAll();

}
