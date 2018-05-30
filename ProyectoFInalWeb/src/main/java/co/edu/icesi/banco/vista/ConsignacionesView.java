package co.edu.icesi.banco.vista;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.icesi.banco.business.IBusinessDelegate;
import co.edu.icesi.banco.modelo.Clientes;
import co.edu.icesi.banco.modelo.Consignaciones;


@ManagedBean
@ViewScoped
public class ConsignacionesView {
	
	private InputText txtIdCliente;
	private BigDecimal txtValor;	
	private String txtDescripcion;

	private SelectOneMenu sUsuarios;
	private List<SelectItem> lstUsuariosItem;
	
	private SelectOneMenu sCuentas;
	private List<SelectItem> lstCuentasItem;
	
	private List<Consignaciones> lstConsignaciones;
	private List<Consignaciones> lstConsignacionesHistoricas;

	private CommandButton btnRealizarConsignacion;
	private Panel panel1;
	private Panel panel2;
	
	
	@ManagedProperty(value = "#{businessDelegate}")
	private IBusinessDelegate businessDelegate;


	public IBusinessDelegate getBusinessDelegate() {
		return businessDelegate;
	}
	public void setBusinessDelegate(IBusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	}
	public InputText getTxtIdCliente() {
		return txtIdCliente;
	}
	public void setTxtIdCliente(InputText txtIdCliente) {
		this.txtIdCliente = txtIdCliente;
	}
	public BigDecimal getTxtValor() {
		return txtValor;
	}
	public void setTxtValor(BigDecimal txtValor) {
		this.txtValor = txtValor;
	}

	public String getTxtDescripcion() {
		return txtDescripcion;
	}
	public void setTxtDescripcion(String txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}
	public SelectOneMenu getsUsuarios() {
		return sUsuarios;
	}
	public void setsUsuarios(SelectOneMenu sUsuarios) {
		this.sUsuarios = sUsuarios;
	}
	public List<SelectItem> getLstUsuariosItem() {
		return lstUsuariosItem;
	}
	public void setLstUsuariosItem(List<SelectItem> lstUsuariosItem) {
		this.lstUsuariosItem = lstUsuariosItem;
	}
	public SelectOneMenu getsCuentas() {
		return sCuentas;
	}
	public void setsCuentas(SelectOneMenu sCuentas) {
		this.sCuentas = sCuentas;
	}
	public List<SelectItem> getLstCuentasItem() {
		return lstCuentasItem;
	}
	public void setLstCuentasItem(List<SelectItem> lstCuentasItem) {
		this.lstCuentasItem = lstCuentasItem;
	}
	public List<Consignaciones> getLstConsignaciones() {
		return lstConsignaciones;
	}
	public void setLstConsignaciones(List<Consignaciones> lstConsignaciones) {
		this.lstConsignaciones = lstConsignaciones;
	}
	public List<Consignaciones> getLstConsignacionesHistoricas() {
		return lstConsignacionesHistoricas;
	}
	public void setLstConsignacionesHistoricas(List<Consignaciones> lstConsignacionesHistoricas) {
		this.lstConsignacionesHistoricas = lstConsignacionesHistoricas;
	}
	public CommandButton getBtnRealizarConsignacion() {
		return btnRealizarConsignacion;
	}
	public void setBtnRealizarConsignacion(CommandButton btnRealizarConsignacion) {
		this.btnRealizarConsignacion = btnRealizarConsignacion;
	}
	public Panel getPanel1() {
		return panel1;
	}
	public void setPanel1(Panel panel1) {
		this.panel1 = panel1;
	}
	public Panel getPanel2() {
		return panel2;
	}
	public void setPanel2(Panel panel2) {
		this.panel2 = panel2;
	}
	
	public void txtClienteIdentificacionListener() {
		try {
			if (txtIdCliente == null || txtIdCliente.getValue() == null
					|| txtIdCliente.getValue().toString().trim().equals("")) {
				panel1.setVisible(false);
				throw new Exception("Debe ingresar un número de identificación del cliente");
				
			}else if(sUsuarios==null||sUsuarios.getValue()==null||sUsuarios.getValue().toString().equalsIgnoreCase("-1")) {
				panel1.setVisible(false);
				throw new Exception("Debe seleccionar el usuario");
			}
			Long id = Long.parseLong(txtIdCliente.getValue().toString());
			Clientes cliente = businessDelegate.findByIdClientes(id);
			if (cliente == null) {
				panel1.setVisible(false);
				throw new Exception("Debe ingresar un número de identificación de un cliente que exista");
			}
			if (cliente.getCliHabilitado().trim().equalsIgnoreCase("N")) {

				panel1.setVisible(false);
				throw new Exception("Debe ingresar un número de identificación de un cliente que esté activo");

			} else {

				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Puede comenzar a agregar cuentas al cliente "+cliente.getCliNombre(), ""));

				panel1.setVisible(true);

			}

		} catch (Exception e) {
			panel1.setVisible(false);
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

		}
	}
	

	
	
	

}
