package co.edu.icesi.banco.vista;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.button.Button;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;

import co.edu.icesi.banco.business.IBusinessDelegate;
import co.edu.icesi.banco.modelo.Usuarios;

@ManagedBean
@ViewScoped
public class LoginView {
	
	@ManagedProperty(value = "#{businessDelegate}")
	private IBusinessDelegate businessDelegate;
	
	private InputText usuario;
	private Password contrasena;
	
	
	public String clickIngreso() {
		
		try {
			if(usuario==null||usuario.getValue().toString().equals("")) {
				throw new Exception("Ingrese un usuario válido");
			}
			
			if(contrasena==null||contrasena.getValue().toString().trim().equals("")) {
				throw new Exception("Ingrese una contraseña válida");
			}
			boolean encontro=false;
			
			List<Usuarios> usu= businessDelegate.findAllUsuarios();
			
			for (int i = 0; i < usu.size()&&!encontro; i++) {
				if(usu.get(i).getUsuLogin().equals(usuario.getValue().toString())) {
					if(usu.get(i).getUsuClave().equals(contrasena.getValue().toString())) {
						FacesContext.getCurrentInstance().addMessage("",
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Puede entrar", ""));
						
						return "/VistaCliente.xhtml?faces-redirect=true\"";
					}else {
						throw new Exception("La contraseña no es correcta");
					}
				}
			}
			
			throw new Exception("El usuario ingresado no existe");
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return"";
		
		
	}


	public IBusinessDelegate getBusinessDelegate() {
		return businessDelegate;
	}


	public void setBusinessDelegate(IBusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	}


	public InputText getUsuario() {
		return usuario;
	}


	public void setUsuario(InputText usuario) {
		this.usuario = usuario;
	}


	public Password getContrasena() {
		return contrasena;
	}


	public void setContrasena(Password contrasena) {
		this.contrasena = contrasena;
	}

}
