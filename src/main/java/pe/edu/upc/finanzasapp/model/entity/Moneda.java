package pe.edu.upc.finanzasapp.model.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.Table;



@Entity
@Table(name = "moneda")
public class Moneda {
	

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="moneda_id")
	private int id;
	
	
	@Column(name="nombre", nullable=false)
	private String nombre;
	

	public Moneda() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Moneda(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	

	
	
}
