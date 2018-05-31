package co.edu.icesi.banco.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.banco.dao.ITipoDocumentoDAO;
import co.edu.icesi.banco.dao.IUsuariosDAO;
import co.edu.icesi.banco.modelo.TiposDocumentos;
import co.edu.icesi.banco.modelo.Usuarios;


@Service
@Scope("singleton")
public class UsuariosLogic implements IUsuarioLogic {
	
	@Autowired
	private IUsuariosDAO usuariosDAO;

	@Override
	public void save(Usuarios entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Usuarios entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Usuarios entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Usuarios findById(Long usuCedula) throws Exception {
		if (usuCedula == 0) {
			throw new Exception("Debe ingresar un id del tipo de documento v�lido");
		}
		Usuarios usuario = usuariosDAO.findById(usuCedula);


		return usuario;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Usuarios> findAll() throws Exception {
		List<Usuarios> usuarios = usuariosDAO.findAll();

		// validamos que existan clientes
		if (usuarios.size() < 1)
			throw new Exception("No existen usuarios");

		return usuarios;
	}

}
