package co.edu.icesi.banco.vista;

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
	
	private Panel panel1;

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
				try {
					Long a=Long.parseLong(txtIdentificaciones.getValue().toString());
					
				}catch (Exception e) {
					throw new Exception("Id del cliente inválido");
				}
				
				lstCuentas = businessDelegate.findAllCentasDeUnCliente(Long.parseLong(txtIdentificaciones.getValue().toString()));
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


			}else if(cliente.getCliHabilitado().trim().equalsIgnoreCase("N")){
				
			
				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "EL cliente existe pero está inactivado", ""));

				
			}else {
				lstCuentas=null;
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

	

}
