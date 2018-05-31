package co.edu.icesi.banco.vista;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.panel.Panel;

import co.edu.icesi.banco.business.IBusinessDelegate;
import co.edu.icesi.banco.modelo.Clientes;
import co.edu.icesi.banco.modelo.Consignaciones;
import co.edu.icesi.banco.modelo.Cuentas;
import co.edu.icesi.banco.modelo.Retiros;
import co.edu.icesi.banco.modelo.Transferencias;

@ManagedBean
@ViewScoped
public class ConsultasView {

	@ManagedProperty(value = "#{businessDelegate}")
	private IBusinessDelegate businessDelegate;

	private InputText txtIdentificaciones;
	private InputText txtIdentificaciones2;
	private InputText txtIdentificaciones3;
	
	private String txtNombre;
	private boolean clienteRetiro;
	private boolean clienteTransferencia;
	private boolean clienteConsignacion;
	
	private boolean cuentaRetiro;
	private boolean cuentaTransferencia;
	private boolean cuentaConsignacion;

	private CommandButton btnConsultarEnCliente;
	private CommandButton btnConsultarEnCuenta;

	private List<Cuentas> lstCuentas;

	private List<Consignaciones> lstConsignacionesClientes;
	private List<Retiros> lstRetirosClientes;
	private List<Transferencias> lstTransferenciasClientes;

	private List<Consignaciones> lstConsignacionesCuentas;
	private List<Retiros> lstRetirosCuentas;
	private List<Transferencias> lstTransferenciasCuentas;

	private Panel panel1;
	private Panel panel2;
	private Panel panel3;
	private Panel panel4;
	private Panel panel5;
	private Panel panel6;

	private Date dateInicial;
	private Date dateFinal;
	
	private Date dateInicialCuenta;
	private Date dateFinalCuenta;

	public IBusinessDelegate getBusinessDelegate() {
		return businessDelegate;
	}

	public void setBusinessDelegate(IBusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	}

	public InputText getTxtIdentificaciones() {
		return txtIdentificaciones;
	}

	public void setTxtIdentificaciones(InputText txtIdentificaciones) {
		this.txtIdentificaciones = txtIdentificaciones;
	}

	public String getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(String txtNombre) {
		this.txtNombre = txtNombre;
	}

	public List<Cuentas> getLstCuentas() {
		if (lstCuentas == null) {
			try {
				try {
					Long a = Long.parseLong(txtIdentificaciones.getValue().toString());

				} catch (Exception e) {
					throw new Exception("Id del cliente inválido");
				}

				lstCuentas = businessDelegate
						.findAllCentasDeUnCliente(Long.parseLong(txtIdentificaciones.getValue().toString()));
			} catch (Exception e) {

				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

			}
		}

		return lstCuentas;
	}

	public void setLstCuentas(List<Cuentas> lstCuentas) {
		this.lstCuentas = lstCuentas;
	}

