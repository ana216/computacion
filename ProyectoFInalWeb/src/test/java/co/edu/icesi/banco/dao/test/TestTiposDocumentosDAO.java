package co.edu.icesi.banco.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import co.edu.icesi.banco.modelo.TiposDocumentos;
import co.edu.icesi.banco.modelo.TiposUsuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestTiposDocumentosDAO {
	
	public static final Long cliId = 333221L;
	public static final String cuenId = "000-000-000";
	private static final Long tipoDocId = 9L;
	
	private static final Logger log = LoggerFactory.getLogger(TestClientesDAO.class);
	
	


	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void crearTipoDocumento() {
		
		log.info("Inicio crearTiposDocumentosTest");		

				
		//Consultar el tipoDocumento
		TiposDocumentos tipoDocumento = tipoDocumentoDAO.findById(tipoDocId);
		assertNull("El tipoDocumento ya existe: " , tipoDocumento);		
		
		
		tipoDocumento = new TiposDocumentos();
		tipoDocumento.setTdocNombre("PASAPORTE");	
		tipoDocumento.setTdocCodigo(tipoDocId);	

		tipoDocumentoDAO.save(tipoDocumento);
		
		log.info("Se guard� el tipo de documento"+tipoDocumento.getTdocNombre()+", con el c�digo: "+tipoDocumento.getTdocCodigo());
		

		
	}
	
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void consultarTipoDocumento() {
		

		log.info("Inicio consultarTipoDocumentoTest");		


		TiposDocumentos tipoDocumento = tipoDocumentoDAO.findById(tipoDocId);
		//Verificar existencia
		assertNotNull("El TipoDocumento no existe: " , tipoDocumento);
		log.info("Codigo del tipo de documento: "+tipoDocumento.getTdocCodigo());
		log.info("Nombre del tipo de documento: "+tipoDocumento.getTdocNombre());		
		
		

		
	}
	
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificarTipoDocumento() {
		
		log.info("Inicio modificarTipoDocumentoTest");		

				
		//Consultar el tipoDocumento
		TiposDocumentos tipoDocumento = tipoDocumentoDAO.findById(tipoDocId);
		//Verificar existencia
		assertNotNull("El TipoDocumento no existe: " , tipoDocumento);
		String nom= tipoDocumento.getTdocNombre();
		tipoDocumento.setTdocNombre("Numero de licencia");		

		tipoDocumentoDAO.update(tipoDocumento);
		log.info("Se modifico el nombre del tipo de documento "+ nom+" por el de "+tipoDocumento.getTdocNombre());

			
		

		
	}
	
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void eliminarTipoDocumento() {
		
		log.info("Inicio eliminarTipoDocumentoTest");		


		TiposDocumentos tipoDocumento = tipoDocumentoDAO.findById(tipoDocId);
		assertNotNull("El TipoDocumento no existe: " , tipoDocumento);		

		tipoDocumentoDAO.delete(tipoDocumento);
		
		log.info("Se elimin� el tipo de documento"+tipoDocumento.getTdocNombre()+", que tiene el c�digo: "+tipoDocumento.getTdocCodigo());
			
		

		
	}

}
