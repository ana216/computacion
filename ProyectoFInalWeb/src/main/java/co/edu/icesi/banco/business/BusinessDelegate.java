package co.edu.icesi.banco.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.icesi.banco.logic.IClienteLogic;
import co.edu.icesi.banco.logic.ICuentasLogic;
import co.edu.icesi.banco.logic.ITiposDocumentosLogic;
import co.edu.icesi.banco.modelo.Clientes;
import co.edu.icesi.banco.modelo.Cuentas;
import co.edu.icesi.banco.modelo.TiposDocumentos;


@Service
@Scope("singleton")
public class BusinessDelegate implements IBusinessDelegate {

	
	@Autowired
	private IClienteLogic clienteLogic;	
	@Autowired
	private ITiposDocumentosLogic tipodocumentosLogic;	
	@Autowired
	private ICuentasLogic cuentasLogic;
	
	@Override
	public void saveCuenta(Cuentas entity) throws Exception {
		cuentasLogic.save(entity);
		
	}

	@Override
	public void updateCuenta(Cuentas entity) throws Exception {
		cuentasLogic.update(entity);
		
	}

	@Override
	public void deleteCuenta(Cuentas entity) throws Exception {
		cuentasLogic.delete(entity);
		
	}

	@Override
	public Cuentas findByIdCuenta(String cueNum) throws Exception {
		
		return cuentasLogic.findById(cueNum);
	}

	@Override
	public List<Cuentas> findAllCuentas() throws Exception {
		
		return cuentasLogic.findAll();
	}

	@Override
	public void saveClientes(Clientes entity) throws Exception {
		clienteLogic.save(entity);
		
	}

	@Override
	public void updateClientes(Clientes entity) throws Exception {
		clienteLogic.update(entity);
		
	}

	@Override
	public void deleteClientes(Clientes entity) throws Exception {
		clienteLogic.delete(entity);
		
	}

	@Override
	public Clientes findByIdClientes(Long cliId) throws Exception {
		
		return clienteLogic.findById(cliId);
	}

	@Override
	public List<Clientes> findAllClientes() throws Exception {
		
		return clienteLogic.findAll();
	}
	
	@Override
	public List<Clientes> findAllActiveClientes()throws Exception{
		return clienteLogic.findAllActiveClients();
	}
	
	@Override
	public List<Cuentas> findAllActiveCuentas()throws Exception{
		return cuentasLogic.findAllActiveCuentas();
	}

	@Override
	public void saveTiposDocumentos(TiposDocumentos entity) throws Exception {
		tipodocumentosLogic.save(entity);
		
	}

	@Override
	public void updateTiposDocumentos(TiposDocumentos entity) throws Exception {
		tipodocumentosLogic.update(entity);
		
	}

	@Override
	public void deleteTiposDocumentos(TiposDocumentos entity) throws Exception {
		tipodocumentosLogic.delete(entity);
		
	}

	@Override
	public TiposDocumentos findByIdTiposDocumentos(Long tipDoc) throws Exception {
		
		return tipodocumentosLogic.findById(tipDoc);
	}

	@Override
	public List<TiposDocumentos> findAllTiposDocumentos() throws Exception {
		
		return tipodocumentosLogic.findAll();
	}

	
	
}
