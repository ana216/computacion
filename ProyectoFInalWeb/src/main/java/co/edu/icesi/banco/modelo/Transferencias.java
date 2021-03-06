package co.edu.icesi.banco.modelo;
// Generated 22/05/2018 12:10:38 AM by Hibernate Tools 4.3.5.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Transferencias generated by hbm2java
 */
@Entity
@Table(name = "transferencias", schema = "public")
public class Transferencias implements java.io.Serializable {

	private long transCodigo;
	private Cuentas cuentasByCueNumOrigen;
	private Cuentas cuentasByCueNumDestino;
	private Usuarios usuarios;
	private BigDecimal transMonto;
	private Date transFecha;
	private String transDescripcion;
	private String transHabilitado;

	public Transferencias() {
	}

	public Transferencias(long transCodigo, Cuentas cuentasByCueNumOrigen, Cuentas cuentasByCueNumDestino,
			Usuarios usuarios, BigDecimal transMonto, Date transFecha, String transHabilitado) {
		this.transCodigo = transCodigo;
		this.cuentasByCueNumOrigen = cuentasByCueNumOrigen;
		this.cuentasByCueNumDestino = cuentasByCueNumDestino;
		this.usuarios = usuarios;
		this.transMonto = transMonto;
		this.transFecha = transFecha;
		this.transHabilitado = transHabilitado;
	}

	public Transferencias(long transCodigo, Cuentas cuentasByCueNumOrigen, Cuentas cuentasByCueNumDestino,
			Usuarios usuarios, BigDecimal transMonto, Date transFecha, String transDescripcion,
			String transHabilitado) {
		this.transCodigo = transCodigo;
		this.cuentasByCueNumOrigen = cuentasByCueNumOrigen;
		this.cuentasByCueNumDestino = cuentasByCueNumDestino;
		this.usuarios = usuarios;
		this.transMonto = transMonto;
		this.transFecha = transFecha;
		this.transDescripcion = transDescripcion;
		this.transHabilitado = transHabilitado;
	}

	@Id

	@Column(name = "trans_codigo", unique = true, nullable = false, precision = 10, scale = 0)
	public long getTransCodigo() {
		return this.transCodigo;
	}

	public void setTransCodigo(long transCodigo) {
		this.transCodigo = transCodigo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cue_num_origen", nullable = false)
	public Cuentas getCuentasByCueNumOrigen() {
		return this.cuentasByCueNumOrigen;
	}

	public void setCuentasByCueNumOrigen(Cuentas cuentasByCueNumOrigen) {
		this.cuentasByCueNumOrigen = cuentasByCueNumOrigen;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cue_num_destino", nullable = false)
	public Cuentas getCuentasByCueNumDestino() {
		return this.cuentasByCueNumDestino;
	}

	public void setCuentasByCueNumDestino(Cuentas cuentasByCueNumDestino) {
		this.cuentasByCueNumDestino = cuentasByCueNumDestino;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usu_cedula", nullable = false)
	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	@Column(name = "trans_monto", nullable = false, precision = 10)
	public BigDecimal getTransMonto() {
		return this.transMonto;
	}

	public void setTransMonto(BigDecimal transMonto) {
		this.transMonto = transMonto;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "trans_fecha", nullable = false, length = 29)
	public Date getTransFecha() {
		return this.transFecha;
	}

	public void setTransFecha(Date transFecha) {
		this.transFecha = transFecha;
	}

	@Column(name = "trans_descripcion", length = 100)
	public String getTransDescripcion() {
		return this.transDescripcion;
	}

	public void setTransDescripcion(String transDescripcion) {
		this.transDescripcion = transDescripcion;
	}

	@Column(name = "trans_habilitado", nullable = false, length = 2)
	public String getTransHabilitado() {
		return this.transHabilitado;
	}

	public void setTransHabilitado(String transHabilitado) {
		this.transHabilitado = transHabilitado;
	}

}
