package co.edu.icesi.banco.logic.test;


import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



import co.edu.icesi.banco.logic.ITiposDocumentosLogic;
import co.edu.icesi.banco.modelo.TiposDocumentos;

public class TiposDocumentosLogicTest {

	public static final Long cliId = 333221L;
	public static final String cuenId = "000-000-000";
	private static final Long tipoDocId = 9L;

	private static final Logger log = LoggerFactory.getLogger(TiposDocumentosLogicTest.class);

	@Autowired
	private ITiposDocumentosLogic tipoDocumentoLogic;

	@Ignore
	@Test
	public void crearTipoDocumento() {

		log.info("Inicio crearTiposDocumentosTest");

		// Consultar el tipoDocumento
		TiposDocumentos tipoDocumento;
		try {

			tipoDocumento = new TiposDocumentos();
			tipoDocumento.setTdocNombre("PASAPORTE");
			tipoDocumento.setTdocCodigo(tipoDocId);

			tipoDocumentoLogic.save(tipoDocumento);

			log.info("Se guard� el tipo de documento" + tipoDocumento.getTdocNombre() + ", con el c�digo: "
					+ tipoDocumento.getTdocCodigo());
		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}

	@Test
	public void consultarTipoDocumento() {

		log.info("Inicio consultarTipoDocumentoTest");

		TiposDocumentos tipoDocumento;
		try {
			tipoDocumento = tipoDocumentoLogic.findById(tipoDocId);
			
			if (tipoDocumento != null) {
				log.info("Codigo del tipo de documento: " + tipoDocumento.getTdocCodigo());
				log.info("Nombre del tipo de documento: " + tipoDocumento.getTdocNombre());
			} else {
				log.info("El tipo de documento a consultar no existe");
			}

		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}

	@Ignore
	@Test
	public void modificarTipoDocumento() {

		log.info("Inicio modificarTipoDocumentoTest");

		// Consultar el tipoDocumento
		TiposDocumentos tipoDocumento;
		try {
			tipoDocumento = tipoDocumentoLogic.findById(tipoDocId);
			// Verificar existencia
			if (tipoDocumento != null) {
				String nom = tipoDocumento.getTdocNombre();
				tipoDocumento.setTdocNombre("Numero de licencia");

				tipoDocumentoLogic.update(tipoDocumento);
				log.info("Se modifico el nombre del tipo de documento " + nom + " por el de " + tipoDocumento.getTdocNombre());


			}else {
				log.info("El tipo de documento a modificar no existe");
			}

		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}
	
	@Ignore
	@Test
	public void eliminarTipoDocumento() {

		log.info("Inicio eliminarTipoDocumentoTest");

		TiposDocumentos tipoDocumento;
		try {
			tipoDocumento = tipoDocumentoLogic.findById(tipoDocId);
			
			if (tipoDocumento != null) {
				log.info("Se elimin� el tipo de documento" + tipoDocumento.getTdocNombre() + ", que tiene el c�digo: "
						+ tipoDocumento.getTdocCodigo());
				
				tipoDocumentoLogic.delete(tipoDocumento);
			}else {
				log.info("El tipo de documento a modificar no existe");
			}
			

			


		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}

}
