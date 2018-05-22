package co.edu.icesi.banco.logic.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import co.edu.icesi.banco.logic.IClienteLogic;
import co.edu.icesi.banco.logic.ICuentasLogic;

import co.edu.icesi.banco.modelo.Cuentas;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)

public class CuentasLogicTest {

	public static final Long cliId = 333221L;
	public static final String cuenId = "000-000-000";

	
	private static final Logger log = LoggerFactory.getLogger(CuentasLogicTest.class);
	

	
	
	@Autowired
	private IClienteLogic clientesLogic;


	
	@Autowired
	private ICuentasLogic cuentasLogic;

	@Ignore
	@Test
	public void crearCuenta() {
		
		log.info("inicio crearCuentasTest");		

				
		//Consultar la cuenta
		Cuentas cuenta;
		try {
			cuenta = cuentasLogic.findById(cuenId);
			assertNull("La cuenta ya existe: " , cuenta);		
			//Instanciar la entidad de cuenta
			cuenta = new Cuentas();
			cuenta.setCueNumero(cuenId);
			cuenta.setCueSaldo(new BigDecimal(100000));
			cuenta.setCueActiva("S");
			cuenta.setCueClave("1234");
			//Buscar cliente de la cuenta
			cuenta.setClientes(clientesLogic.findById(cliId));
			cuentasLogic.save(cuenta);
			
			log.info("Se cre� la cuenta n�mero: "+ cuenta.getCueNumero());
		} catch (Exception e) {

			log.error(e.getMessage());
		}
		

		
	}
	

	@Test
	public void consultarCuenta() {
		
		log.info("Inicio consultarCuentasTest");		

				
		//Consultar la cuenta
		Cuentas cuenta;
		try {
			cuenta = cuentasLogic.findById(cuenId);
			
			//Verificar que exista
			assertNotNull("La cuenta no existe: " , cuenta);
			log.info("No. de cuenta: "+cuenta.getCueNumero());
//TODO:			log.info("Nombre de cliente: "+cuenta.getClientes().getCliNombre());	
			log.info("Saldo de la cuenta: "+cuenta.getCueSaldo());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		
		
		
		

		
	}
	
	
	@Test
	public void modificarCuenta() {
		
		log.info("Inicio modificarCuentasTest");
		
		
		//COnsultar la cuenta
		Cuentas cuenta;
		try {
			cuenta = cuentasLogic.findById(cuenId);
			//Verificar que exista la cuenta
			assertNotNull("La cuenta no existe: " , cuenta);		
			cuenta.setCueClave("856412");		
		
			cuentasLogic.update(cuenta);
			log.info("Se modific� la clave de la cuenta n�mero: "+ cuenta.getCueNumero());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	
			
		

		
	}
	
	@Ignore
	@Test
	public void eliminarCuenta() {
		
		log.info("Inicio eliminarCuentasTest");

		
		//Consultar cuenta
		Cuentas cuenta;
		try {
			cuenta = cuentasLogic.findById(cuenId);
			//Verificar existencia
			assertNotNull("La cuenta no existe: " , cuenta);		
		
			
			cuentasLogic.delete(cuenta);
			
			log.info("Se elimin� la cuenta n�mero: "+ cuenta.getCueNumero());
		} catch (Exception e) {
			log.error(e.getMessage());
		}		
		
		
			
		

		
	}

}
