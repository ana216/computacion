package co.edu.icesi.banco.logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.banco.dao.ITransferenciasDAO;
import co.edu.icesi.banco.dao.ICuentasDAO;
import co.edu.icesi.banco.modelo.Transferencias;
import co.edu.icesi.banco.modelo.Cuentas;

@Service
@Scope("singleton")
public class TransferenciaLogic implements ITransferenciaLogic {

	@Autowired
	private ITransferenciasDAO transferenciasDAO;

	@Autowired
	private ICuentasDAO cuentasDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Transferencias entity) throws Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new Exception("Debe ingresar una transferencia");
		}

		// validamos que la cuenta de destino no venga nulo ni vacio
		if (entity.getCuentasByCueNumDestino() == null || entity.getCuentasByCueNumDestino().getCueNumero() == null
				|| entity.getCuentasByCueNumDestino().getCueNumero().trim().equals("")) {
			throw new Exception("Debe asociar una cuenta de destino válida");
		}

		// validamos que la cuenta de origen no venga nulo ni vacio
		if (entity.getCuentasByCueNumOrigen() == null || entity.getCuentasByCueNumOrigen().getCueNumero() == null
				|| entity.getCuentasByCueNumOrigen().getCueNumero().trim().equals("")) {
			throw new Exception("Debe asociar una cuenta de origen válida");
		}

		// Validamos que el número de la cuenta de origen sea valido
		if (entity.getCuentasByCueNumOrigen().getCueNumero() == null
				|| entity.getCuentasByCueNumOrigen().getCueNumero().trim().equals("")) {
			throw new Exception("Debe ingresar el número de la cuenta de origen");
		}

		// Validamos que el número de la cuenta de destino sea valido
		if (entity.getCuentasByCueNumDestino().getCueNumero() == null
				|| entity.getCuentasByCueNumDestino().getCueNumero().trim().equals("")) {
			throw new Exception("Debe ingresar el número de la cuenta de destino");
		}

		/// validamos que la descripción sea valida
		if (entity.getTransDescripcion() == null || entity.getTransDescripcion().trim().equals("")) {
			throw new Exception("La descripción debe ser válida");
		}

		/// validamos el valor de la transferencia
		if (entity.getTransMonto() == null || entity.getTransMonto().compareTo(new BigDecimal("0")) < 1) {
			throw new Exception("El valor no es válido");
		}

		// Calculo consecutivo

		List<Transferencias> cons = transferenciasDAO.findAll();

		if(cons.size()<1) {
			entity.setTransCodigo(1);
		}else {
			entity.setTransCodigo(cons.get(cons.size() - 1).getTransCodigo() + 1);
		}


		// Verificacion saldo cuenta origen
		Cuentas cuenta = entity.getCuentasByCueNumOrigen();
		Cuentas cuentaDest = entity.getCuentasByCueNumDestino();
		if(cuenta==null) {
			throw new Exception("La cuenta origen no existe");
		}
		if(cuentaDest==null) {
			throw new Exception("La cuenta destino no existe");
		}
		if(cuentaDest.getCueNumero().equals(cuenta.getCueNumero())) {
			throw new Exception("La cuenta de destino y origen es la misma");
		}
		
		
		if(cuenta.getCueActiva().trim().equalsIgnoreCase("N")) {
			throw new Exception("La cuenta origen no esta activa");
		}
		if(cuentaDest.getCueActiva().trim().equalsIgnoreCase("N")) {
			throw new Exception("La cuenta destino no esta activa");
		}
		
		if (cuenta.getCueSaldo().compareTo(entity.getTransMonto()) < 0) {
			throw new Exception("La cuenta origen no tiene saldo suficiente para hacer la transferencia");
		}
		
		BigDecimal nuevoValor = cuenta.getCueSaldo().subtract(entity.getTransMonto());
		cuenta.setCueSaldo(nuevoValor);
		nuevoValor=cuentaDest.getCueSaldo().add(entity.getTransMonto());
		cuentaDest.setCueSaldo(nuevoValor);

		
		cuentasDAO.update(cuenta);
		cuentasDAO.update(cuentaDest);
		transferenciasDAO.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Transferencias entity) throws Exception {

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Transferencias entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Transferencias findById(Long cliId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Transferencias> findAll() throws Exception {
		List<Transferencias> transferencias = transferenciasDAO.findAll();

		// validamos que existan clientes
		if (transferencias.size() < 1)
			throw new Exception("No existen transferencias");

		return transferencias;

	}
}
