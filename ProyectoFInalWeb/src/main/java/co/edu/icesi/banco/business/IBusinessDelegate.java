package co.edu.icesi.banco.business;

import java.util.Date;
import java.util.List;

import co.edu.icesi.banco.modelo.Clientes;
import co.edu.icesi.banco.modelo.Consignaciones;
import co.edu.icesi.banco.modelo.Cuentas;
import co.edu.icesi.banco.modelo.Retiros;
import co.edu.icesi.banco.modelo.TiposDocumentos;
import co.edu.icesi.banco.modelo.Transferencias;
import co.edu.icesi.banco.modelo.Usuarios;

public interface IBusinessDelegate {

	
	public void saveCuenta (Cuentas entity)throws Exception;
	public void updateCuenta(Cuentas entity)throws Exception;
	public void deleteCuenta(Cuentas entity)throws Exception;
	public Cuentas findByIdCuenta(String cueNum)throws Exception;
	public List<Cuentas> findAllCuentas()throws Exception;
	public void saveClientes(Clientes entity)throws Exception;
	public void updateClientes(Clientes entity)throws Exception;
	public void deleteClientes(Clientes entity)throws Exception;
	public Clientes findByIdClientes(Long cliId)throws Exception;
	public List<Clientes> findAllClientes()throws Exception;
	public List<Clientes> findAllActiveClientes()throws Exception;
	public void saveTiposDocumentos (TiposDocumentos entity)throws Exception;
	public void updateTiposDocumentos(TiposDocumentos entity)throws Exception;
	public void deleteTiposDocumentos(TiposDocumentos entity)throws Exception;
	public TiposDocumentos findByIdTiposDocumentos(Long tipDoc)throws Exception;
	public List<TiposDocumentos> findAllTiposDocumentos()throws Exception;
	public List<Cuentas> findAllActiveCuentas()throws Exception;
	public Usuarios findByIdUsuario(Long usuCedula)throws Exception;
	public List<Usuarios> findAllUsuarios()throws Exception;
	public void saveConsignacion(Consignaciones entity)throws Exception;
	public List<Consignaciones> findAllConsignaciones()throws Exception;
	public void saveRetiros(Retiros entity)throws Exception;
	public List<Retiros> findAllRetiros()throws Exception;
	public void saveTransferencias(Transferencias entity)throws Exception;
	public List<Transferencias> findAllTransferencias()throws Exception;
	public List<Cuentas> findAllCentasDeUnCliente(Long cliId)throws Exception;
	public List<Consignaciones> findConsignacionesPorClienteYFecha(Long cliId, Date fechaIni, Date fechaFin)throws Exception;

	
}
