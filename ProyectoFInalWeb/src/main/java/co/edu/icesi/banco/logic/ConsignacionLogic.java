package co.edu.icesi.banco.logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.banco.dao.IConsignacionesDAO;
import co.edu.icesi.banco.dao.ICuentasDAO;
import co.edu.icesi.banco.modelo.Consignaciones;
import co.edu.icesi.banco.modelo.Cuentas;

@Service
@Scope("singleton")
public class ConsignacionLogic implements IConsignacionLogic {

	@Autowired
	private IConsignacionesDAO consignacionesDAO;

	@Autowired
	private ICuentasDAO cuentasDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Consignaciones entity) throws Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new Exception("Debe ingresar una consignación");
		}

		// Validamos que el id no sea null
		if (entity.getId() == null) {
			throw new Exception("Debe ingresar el id de la consignación");
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
		if (entity.getConDescripcion() == null || entity.getConDescripcion().trim().equals("")) {
			throw new Exception("La descripción debe ser válida");
		}

		/// validamos el valor de la consignacion
		if (entity.getConValor() == null || entity.getConValor().compareTo(new BigDecimal("0")) < 1) {
			throw new Exception("El valor no es válido");
		}

		// Calculo consecutivo
		String cueNumero = entity.getId().getCueNumero();
		List<Consignaciones> cons = consignacionesDAO.findAll();
		List<Consignaciones> consCuentaNumIgual = new ArrayList<Consignaciones>();
		for (int i = 0; i < cons.size(); i++) {
			if (cons.get(i).getId().getCueNumero().equals(cueNumero)) {
				consCuentaNumIgual.add(cons.get(i));
			}
		}
		long conId = 1;
		if (consCuentaNumIgual.size() < 1) {
			entity.getId().setConCodigo(conId);
		} else {
			entity.getId()
					.setConCodigo(consCuentaNumIgual.get(consCuentaNumIgual.size() - 1).getId().getConCodigo() + 1);
		}

		Cuentas cuenta = cuentasDAO.findById(cueNumero);
		if(cuenta==null) {
			throw new Exception("La cuenta origen no existe");
		}
		BigDecimal nuevoValor = cuenta.getCueSaldo().add(entity.getConValor());
		cuenta.setCueSaldo(nuevoValor);


		cuentasDAO.update(cuenta);
		consignacionesDAO.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Consignaciones entity) throws Exception {
		if (entity == null) {
			throw new Exception("Debe ingresar una consignación");
		}

		// Validamos que el id no sea null
		if (entity.getId() == null) {
			throw new Exception("Debe ingresar el id de la consignación");
		}

		// Validamos que el codigo sea valido
		if (entity.getId().getConCodigo() == 0) {
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
		if (entity.getConDescripcion() == null || entity.getConDescripcion().trim().equals("")) {
			throw new Exception("La descripción debe ser válida");
		}

		/// validamos el valor de la consignacion
		if (entity.getConValor() == null || entity.getConValor().compareTo(new BigDecimal("0")) < 1) {
			throw new Exception("El valor no es válido");
		}

		consignacionesDAO.update(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Consignaciones entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Consignaciones findById(Long cliId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Consignaciones> findAll() throws Exception {
		List<Consignaciones> consignaciones = consignacionesDAO.findAll();

		// validamos que existan clientes
		if (consignaciones.size() < 1)
			throw new Exception("No existen consignaciones");

		return consignaciones;

	}

	@Override
	public List<Consignaciones> findConsignacionesPorClienteYFecha(Long cliId, Date fechaIni, Date fechaFin)
			throws Exception {
	

		// validamos que las fechas este correctas
		if(fechaIni.before(fechaFin)) {
			throw new Exception("Las fechas son inválidas");
		}
		
		List<Consignaciones> consignaciones = consignacionesDAO.findConsignacionesPorClienteYFecha(cliId, fechaIni, fechaFin);

		// validamos que existan consignaciones
		if (consignaciones.size() < 1)
			throw new Exception("No existen consignaciones");

		return consignaciones;
		
		
	}

}
