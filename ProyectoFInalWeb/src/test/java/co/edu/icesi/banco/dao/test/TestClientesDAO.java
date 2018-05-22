package co.edu.icesi.banco.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.banco.dao.IClientesDAO;
import co.edu.icesi.banco.dao.ITipoDocumentoDAO;
import co.edu.icesi.banco.modelo.Clientes;

import co.edu.icesi.banco.modelo.TiposDocumentos;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestClientesDAO {

	public static final Long cliId = 33324421L;
	public static final Long tdocId = 10L;
	
	private static final Logger log = LoggerFactory.getLogger(TestClientesDAO.class);
	
	
	@Autowired
	private IClientesDAO clientesDAO;

	@Autowired
	private ITipoDocumentoDAO tipodocumentoDAO;
	
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void crearCliente() {

		log.info("Inicio crearClienteTest");

		// Instanciar la fabrica de Entity manager

		// Consulatr el cliente
		Clientes cliente = clientesDAO.findById(cliId);
		assertNull("El cliente ya existe: ", cliente);
		// Instanciar la entidad de clientes, ya que se va a persistir un cliente
		cliente = new Clientes();
		cliente.setCliDireccion("Avd siempre viva 123");
		cliente.setCliId(cliId);
		cliente.setCliMail("homerojsimpson@gmail.com");
		cliente.setCliNombre("Homer J Simpson");
		cliente.setCliTelefono("123456789");
		cliente.setCliHabilitado("S");
		// Se consulta el tipo de documento
		TiposDocumentos tiposDocumentos = tipodocumentoDAO.findById(tdocId);
		assertNotNull("El tipo de documento NO existe", tiposDocumentos);
		cliente.setTiposDocumentos(tiposDocumentos);
		
		clientesDAO.save(cliente);
	}
	
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void consultarCliente() {
		
		log.info("Inicio consultarClienteTest");


		Clientes cliente = clientesDAO.findById(cliId);		
		//Si no existe
		assertNotNull("El cliente NO existe: " , cliente);		
		//Si existe
		log.info("Id: "+cliente.getCliId());
		log.info("Nombre: "+cliente.getCliNombre());		
		
		
		

		
	}
	
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificarCuenta() {
		
		log.info("Inicio modificarClienteTest");
		
		
		//Se consulta el cliente
		Clientes cliente = clientesDAO.findById(cliId);		
		//Si no existe
		assertNotNull("El cliente no existe: " , cliente);		
		//Si existe
		cliente.setCliDireccion("Carrera 123");		

		clientesDAO.update(cliente);
		
			
		

		
	}
	
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void eliminarCuenta() {
		
		log.info("Inicio eliminarClienteTest");


		
		Clientes cliente = clientesDAO.findById(cliId);		
		//Si no existe
		assertNotNull("El cliente no existe: " , cliente);		
	

		clientesDAO.delete(cliente);
		
		
			
		

		
	}

}
