package co.edu.icesi.banco.vista;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputmask.InputMask;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.icesi.banco.business.IBusinessDelegate;

import co.edu.icesi.banco.modelo.Cuentas;
import co.edu.icesi.banco.modelo.Retiros;
import co.edu.icesi.banco.modelo.RetirosId;
import co.edu.icesi.banco.modelo.Usuarios;


@ManagedBean
@ViewScoped
public class RetirosView {
	private InputMask txtNumCuenta;
	private BigDecimal txtValor;

	private InputTextarea txtDescripcion;

	private SelectOneMenu sUsuarios;
	private List<SelectItem> lstUsuariosItem;

	private List<Retiros> lstRetiros;

	private CommandButton btnRealizarRetiros;
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

	public InputMask getTxtNumCuenta() {
		return txtNumCuenta;
	}

	public void setTxtNumCuenta(InputMask txtNumCuenta) {
		this.txtNumCuenta = txtNumCuenta;
	}

	public BigDecimal getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(BigDecimal txtValor) {
		this.txtValor = txtValor;
	}

	public InputTextarea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(InputTextarea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public SelectOneMenu getsUsuarios() {
		return sUsuarios;
	}

	public void setsUsuarios(SelectOneMenu sUsuarios) {
		this.sUsuarios = sUsuarios;
	}

	public List<SelectItem> getLstUsuariosItem() {
		if (lstUsuariosItem == null) {
			lstUsuariosItem = new ArrayList<SelectItem>();
			try {
				List<Usuarios> lstUusarios = businessDelegate.findAllUsuarios();
				for (Usuarios usu : lstUusarios) {
					lstUsuariosItem.add(new SelectItem(usu.getUsuCedula(), usu.getUsuLogin()));
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
		}

		return lstUsuariosItem;
	}

	public void setLstUsuariosItem(List<SelectItem> lstUsuariosItem) {
		this.lstUsuariosItem = lstUsuariosItem;
	}

	public List<Retiros> getLstRetiros() {
		if (lstRetiros== null) {
			try {
				lstRetiros = businessDelegate.findAllRetiros();
			} catch (Exception e) {

				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

			}
		}

		return lstRetiros;
	}

	public void setLstRetiros(List<Retiros> lstRetiros) {
		this.lstRetiros = lstRetiros;
	}

	public CommandButton getBtnRealizarRetiros() {
		return btnRealizarRetiros;
	}

	public void setBtnRealizarRetiros(CommandButton btnRealizarRetiros) {
		this.btnRealizarRetiros = btnRealizarRetiros;
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

	public void txtUsuarioListener() {
		try {
			if (sUsuarios == null || sUsuarios.getValue() == null
					|| sUsuarios.getValue().toString().equalsIgnoreCase("-1")) {
				panel1.setVisible(false);
				throw new Exception("Debe seleccionar el usuario");
			}

		

		} catch (Exception e) {
			panel1.setVisible(false);
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

		}
	}

	public void txtNumCuentaListener() {
		try {
			if (txtNumCuenta == null 
					|| txtNumCuenta.getValue() == null
					|| txtNumCuenta.getValue().toString().trim().equals("")
					) {
				panel1.setVisible(false);
				throw new Exception("Debe ingresar un número de cuenta válido"+"v");

			}
			
			Cuentas cuenta = businessDelegate.findByIdCuenta(txtNumCuenta.getValue().toString());
			if (cuenta == null) {

				throw new Exception("Debe ingresar un número de cuenta que exista");
			}
			if (cuenta.getCueActiva().trim().equalsIgnoreCase("N")) {

				throw new Exception("Debe ingresar una cuenta que esté activa");

			} else {

				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Puede comenzar a realizar un retiro al número de cuenta: "
										+ cuenta.getCueNumero() ,
								""));

				panel1.setVisible(true);

			}
			
		} catch (Exception e) {
			panel1.setVisible(false);
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			

		}
	}

	public String action_realizar_retiros() {

		try {

			if (txtDescripcion != null && txtDescripcion.getValue() != null
					&& !txtDescripcion.getValue().toString().trim().equals("")) {

				Cuentas cuenta = businessDelegate.findByIdCuenta(txtNumCuenta.getValue().toString());
				Usuarios usuario = businessDelegate.findByIdUsuario(Long.parseLong(sUsuarios.getValue().toString()));
				Retiros retiros = new Retiros();
				RetirosId retirosId = new RetirosId();
				retirosId.setRetCodigo(0);
				retirosId.setCueNumero(cuenta.getCueNumero());

				retiros.setId(retirosId);
				retiros.setRetHabilitado("S");
				retiros.setCuentas(cuenta);
				retiros.setUsuarios(usuario);
				retiros.setRetFecha(new Date());
				retiros.setRetValor(txtValor);
				retiros.setRetDescripcion(txtDescripcion.getValue().toString());
				businessDelegate.saveRetiros(retiros);

				lstRetiros=null;
				action_limpiar();
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Se realizo el retiro satisfactoriamente", ""));

			} else {

				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe llenar el campo de descripción", ""));

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage() , ""));

		}
		return "";
	}

	public String action_limpiar() {

		txtValor = new BigDecimal("0");
		txtNumCuenta.resetValue();
		txtDescripcion.resetValue();

		sUsuarios.resetValue();

		return "";
	}

}