	public void txtIdentificacionListener() {
		try {
			if (txtIdentificaciones == null || txtIdentificaciones.getValue() == null
					|| txtIdentificaciones.getValue().toString().trim().equals("")) {
				throw new Exception("Debe ingresar un número de identificación");
			}
			Long id = Long.parseLong(txtIdentificaciones.getValue().toString());
			Clientes cliente = businessDelegate.findByIdClientes(id);
			if (cliente == null) {

				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "EL cliente no existe", ""));

			} else if (cliente.getCliHabilitado().trim().equalsIgnoreCase("N")) {

				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "EL cliente existe pero está inactivado", ""));

			} else {
				lstCuentas = null;
			}

		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

		}
	}

	public void txtIdentificacionListener2() {
		try {
			if (txtIdentificaciones2 == null || txtIdentificaciones2.getValue() == null
					|| txtIdentificaciones2.getValue().toString().trim().equals("")) {
				throw new Exception("Debe ingresar un número de identificación");
			}
			Long id = Long.parseLong(txtIdentificaciones2.getValue().toString());
			Clientes cliente = businessDelegate.findByIdClientes(id);
			if (cliente == null) {

				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "EL cliente no existe", ""));

			} else if (cliente.getCliHabilitado().trim().equalsIgnoreCase("N")) {

				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "EL cliente existe pero está inactivado", ""));

			}

		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

		}
	}
	
	
	public void txtIdentificacionListener3() {
		try {
			if (txtIdentificaciones3 == null || txtIdentificaciones3.getValue() == null
					|| txtIdentificaciones3.getValue().toString().trim().equals("")) {
				throw new Exception("Debe ingresar un número de identificación");
			}
			Long id = Long.parseLong(txtIdentificaciones3.getValue().toString());
			Clientes cliente = businessDelegate.findByIdClientes(id);
			if (cliente == null) {

				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "EL cliente no existe", ""));

			} else if (cliente.getCliHabilitado().trim().equalsIgnoreCase("N")) {

				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "EL cliente existe pero está inactivado", ""));

			}

		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

		}
	}

	public Panel getPanel1() {
		return panel1;
	}

	public void setPanel1(Panel panel1) {
		this.panel1 = panel1;
	}

	public Date getDateInicial() {
		return dateInicial;
	}

	public void setDateInicial(Date dateInicial) {
		this.dateInicial = dateInicial;
	}

	public Date getDateFinal() {
		return dateFinal;
	}

	public void setDateFinal(Date dateFinal) {
		this.dateFinal = dateFinal;
	}

	public InputText getTxtIdentificaciones2() {
		return txtIdentificaciones2;
	}

	public void setTxtIdentificaciones2(InputText txtIdentificaciones2) {
		this.txtIdentificaciones2 = txtIdentificaciones2;
	}

	public List<Consignaciones> getLstConsignacionesClientes() {
		if (lstConsignacionesClientes == null) {
			try {

				lstConsignacionesClientes = businessDelegate.findConsignacionesPorClienteYFecha(
						Long.parseLong(txtIdentificaciones2.getValue().toString()), dateInicial, dateFinal);
			} catch (Exception e) {

				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

			}
		}

		return lstConsignacionesClientes;
	}

	public void setLstConsignacionesClientes(List<Consignaciones> lstConsignacionesClientes) {
		this.lstConsignacionesClientes = lstConsignacionesClientes;
	}

	public CommandButton getBtnConsultarEnCliente() {
		return btnConsultarEnCliente;
	}

	public void setBtnConsultarEnCliente(CommandButton btnConsultarEnCliente) {
		this.btnConsultarEnCliente = btnConsultarEnCliente;
	}

	public void consultarEnCliente() {
		try {
			if (clienteConsignacion) {
				panel1.setVisible(true);
				lstConsignacionesClientes = null;
			}
			if (clienteRetiro) {
				panel2.setVisible(true);
				lstRetirosClientes = null;
			}
			if (clienteTransferencia) {
				panel3.setVisible(true);
				lstTransferenciasClientes = null;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

		}

	}
	
	public void consultarEnCuenta() {
		try {
			if (cuentaConsignacion) {
				panel4.setVisible(true);
				lstConsignacionesCuentas= null;
			}
			if (cuentaRetiro) {
				panel5.setVisible(true);
				lstRetirosCuentas = null;
			}
			if (cuentaTransferencia) {
				panel6.setVisible(true);
				lstTransferenciasCuentas = null;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

		}

	}

	public boolean isClienteRetiro() {
		return clienteRetiro;
	}

	public void setClienteRetiro(boolean clienteRetiro) {
		this.clienteRetiro = clienteRetiro;
	}

	public boolean isClienteTransferencia() {
		return clienteTransferencia;
	}

	public void setClienteTransferencia(boolean clienteTransferencia) {
		this.clienteTransferencia = clienteTransferencia;
	}

	public boolean isClienteConsignacion() {
		return clienteConsignacion;
	}

	public void setClienteConsignacion(boolean clienteConsignacion) {
		this.clienteConsignacion = clienteConsignacion;
	}

	public Panel getPanel2() {
		return panel2;
	}

	public void setPanel2(Panel panel2) {
		this.panel2 = panel2;
	}

	public Panel getPanel3() {
		return panel3;
	}

	public void setPanel3(Panel panel3) {
		this.panel3 = panel3;
	}

	public Panel getPanel4() {
		return panel4;
	}

	public void setPanel4(Panel panel4) {
		this.panel4 = panel4;
	}

	public Panel getPanel5() {
		return panel5;
	}

	public void setPanel5(Panel panel5) {
		this.panel5 = panel5;
	}

	public Panel getPanel6() {
		return panel6;
	}

	public void setPanel6(Panel panel6) {
		this.panel6 = panel6;
	}

	public List<Retiros> getLstRetirosClientes() {
		return lstRetirosClientes;
	}

	public void setLstRetirosClientes(List<Retiros> lstRetirosClientes) {
		this.lstRetirosClientes = lstRetirosClientes;
	}

	public List<Transferencias> getLstTransferenciasClientes() {
		return lstTransferenciasClientes;
	}

	public void setLstTransferenciasClientes(List<Transferencias> lstTransferenciasClientes) {
		this.lstTransferenciasClientes = lstTransferenciasClientes;
	}

	public List<Consignaciones> getLstConsignacionesCuentas() {
		return lstConsignacionesCuentas;
	}

	public void setLstConsignacionesCuentas(List<Consignaciones> lstConsignacionesCuentas) {
		this.lstConsignacionesCuentas = lstConsignacionesCuentas;
	}

	public List<Retiros> getLstRetirosCuentas() {
		return lstRetirosCuentas;
	}

	public void setLstRetirosCuentas(List<Retiros> lstRetirosCuentas) {
		this.lstRetirosCuentas = lstRetirosCuentas;
	}

	public List<Transferencias> getLstTransferenciasCuentas() {
		return lstTransferenciasCuentas;
	}

	public void setLstTransferenciasCuentas(List<Transferencias> lstTransferenciasCuentas) {
		this.lstTransferenciasCuentas = lstTransferenciasCuentas;
	}

	public boolean isCuentaRetiro() {
		return cuentaRetiro;
	}

	public void setCuentaRetiro(boolean cuentaRetiro) {
		this.cuentaRetiro = cuentaRetiro;
	}

	public boolean isCuentaTransferencia() {
		return cuentaTransferencia;
	}

	public void setCuentaTransferencia(boolean cuentaTransferencia) {
		this.cuentaTransferencia = cuentaTransferencia;
	}

	public boolean isCuentaConsignacion() {
		return cuentaConsignacion;
	}

	public void setCuentaConsignacion(boolean cuentaConsignacion) {
		this.cuentaConsignacion = cuentaConsignacion;
	}

	public InputText getTxtIdentificaciones3() {
		return txtIdentificaciones3;
	}

	public void setTxtIdentificaciones3(InputText txtIdentificaciones3) {
		this.txtIdentificaciones3 = txtIdentificaciones3;
	}

	public CommandButton getBtnConsultarEnCuenta() {
		return btnConsultarEnCuenta;
	}

	public void setBtnConsultarEnCuenta(CommandButton btnConsultarEnCuenta) {
		this.btnConsultarEnCuenta = btnConsultarEnCuenta;
	}

	public Date getDateInicialCuenta() {
		return dateInicialCuenta;
	}

	public void setDateInicialCuenta(Date dateInicialCuenta) {
		this.dateInicialCuenta = dateInicialCuenta;
	}

	public Date getDateFinalCuenta() {
		return dateFinalCuenta;
	}

	public void setDateFinalCuenta(Date dateFinalCuenta) {
		this.dateFinalCuenta = dateFinalCuenta;
	}

}
