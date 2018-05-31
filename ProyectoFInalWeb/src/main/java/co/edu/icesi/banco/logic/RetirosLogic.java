package co.edu.icesi.banco.logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import co.edu.icesi.banco.dao.ICuentasDAO;
import co.edu.icesi.banco.dao.IRetirosDAO;
import co.edu.icesi.banco.modelo.Cuentas;
import co.edu.icesi.banco.modelo.Retiros;

@Service
@Scope("singleton")
public class RetirosLogic implements IRetirosLogic {

	@Autowired
	private IRetirosDAO retirosDAO;

	@Autowired
	private ICuentasDAO cuentasDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Retiros entity) throws Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new Exception("Debe ingresar un retiro");
		}

		// Validamos que el id no sea null
		if (entity.getId() == null) {
			throw new Exception("Debe ingresar el id del retiro");
		}

		// Validamos que el número de la cuenta sea valido
		if (entity.getId().getCueNumero() == null || entity.getId().getCueNumero().trim().equals("")) {
			throw new Exception("Debe ingresar el número de la cuenta");
		}

		// validamos que el tipo de documento no venga nulo ni vacio
		if (entity.getCuentas() == null || entity.getCuentas().getCueNumero() == null
				|| entity.getCuentas().getCueNumero().trim().equals("")) {
			throw new Exception("Debe asociar una cuenta válida");
		}

		/// validamos que la descripción sea valida
		if (entity.getRetDescripcion() == null || entity.getRetDescripcion().trim().equals("")) {
			throw new Exception("La descripción debe ser válida");
		}

		/// validamos el valor de la consignacion
		if (entity.getRetValor() == null || entity.getRetValor().compareTo(new BigDecimal("0")) < 1) {
			throw new Exception("El valor no es válido");
		}

		// Calculo consecutivo
		String cueNumero = entity.getId().getCueNumero();
		List<Retiros> cons = retirosDAO.findAll();
		List<Retiros> consCuentaNumIgual = new ArrayList<Retiros>();
		for (int i = 0; i < cons.size(); i++) {
			if (cons.get(i).getId().getCueNumero().equals(cueNumero)) {
				consCuentaNumIgual.add(cons.get(i));
			}
		}
		long conId = 1;
		if (consCuentaNumIgual.size() < 1) {
			entity.getId().setRetCodigo(conId);
		} else {
			entity.getId()
					.setRetCodigo(consCuentaNumIgual.get(consCuentaNumIgual.size() - 1).getId().getRetCodigo() + 1);
		}

		Cuentas cuenta = cuentasDAO.findById(cueNumero);
		if(cuenta==null) {
			throw new Exception("La cuenta origen no existe");
		}
		
		if (cuenta.getCueSaldo().compareTo(entity.getRetValor()) < 0) {
			throw new Exception("La cuenta no tiene saldo suficiente");
		}
		BigDecimal nuevoValor = cuenta.getCueSaldo().subtract(entity.getRetValor());
		cuenta.setCueSaldo(nuevoValor);

		cuentasDAO.update(cuenta);
		retirosDAO.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Retiros entity) throws Exception {
		if (entity == null) {
			throw new Exception("Debe ingresar una consignación");
		}

		// Validamos que el id no sea null
		if (entity.getId() == null) {
			throw new Exception("Debe ingresar el id de la consignación");
		}

		// Validamos que el codigo sea valido
		if (entity.getId().getRetCodigo() == 0) {
			throw new Exception("Debe ingresar el codigo de la consignación");
		}

		// Validamos que el número de la cuenta sea valido
		if (entity.getId().getCueNumero() == null || entity.getId().getCueNumero().trim().equals("")) {
			throw new Exception("Debe ingresar el número de la cuenta");
		}

		// validamos que el tipo de documento no venga nulo ni vacio
		if (entity.getCuentas() == null || entity.getCuentas().getCueNumero() == null
				|| entity.getCuentas().getCueNumero().trim().equals("")) {
			throw new Exception("Debe asociar una cuenta válida");
		}

		/// validamos que la descripción sea valida
		if (entity.getRetDescripcion() == null || entity.getRetDescripcion().trim().equals("")) {
			throw new Exception("La descripción debe ser válida");
		}

		/// validamos el valor de la consignacion
		if (entity.getRetValor() == null || entity.getRetValor().compareTo(new BigDecimal("0")) < 1) {
			throw new Exception("El valor no es válido");
		}

		retirosDAO.update(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Retiros entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Retiros findById(Long cliId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Retiros> findAll() throws Exception {
		List<Retiros> retiros = retirosDAO.findAll();

		// validamos que existan clientes
		if (retiros.size() < 1)
			throw new Exception("No existen Retiros");

		return retiros;

	}

}
