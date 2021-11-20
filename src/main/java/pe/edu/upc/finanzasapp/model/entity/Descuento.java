package pe.edu.upc.finanzasapp.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "descuentos")
public class Descuento {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name ="descuento_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name ="registro_id", nullable = false)
	private Registro registro;
	
	@Column(name = "tipotasa")
	private String tipoTasa;
	
	@Column(name = "tasa")
	private double tasa;
	
	@Column(name = "d")
	private double d;
	
	@Column(name = "descuento")
	private double descuento;
	
	@Column(name = "gastosiniciales")
	private double gastosIniciales;
	
	@Column(name = "gastosfinales")
	private double gastosFinales;
	
	@Column(name = "valorneto")
	private double valorNeto;
	
	@Column(name = "valorrecibido")
	private double valorRecibido;
	
	@Column(name = "valorentregado")
	private double valorEntregado;
	
	@Column(name = "tcea")
	private double tcea;

	public Descuento() {
		super();
		
	}

	public Descuento(Integer id, Registro registro, String tipoTasa, double tasa, double d, double descuento,
			double gastosIniciales, double gastosFinales, double valorNeto, double valorRecibido, double valorEntregado,
			double tcea) {
		super();
		this.id = id;
		this.registro = registro;
		this.tipoTasa = tipoTasa;
		this.tasa = tasa;
		this.d = d;
		this.descuento = descuento;
		this.gastosIniciales = gastosIniciales;
		this.gastosFinales = gastosFinales;
		this.valorNeto = valorNeto;
		this.valorRecibido = valorRecibido;
		this.valorEntregado = valorEntregado;
		this.tcea = tcea;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public String getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(String tipoTasa) {
		this.tipoTasa = tipoTasa;
	}

	public double getTasa() {
		return tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getGastosIniciales() {
		return gastosIniciales;
	}

	public void setGastosIniciales(double gastosIniciales) {
		this.gastosIniciales = gastosIniciales;
	}

	public double getGastosFinales() {
		return gastosFinales;
	}

	public void setGastosFinales(double gastosFinales) {
		this.gastosFinales = gastosFinales;
	}

	public double getValorNeto() {
		return valorNeto;
	}

	public void setValorNeto(double valorNeto) {
		this.valorNeto = valorNeto;
	}

	public double getValorRecibido() {
		return valorRecibido;
	}

	public void setValorRecibido(double valorRecibido) {
		this.valorRecibido = valorRecibido;
	}

	public double getValorEntregado() {
		return valorEntregado;
	}

	public void setValorEntregado(double valorEntregado) {
		this.valorEntregado = valorEntregado;
	}

	public double getTcea() {
		return tcea;
	}

	public void setTcea(double tcea) {
		this.tcea = tcea;
	}

	
	
	
}
