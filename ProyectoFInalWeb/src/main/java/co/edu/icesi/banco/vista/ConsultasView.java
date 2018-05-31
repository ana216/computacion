package co.edu.icesi.banco.vista;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import co.edu.icesi.banco.business.IBusinessDelegate;
import co.edu.icesi.banco.modelo.Cuentas;

@ManagedBean
@ViewScoped
public class ConsultasView {
	
	@ManagedProperty(value = "#{businessDelegate}")
	private IBusinessDelegate businessDelegate;
	
	private InputText txtIdentificaciones;
	private String txtNombre;
	
	private CommandButton btnConsultar;
	
	private List<Cuentas> lstCuentas;

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

	public CommandButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(CommandButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public List<Cuentas> getLstCuentas() {
		if (lstCuentas == null) {
			try {
				lstCuentas = businessDelegate.findAllActiveCuentas();
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

	

}
