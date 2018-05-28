package co.edu.icesi.banco.vista;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputmask.InputMask;
import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.panel.Panel;

import org.primefaces.component.password.Password;


import co.edu.icesi.banco.business.IBusinessDelegate;
import co.edu.icesi.banco.modelo.Clientes;
import co.edu.icesi.banco.modelo.Cuentas;


@ManagedBean
@ViewScoped
public class CuentaView {
	@ManagedProperty(value = "#{businessDelegate}")
	private IBusinessDelegate businessDelegate;

	private InputMask txtCueNumero;
	private InputText txtIdCliente;
	private InputNumber txtCuentaSaldo;
	private Password txtCueClave;

	private List<Cuentas> lstCuentas;
	private List<Cuentas> lstCuentasHistoricas;

	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnEliminar;
	private CommandButton btnLimpiar;
	private CommandButton btnActivar;

	private Panel panel;

	public IBusinessDelegate getBusinessDelegate() {
		return businessDelegate;
	}

	public void setBusinessDelegate(IBusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	}

	public InputNumber getTxtCuentaSaldo() {
		return txtCuentaSaldo;
	}

	public void setTxtCuentaSaldo(InputNumber txtCuentaSaldo) {
		this.txtCuentaSaldo = txtCuentaSaldo;
	}

	public Password getTxtCueClave() {
		return txtCueClave;
	}

	public void setTxtCueClave(Password txtCueClave) {
		this.txtCueClave = txtCueClave;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public CommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public CommandButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(CommandButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public CommandButton getBtnActivar() {
		return btnActivar;
	}

	public void setBtnActivar(CommandButton btnActivar) {
		this.btnActivar = btnActivar;
	}

	public InputMask getTxtCueNumero() {
		return txtCueNumero;
	}

	public void setTxtCueNumero(InputMask txtCueNumero) {
		this.txtCueNumero = txtCueNumero;
	}

	public InputText getTxtIdCliente() {
		return txtIdCliente;
	}

	public void setTxtIdCliente(InputText txtIdCliente) {
		this.txtIdCliente = txtIdCliente;
	}

	public String action_crear() {

		try {

			if (txtIdCliente == null || txtIdCliente.getValue() != null
					|| txtIdCliente.getValue().toString().trim().equals("")) {
				Clientes cliente = businessDelegate
						.findByIdClientes(Long.parseLong(txtIdCliente.getValue().toString()));
				Cuentas cuenta = new Cuentas();
				cuenta.setCueNumero(txtCueNumero.getValue().toString());
				cuenta.setCueClave(txtCueClave.getValue().toString());
				cuenta.setClientes(cliente);
				cuenta.setCueActiva("S");
				cuenta.setCueSaldo(new BigDecimal(txtCuentaSaldo.getValue().toString()));

				businessDelegate.saveCuenta(cuenta);
				lstCuentas = null;
				lstCuentasHistoricas = null;
				action_limpiar();
				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Se creó la cuenta satisfactoriamente", ""));
			} else {
				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "El id del cliente asociado es inválido", ""));

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage() + "vino de la lógica", ""));

		}
		return "";
	}

	public String action_limpiar() {

		txtCueClave.resetValue();
		txtCuentaSaldo.resetValue();
		txtCueNumero.resetValue();

		return "";
	}

	public String action_inactivar() {
		try {

			Cuentas cuenta = businessDelegate.findByIdCuenta(txtCueNumero.getValue().toString());
			cuenta.setCueActiva("N");
			// Se elimina la cuenta
			businessDelegate.updateCuenta(cuenta);
			lstCuentas = null;
			lstCuentasHistoricas = null;
			action_limpiar();
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Se inactivó la cuenta satisfactoriamente", ""));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

		}
		return "";
	}
	
	public String action_activar() {
		try {

			Cuentas cuenta = businessDelegate.findByIdCuenta(txtCueNumero.getValue().toString());
			cuenta.setCueActiva("S");
			// Se elimina la cuenta
			businessDelegate.updateCuenta(cuenta);
			lstCuentas = null;
			lstCuentasHistoricas = null;
			action_limpiar();
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Se activó la cuenta satisfactoriamente", ""));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

		}
		return "";
	}

	public void txtClienteIdentificacionListener() {
		try {
			if (txtIdCliente == null || txtIdCliente.getValue() == null
					|| txtIdCliente.getValue().toString().trim().equals("")) {
				throw new Exception("Debe ingresar un número de identificación");
			}
			Long id = Long.parseLong(txtIdCliente.getValue().toString());
			Clientes cliente = businessDelegate.findByIdClientes(id);
			if (cliente == null) {
				panel.setVisible(false);
				throw new Exception("Debe ingresar un número de identificación de un cliente que exista");
			}
			if (cliente.getCliHabilitado().trim().equalsIgnoreCase("N")) {

				panel.setVisible(false);
				throw new Exception("Debe ingresar un número de identificación de un cliente que esté activo");

			} else {

				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Puede comenzar a agregar cuentas al cliente "+cliente.getCliNombre(), ""));

				panel.setVisible(true);

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

		}
	}

	public List<Cuentas> getLstCuentas() {
		return lstCuentas;
	}

	public void setLstCuentas(List<Cuentas> lstCuentas) {
		this.lstCuentas = lstCuentas;
	}

	public List<Cuentas> getLstCuentasHistoricas() {
		return lstCuentasHistoricas;
	}

	public void setLstCuentasHistoricas(List<Cuentas> lstCuentasHistoricas) {
		this.lstCuentasHistoricas = lstCuentasHistoricas;
	}

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}
	public String action_modificar() {

		try {

			if (txtIdCliente == null || txtIdCliente.getValue() != null
					|| txtIdCliente.getValue().toString().trim().equals("")) {
				Clientes cliente = businessDelegate
						.findByIdClientes(Long.parseLong(txtIdCliente.getValue().toString()));
				Cuentas cuenta = businessDelegate.findByIdCuenta(txtCueNumero.getValue().toString());
				
				cuenta.setCueClave(txtCueClave.getValue().toString());
				cuenta.setClientes(cliente);
				cuenta.setCueActiva("S");
				cuenta.setCueSaldo(new BigDecimal(txtCuentaSaldo.getValue().toString()));

				businessDelegate.updateCuenta(cuenta);
				lstCuentas = null;
				lstCuentasHistoricas = null;
				action_limpiar();
				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó la cuenta satisfactoriamente", ""));
			} else {
				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "El id del cliente asociado es inválido", ""));

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage() + "vino de la lógica", ""));

		}
		return "";
	}

}
