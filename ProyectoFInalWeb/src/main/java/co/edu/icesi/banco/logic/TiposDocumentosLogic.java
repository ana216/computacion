package co.edu.icesi.banco.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.banco.dao.ITipoDocumentoDAO;
import co.edu.icesi.banco.modelo.Clientes;
import co.edu.icesi.banco.modelo.TiposDocumentos;

@Service
@Scope("singleton")
public class TiposDocumentosLogic implements ITiposDocumentosLogic {

	@Autowired
	private ITipoDocumentoDAO tiposDocumentosDAO;

//	@Autowired
//	private IClientesDAO clientesDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(TiposDocumentos entity) throws Exception {

		if (entity.getTdocCodigo() == 0) {
			throw new Exception("El c�digo del tipo de documento es obligatorio");
		}

		if (entity.getTdocNombre() == null || entity.getTdocNombre().equals("")) {
			throw new Exception("El nombre del tipo de documento es olbigatorio");
		}

		if (tiposDocumentosDAO.findById(entity.getTdocCodigo()) != null) {
			throw new Exception("Ya existe un tipo de documento con el c�digo " + entity.getTdocCodigo());
		}
		tiposDocumentosDAO.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(TiposDocumentos entity) throws Exception {
		if (entity.getTdocCodigo() == 0) {
			throw new Exception("El c�digo del tipo de documento es obligatorio");
		}

		if (entity.getTdocNombre() == null || entity.getTdocNombre().equals("")) {
			throw new Exception("El nombre del tipo de documento es olbigatorio");
		}

		if (tiposDocumentosDAO.findById(entity.getTdocCodigo()) != null) {
			throw new Exception("Ya existe un tipo de documento con el c�digo " + entity.getTdocCodigo());
		}
		tiposDocumentosDAO.save(entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(TiposDocumentos entity) throws Exception {

		Long codigo = entity.getTdocCodigo();
		if (codigo == null || codigo == 0) {
			throw new Exception("El c�digo del tipo de documento es obligatorio");
		}
		List<Clientes> clientes = (List<Clientes>) tiposDocumentosDAO.findById(codigo).getClienteses();
		if (clientes != null && !clientes.isEmpty()) {
			throw new Exception("El tipo de documento con el c�digo " + codigo
					+ " no se puede eliminar ya que tiene clientes asociados");
		}

		TiposDocumentos tip = tiposDocumentosDAO.findById(codigo);

		if (tip == null) {
			throw new Exception("No existe un tipo de documento con el c�digo " + codigo);
		}
		tiposDocumentosDAO.delete(tip);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TiposDocumentos findById(Long tipId) throws Exception {
		// validamos que el id del documento sea v�lido
		if (tipId == 0) {
			throw new Exception("Debe ingresar un id del tipo de documento v�lido");
		}
		TiposDocumentos tipod = tiposDocumentosDAO.findById(tipId);

//		// validamos que exista un tipo de documento con ese id
//		if (tipod == null) {
//			throw new Exception("El tipo de documento no existe");
//		}

		return tipod;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<TiposDocumentos> findAll() throws Exception {
		List<TiposDocumentos> tiposDocumentos = tiposDocumentosDAO.findAll();

		// validamos que existan clientes
		if (tiposDocumentos.size() < 1)
			throw new Exception("No existen tipos de documentos");

		return tiposDocumentos;
	}

}
