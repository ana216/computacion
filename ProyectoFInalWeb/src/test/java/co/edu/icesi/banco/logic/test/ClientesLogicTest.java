package co.edu.icesi.banco.logic.test;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import co.edu.icesi.banco.logic.IClienteLogic;
import co.edu.icesi.banco.logic.ITiposDocumentosLogic;
import co.edu.icesi.banco.modelo.Clientes;
import co.edu.icesi.banco.modelo.TiposDocumentos;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)

public class ClientesLogicTest {

	public static final Long cliId = 33324421L;
	public static final Long tdocId = 10L;

	private static final Logger log = LoggerFactory.getLogger(ClientesLogicTest.class);

	@Autowired
	private IClienteLogic clienteLogic;

	
	@Autowired
	private ITiposDocumentosLogic tipodocumentosLogic;

	@Test
	public void crearCliente() {

		log.info("Inicio crearClienteTest");

		try {

			// Consulatr el cliente
			Clientes cliente = new Clientes();
			cliente.setCliDireccion("Avd siempre viva 123");
			cliente.setCliId(cliId);
			cliente.setCliMail("homerojsimpson@gmail.com");
			cliente.setCliNombre("Homer J Simpson");
			cliente.setCliTelefono("123456789");

			// Se consulta el tipo de documento

			TiposDocumentos tiposDocumentos = tipodocumentosLogic.findById(tdocId);
			cliente.setTiposDocumentos(tiposDocumentos);

			clienteLogic.save(cliente);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Test
	public void consultarCliente() {

		log.info("Inicio consultarClienteTest");

		Clientes cliente;
		try {
			cliente = clienteLogic.findById(cliId);

			if(cliente!=null) {
				log.info("Id: " + cliente.getCliId());
				log.info("Nombre: " + cliente.getCliNombre());
			}else {
				log.info("El cliente a consultar no existe");
			}
			
			
		} catch (Exception e) {

			log.error(e.getMessage());
		}

	}

	@Test
	public void modificarCliente() {

		log.info("Inicio modificarClienteTest");

		// Se consulta el cliente
		Clientes cliente;
		try {
			cliente = clienteLogic.findById(cliId);
			if(cliente!=null) {
				cliente.setCliDireccion("Carrera 123");

				clienteLogic.update(cliente);
			}else {
				log.info("El cliente a modificar no existe");
			}
			
			
		} catch (Exception e) {


			log.error(e.getMessage());
		}
	

	}

	@Test
	public void eliminarCliente() {

		log.info("Inicio eliminarClienteTest");

		Clientes cliente;
		try {
			cliente = clienteLogic.findById(cliId);
			if(cliente!=null) {
				clienteLogic.delete(cliente);
				log.info("Se elimin� el cliente: "+cliente.getCliNombre());
			}else {
				log.info("El cliente a eliminar no existe");
			}

			
		} catch (Exception e) {

			log.error(e.getMessage());
		}
		

	}

}
