package co.edu.icesi.banco.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.banco.dao.IClientesDAO;
import co.edu.icesi.banco.modelo.Clientes;
import co.edu.icesi.banco.modelo.Cuentas;

@Service
@Scope("singleton")
public class ClienteLogic implements IClienteLogic {

	@Autowired
	private IClientesDAO clientesDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Clientes entity) throws Exception {
		// Se valida que se ingrese un cliente
		if (entity == null) {
			throw new Exception("Debe ingresar un cliente");
		}
		// Validamps que el id no sea null
		if (entity.getCliId() == 0) {
			throw new Exception("Debe ingresar el id del cliente");
		}

		/// validamos que la direccion no venga nula ni vacia
		if (entity.getCliDireccion() == null || entity.getCliDireccion().trim().equals("")) {
			throw new Exception("Debe ingresar la direcci�n del cliente");
		}

		/// validamos que el email no venga nulo ni vacio
		if (entity.getCliMail() != null && entity.getCliMail().length() > 50) {
			throw new Exception("El Mail debe tener menos de 50 caracteres");
		}

		/// validamos que el nombre no venga nulo ni vacio
		if (entity.getCliNombre() == null || entity.getCliNombre().trim().equals("")) {
			throw new Exception("Debe ingresar un nombre");
		}

		/// validamos que el tipo de documento no venga nulo ni vacio
		if (entity.getTiposDocumentos() == null || entity.getTiposDocumentos().getTdocCodigo() == 0) {
			throw new Exception("Debe ingresar un tipo de documento");
		}

		/// validamos que el telefono no venga vacio
		if (entity.getCliTelefono() != null && entity.getCliTelefono().trim().equals("")) {
			throw new Exception("Debe ingresar un tel�fono v�lido");
		}

		/// validamos que el email sea v�lido
		// Patr�n para validar el email
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(entity.getCliMail());
		if (entity.getCliMail() != null && !mather.find()) {
			throw new Exception("Debe ingresar un mail v�lido");
		}

		clientesDAO.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Clientes entity) throws Exception {
		// Se valida que se ingrese un cliente
		if (entity == null) {
			throw new Exception("Debe ingresar un cliente");
		}
		// Validamps que el id no sea null
		if (entity.getCliId() == 0) {
			throw new Exception("Debe ingresar el id del cliente");
		}

		/// validamos que la direccion no venga nula ni vacia
		if (entity.getCliDireccion() == null || entity.getCliDireccion().trim().equals("")) {
			throw new Exception("Debe ingresar la direcci�n del cliente");
		}

		/// validamos que el email no venga nulo ni vacio
		if (entity.getCliMail() != null && entity.getCliMail().length() > 50) {
			throw new Exception("El Mail debe tener menos de 50 caracteres");
		}

		/// validamos que el nombre no venga nulo ni vacio
		if (entity.getCliNombre() == null || entity.getCliNombre().trim().equals("")) {
			throw new Exception("Debe ingresar un nombre");
		}

		/// validamos que el tipo de documento no venga nulo ni vacio
		if (entity.getTiposDocumentos() == null || entity.getTiposDocumentos().getTdocCodigo() == 0) {
			throw new Exception("Debe ingresar un tipo de documento");
		}

		/// validamos que el telefono no venga vacio
		if (entity.getCliTelefono() != null && entity.getCliTelefono().trim().equals("")) {
			throw new Exception("Debe ingresar un tel�fono v�lido");
		}

		/// validamos que el email sea v�lido
		// Patr�n para validar el email
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(entity.getCliMail());
		if (entity.getCliMail() != null && !mather.find()) {
			throw new Exception("Debe ingresar un mail v�lido");
		}

		clientesDAO.update(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Clientes entity) throws Exception {
		Clientes cli = clientesDAO.findById(entity.getCliId());

		// TODO: Es necesario verificar el c�digo del cliente?

		// validamos que el cliente exista
		if (cli == null) {
			throw new Exception("Debe ingresar un cliente que exista");
		}

		//TODO:
		List<Cuentas> cuentas = (List<Cuentas>) cli.getCuentases();
		if (cuentas != null && !cuentas.isEmpty()) {
			throw new Exception(
					"El cliente con id " + entity.getCliId() + " no se puede eliminar ya que tiene cuentas asociadas");
		}

		clientesDAO.delete(cli);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Clientes findById(Long cliId) throws Exception {

		// validamos que el id del cliente sea v�lido
		if (cliId == 0) {
			throw new Exception("Debe ingresar un id del cliente v�lido");
		}
		Clientes cli = clientesDAO.findById(cliId);

		// validamos que exista un cliente con ese id
		if (cli == null) {
			throw new Exception("El cliente no existe");
		}

		return cli;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Clientes> findAll() throws Exception {
		List<Clientes> clientes = clientesDAO.findAll();

		// validamos que existan clientes
		if (clientes.size() < 1)
			throw new Exception("No existen clientes");

		return clientes;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)

	public List<Clientes> findAllActiveClients() throws Exception {
		List<Clientes> clientesTotales = clientesDAO.findAll();
		List<Clientes> clientes= new ArrayList<Clientes>();
		
		for (int i = 0; i < clientesTotales.size(); i++) {
			
			if(clientesTotales.get(i).getCliHabilitado().trim().equals("S")) {
				clientes.add(clientesTotales.get(i));
			}
		}

		// validamos que existan clientes
		if (clientes.size() < 1)
			throw new Exception("No existen clientes");

		return clientes;
	}

}
