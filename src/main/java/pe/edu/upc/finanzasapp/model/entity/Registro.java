package pe.edu.upc.finanzasapp.model.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "registro")
public class Registro {
	

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "registro_id")
	private Integer id;

	
	@Column(name="nombreEmpresa", nullable=false, length=80)
	private String nombreEmpresa;
	
	@Column(name="montoTotal", nullable=false, length=10)
	private float montoTotal;
	
	@Column(name = "fechainicio", nullable = false)	
	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechainicio;
	
	@Column(name = "fechafinal", nullable = false)	
	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechafinal;

	
	@ManyToOne
	@JoinColumn(name = "moneda_id", nullable = false)
	private Moneda moneda;
	
	@Column(name ="dias")
	private long dias;

	public Registro() {
		super();
		// TODO Auto-generated constructor stub
	}


	

	public Registro(Integer id, String nombreEmpresa, float montoTotal, Date fechainicio, Date fechafinal,
			Moneda moneda, long dias) {
		super();
		this.id = id;
		this.nombreEmpresa = nombreEmpresa;
		this.montoTotal = montoTotal;
		this.fechainicio = fechainicio;
		this.fechafinal = fechafinal;
		this.moneda = moneda;
		this.dias = dias;
	}



	





	public long getDias() {
		return dias;
	}




	public void setDias(long dias) {
		this.dias = dias;
	}




	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombreEmpresa() {
		return nombreEmpresa;
	}


	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}


	public float getMontoTotal() {
		return montoTotal;
	}


	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}


	public Date getFechainicio() {
		return fechainicio;
	}


	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}


	public Date getFechafinal() {
		return fechafinal;
	}


	public void setFechafinal(Date fechafinal) {
		this.fechafinal = fechafinal;
	}


	public Moneda getMoneda() {
		return moneda;
	}


	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}



}
