package co.edu.icesi.banco.logic;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.banco.dao.IClientesDAO;
import co.edu.icesi.banco.dao.ICuentasDAO;
import co.edu.icesi.banco.modelo.Clientes;
import co.edu.icesi.banco.modelo.Cuentas;

@Service
@Scope("singleton")
public class CuentasLogic implements ICuentasLogic {

	@Autowired
	private ICuentasDAO cuentasDAO;

	@Autowired
	private IClientesDAO clientesDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Cuentas entity) throws Exception {
		if (entity.getCueNumero() == null || entity.getCueNumero().equals("")) {
			throw new Exception("La n�mero de cuenta de la cuenta es obligatorio");
		}

		if (entity.getClientes() == null) {
			throw new Exception("El cliente asociado a la cuenta es obligatorio");
		}
		if (clientesDAO.findById(entity.getClientes().getCliId()) == null) {
			throw new Exception("No existe un cliente con id " + entity.getClientes().getCliId());
		}
		if (entity.getCueSaldo() == null) {
			throw new Exception("El saldo de la cuenta es obligatorio");
		}

		if (entity.getCueActiva() == null || entity.getCueActiva().equals("")) {
			throw new Exception("El estado de la cuenta debe ser obligatorio");
		}

		if (entity.getCueClave() == null || entity.getCueClave().equals("")) {
			throw new Exception("La clave de la cuenta debe ser obligatorio");
		}

		if (cuentasDAO.findById(entity.getCueNumero()) != null) {
			throw new Exception("Ya existe una cuenta con n�mero " + entity.getCueNumero());
		}
		cuentasDAO.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Cuentas entity) throws Exception {

		if (entity.getCueNumero() == null || entity.getCueNumero().equals("")) {
			throw new Exception("La n�mero de cuenta de la cuenta es obligatorio");
		}

		if (entity.getClientes() == null) {
			throw new Exception("El cliente asociado a la cuenta es obligatorio");
		}
		if (clientesDAO.findById(entity.getClientes().getCliId()) == null) {
			throw new Exception("No existe un cliente con id " + entity.getClientes().getCliId());
		}
		if (entity.getCueSaldo() == null) {
			throw new Exception("El saldo de la cuenta es obligatorio");
		}

		if (entity.getCueActiva() == null || entity.getCueActiva().equals("")) {
			throw new Exception("El estado de la cuenta debe ser obligatorio");
		}

		if (entity.getCueClave() == null || entity.getCueClave().equals("")) {
			throw new Exception("La clave de la cuenta debe ser obligatorio");
		}

		if (cuentasDAO.findById(entity.getCueNumero()) == null) {
			throw new Exception("No existe una cuenta con n�mero " + entity.getCueNumero());
		}

		cuentasDAO.update(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Cuentas entity) throws Exception {
		String codigo = entity.getCueNumero();

		if (codigo == null || codigo.equals("")) {
			throw new Exception("El n�mero de cuenta es obligatorio");
		}
		// List<Consignaciones> consignaciones =
		// consignacionesDAO.findByProperty("cuentas.cueNumero", codigo);
		// if (consignaciones != null && !consignaciones.isEmpty()) {
		// throw new Exception("La cuenta con n�mero "+codigo+" no se puede eliminar ya
		// que tiene consignaciones asociadas");
		// }
		//
		// List<Retiros> retiros = retirosDAO.findByProperty("cuentas.cueNumero",
		// codigo);
		// if (retiros != null && !retiros.isEmpty()) {
		// throw new Exception("La cuenta con n�mero "+codigo+" no se puede eliminar ya
		// que tiene retiros asociados");
		// }
		Cuentas cu = cuentasDAO.findById(codigo);

		if (cu == null) {
			throw new Exception("No existe una cuenta con el n�mero " + codigo);
		}
		cuentasDAO.delete(cu);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Cuentas findById(String cueNum) throws Exception {
		// validamos que el n�mero de cuenta sea v�lido
		if (cueNum == null || cueNum.trim().equals("")) {
			throw new Exception("Debe ingresar un n�mero de cuenta v�lido");
		}
		Cuentas cue = cuentasDAO.findById(cueNum);

		

		return cue;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Cuentas> findAll() throws Exception {
		List<Cuentas> cuentas = cuentasDAO.findAll();

		// validamos que existan cuentas
		if (cuentas.size() < 1)
			throw new Exception("No existen cuentas");

		return cuentas;
	}
	
	
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Cuentas> findAllActiveCuentas() throws Exception {
		List<Cuentas> cuentasTotales = cuentasDAO.findAll();
		List<Cuentas> cuentas= new ArrayList<Cuentas>();
		
		for (int i = 0; i < cuentasTotales.size(); i++) {
			
			if(cuentasTotales.get(i).getCueActiva().trim().equals("S")) {
				cuentas.add(cuentasTotales.get(i));
			}
		}

		// validamos que existan clientes
		if (cuentas.size() < 1)
			throw new Exception("No existen clientes");

		return cuentas;
	}

	@Override
	public List<Cuentas> findAllActiveCuentasPorCliente(long cli) throws Exception {

		List<Cuentas> cuentas = cuentasDAO.findAllActiveCuentasDeUnCliente(cli);

		// validamos que existan cuentas
		if (cuentas.size() < 1)
			throw new Exception("No existen cuentas para ese cliente");

		return cuentas;
	}
	

}
