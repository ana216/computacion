package co.edu.icesi.banco.dao.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

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
import co.edu.icesi.banco.dao.ICuentasDAO;
import co.edu.icesi.banco.dao.ITipoDocumentoDAO;
import co.edu.icesi.banco.dao.ITiposUsuariosDAO;
import co.edu.icesi.banco.modelo.TiposUsuarios;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestTiposUsuariosDAO {
	
	public static final Long cliId = 333221L;
	public static final Long tdocId = 10L;
	public static final String cuenId = "000-000-000";
	private static final Long tipoUsId = 100L;
	
	private static final Logger log = LoggerFactory.getLogger(TestClientesDAO.class);
	
	
	@Autowired
	private IClientesDAO clientesDAO;

	@Autowired
	private ITipoDocumentoDAO tipodocumentoDAO;
	
	@Autowired
	private ICuentasDAO cuentasDAO;
	
	@Autowired
	private ITiposUsuariosDAO tiposUsuariosDAO;



	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void crearTipoUsuario() {
		
		log.info("Inicio crearTiposUsuariosTest");		


		//Se consulta el tipoUsuario
		TiposUsuarios tipoUsuario = tiposUsuariosDAO.findById(tipoUsId);
		assertNull("El tipoUsuario ya existe: " , tipoUsuario);
		//Instanciar la entidad de TiposUsuarios, ya que se va a persistir un tipoUsuario
		tipoUsuario = new TiposUsuarios();
		tipoUsuario.setTusuNombre("ANALISTA");	
		tipoUsuario.setTusuCodigo(tipoUsId);			
		
		tiposUsuariosDAO.save(tipoUsuario);
		

		
	}
	
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void consultarTipoUsuario() {
		

		log.info("Inicio consultarTiposUsuariosTest");
		
		
				
		//Se consulta el tipoUsuario
		TiposUsuarios tipoUsuario = tiposUsuariosDAO.findById(tipoUsId);
		//Si no existe
		assertNotNull("El TipoUsuario no existe: " , tipoUsuario);
		//Si existe
		log.info("Código: "+tipoUsuario.getTusuCodigo());
		log.info("Nombre: "+tipoUsuario.getTusuNombre());
	
		

		
	}
	
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificarTipoUsuario() {
		
		log.info("inicio modificarTiposUsuariosTest");		

			
		//Se consulta el tipoDocumento
		TiposUsuarios tipoUsuario = tiposUsuariosDAO.findById(tipoUsId);
		//Verificar existencia
		assertNotNull("El TipoUsuario no existe: " , tipoUsuario);
		tipoUsuario.setTusuNombre("CONTADOR");		
		
		tiposUsuariosDAO.update(tipoUsuario);
		
			
		

		
	}
	
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void eliminarCuenta() {
		
		log.info("Inicio eliminarTiposUsuariosTest");		

			
		//Consultar el tipoDocumento
		TiposUsuarios tipoUsuario = tiposUsuariosDAO.findById(tipoUsId);
		assertNotNull("El TipoUsuario no existe: " , tipoUsuario);		

		tiposUsuariosDAO.delete(tipoUsuario);
		
			
		

		
	}

}
