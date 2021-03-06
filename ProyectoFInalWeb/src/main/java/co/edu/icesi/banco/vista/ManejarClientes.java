package co.edu.icesi.banco.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputmask.InputMask;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.icesi.banco.business.IBusinessDelegate;
import co.edu.icesi.banco.modelo.Clientes;
import co.edu.icesi.banco.modelo.TiposDocumentos;

@ManagedBean
@ViewScoped
public class ManejarClientes {

	@ManagedProperty(value = "#{businessDelegate}")
	private IBusinessDelegate businessDelegate;

	private InputText txtIdentificaciones;
	private InputText txtNombre;
	private InputText txtDireccion;
	private InputMask txtTelefono;
	private InputText txtMail;
	private List<Clientes> filteredClientes;

	private SelectOneMenu somTipoDocumento;
	private List<SelectItem> lstTiposDocumentosItem;

	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnEliminar;
	private CommandButton btnLimpiar;
	private CommandButton btnActivar;

	private List<Clientes> lstClientes;
	private List<Clientes> lstClientesHistoricos;

	public String action_crear() {

		try {
			Clientes cliente = new Clientes();

			cliente.setCliMail(txtMail.getValue().toString());
			cliente.setCliNombre(txtNombre.getValue().toString());
			cliente.setCliTelefono(txtTelefono.getValue().toString());
			cliente.setCliDireccion(txtDireccion.getValue().toString());
			cliente.setCliHabilitado("S");

			Long id = null;
			if (txtIdentificaciones == null || txtIdentificaciones.getValue() != null
					|| txtIdentificaciones.getValue().toString().trim().equals("")) {
				id = Long.parseLong(txtIdentificaciones.getValue().toString());
			}
			cliente.setCliId(id);

			Long idTipoDocu = Long.parseLong(somTipoDocumento.getValue().toString());
			TiposDocumentos tipoDoc = businessDelegate.findByIdTiposDocumentos(idTipoDocu);
			cliente.setTiposDocumentos(tipoDoc);
			// Se guarda el cliente una vez se llenen los datos
			businessDelegate.saveClientes(cliente);
			lstClientes = null;
			lstClientesHistoricos = null;
			action_limpiar();
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Se creó el cliente satisfactoriamente", ""));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage() + "vino de la lógica", ""));

		}
		return "";
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
				btnCrear.setDisabled(false);
				btnModificar.setDisabled(true);
				btnEliminar.setDisabled(true);
				btnActivar.setDisabled(true);
				action_limpiar();

			} if(cliente.getCliHabilitado().trim().equalsIgnoreCase("N")){
				
				btnCrear.setDisabled(true);
				btnModificar.setDisabled(true);
				btnEliminar.setDisabled(true);
				btnActivar.setDisabled(false);
				txtNombre.setValue(cliente.getCliNombre());
				txtTelefono.setValue(cliente.getCliTelefono());
				txtDireccion.setValue(cliente.getCliDireccion());
				txtMail.setValue(cliente.getCliMail());
				somTipoDocumento.setValue(cliente.getTiposDocumentos().getTdocCodigo());
				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "EL cliente existe pero está inactivado", ""));

				
			}
				else {
			
				
				btnCrear.setDisabled(true);
				btnModificar.setDisabled(false);
				btnEliminar.setDisabled(false);
				btnActivar.setDisabled(true);

				txtNombre.setValue(cliente.getCliNombre());
				txtTelefono.setValue(cliente.getCliTelefono());
				txtDireccion.setValue(cliente.getCliDireccion());
				txtMail.setValue(cliente.getCliMail());
				somTipoDocumento.setValue(cliente.getTiposDocumentos().getTdocCodigo());
				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "EL cliente existe y está activo", ""));

				
			}

		} catch (Exception e) {
			
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));


		}
	}

	public String action_modificar() {

		try {
			Clientes cliente = new Clientes();

			cliente.setCliMail(txtMail.getValue().toString());
			cliente.setCliNombre(txtNombre.getValue().toString());
			cliente.setCliTelefono(txtTelefono.getValue().toString());
			cliente.setCliDireccion(txtDireccion.getValue().toString());
			cliente.setCliHabilitado("S");

			Long id = null;
			if (txtIdentificaciones == null || txtIdentificaciones.getValue() != null
					|| txtIdentificaciones.getValue().toString().trim().equals("")) {
				id = Long.parseLong(txtIdentificaciones.getValue().toString());
			}
			cliente.setCliId(id);

			Long idTipoDocu = Long.parseLong(somTipoDocumento.getValue().toString());
			TiposDocumentos tipoDoc = businessDelegate.findByIdTiposDocumentos(idTipoDocu);
			cliente.setTiposDocumentos(tipoDoc);
			// Se guarda el cliente una vez se llenen los datos
			businessDelegate.updateClientes(cliente);
			lstClientes = null;
			lstClientesHistoricos = null;
			action_limpiar();
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó el cliente satisfactoriamente", ""));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

		}
		return "";
	}

	public String action_borrar() {
		try {
			Clientes cliente = new Clientes();

			Long id = null;
			if (txtIdentificaciones == null || txtIdentificaciones.getValue() != null
					|| txtIdentificaciones.getValue().toString().trim().equals("")) {
				id = Long.parseLong(txtIdentificaciones.getValue().toString());
			}
			cliente.setCliId(id);

			Long idTipoDocu = Long.parseLong(somTipoDocumento.getValue().toString());
			TiposDocumentos tipoDoc = businessDelegate.findByIdTiposDocumentos(idTipoDocu);
			cliente.setTiposDocumentos(tipoDoc);
			// Se elimina el cliente
			businessDelegate.deleteClientes(cliente);
			lstClientes = null;
			lstClientesHistoricos = null;
			action_limpiar();
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Se eliminó el cliente satisfactoriamente", ""));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

		}
		return "";
	}

	public String action_inactivar() {
		try {
			Long id = Long.parseLong(txtIdentificaciones.getValue().toString());

			Clientes cliente = businessDelegate.findByIdClientes(id);
			cliente.setCliHabilitado("N");
			// Se elimina el cliente
			businessDelegate.updateClientes(cliente);
			lstClientes = null;
			lstClientesHistoricos = null;
			action_limpiar();
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Se inactivó el cliente satisfactoriamente", ""));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

		}
		return "";
	}

	public String action_limpiar() {

		txtIdentificaciones.resetValue();
		txtNombre.resetValue();
		txtDireccion.resetValue();
		txtTelefono.resetValue();
		txtMail.resetValue();
		somTipoDocumento.resetValue();
		btnCrear.setDisabled(false);
		btnModificar.setDisabled(true);
		btnEliminar.setDisabled(true);

		return "";
	}

	public InputText getTxtIdentificaciones() {
		return txtIdentificaciones;
	}

	public void setTxtIdentificaciones(InputText txtIdentificaciones) {
		this.txtIdentificaciones = txtIdentificaciones;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputText getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(InputText txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public InputMask getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(InputMask txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public InputText getTxtMail() {
		return txtMail;
	}

	public void setTxtMail(InputText txtMail) {
		this.txtMail = txtMail;
	}

	public SelectOneMenu getSomTipoDocumento() {
		return somTipoDocumento;
	}

	public void setSomTipoDocumento(SelectOneMenu somTipoDocumento) {
		this.somTipoDocumento = somTipoDocumento;
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

	public List<Clientes> getLstClientes() {

		if (lstClientes == null) {
			try {
				lstClientes = businessDelegate.findAllActiveClientes();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));


			}
		}

		return lstClientes;
	}

	public void setLstClientes(List<Clientes> lstClientes) {
		this.lstClientes = lstClientes;
	}

	public IBusinessDelegate getBusinessDelegate() {
		return businessDelegate;
	}

	public void setBusinessDelegate(IBusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	}

	public List<SelectItem> getLstTiposDocumentosItem() {

		if (lstTiposDocumentosItem == null) {
			lstTiposDocumentosItem = new ArrayList<SelectItem>();
			try {
				List<TiposDocumentos> lstTiposDocumentos = businessDelegate.findAllTiposDocumentos();
				for (TiposDocumentos tipoDocu : lstTiposDocumentos) {
					lstTiposDocumentosItem.add(new SelectItem(tipoDocu.getTdocCodigo(), tipoDocu.getTdocNombre()));
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
		}

		return lstTiposDocumentosItem;
	}

	public void setLstTiposDocumentosItem(List<SelectItem> lstTiposDocumentosItem) {
		this.lstTiposDocumentosItem = lstTiposDocumentosItem;
	}

	public List<Clientes> getFilteredClientes() {
		return filteredClientes;
	}

	public void setFilteredClientes(List<Clientes> filteredClientes) {
		this.filteredClientes = filteredClientes;
	}

	public String action_activar() {

		try {
			Long id = Long.parseLong(txtIdentificaciones.getValue().toString());

			Clientes cliente = businessDelegate.findByIdClientes(id);
			cliente.setCliHabilitado("S");
			businessDelegate.updateClientes(cliente);
			lstClientes = null;
			lstClientesHistoricos = null;
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se activó el cliente correctamente", ""));

			action_limpiar();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}

		return "";
	}

	public CommandButton getBtnActivar() {
		return btnActivar;
	}

	public void setBtnActivar(CommandButton btnActivar) {
		this.btnActivar = btnActivar;
	}

	public List<Clientes> getLstClientesHistoricos() {
		if (lstClientesHistoricos == null) {
			try {
				lstClientesHistoricos = businessDelegate.findAllClientes();
			} catch (Exception e) {

				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

			}
		}

		return lstClientesHistoricos;
	}

	public void setLstClientesHistoricos(List<Clientes> lstClientesHistoricos) {
		this.lstClientesHistoricos = lstClientesHistoricos;
	}
}
