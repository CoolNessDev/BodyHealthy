package aplication.upn.BodyHealthy.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "musculo")
public class Musculo {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idMusculo;
	private String nombre;

	@ManyToMany(mappedBy = "musculos")
	private Set<Ejercicio> ejercicios = new HashSet<>();

	public Musculo(int idMusculo, String nombre, Set<Ejercicio> ejercicios) {
		this.idMusculo = idMusculo;
		this.nombre = nombre;
		this.ejercicios = ejercicios;
	}

	public Musculo(String nombre ) {
		this.nombre = nombre;
	}
	public Musculo() {

	}
	public int getIdMusculo() {
		return idMusculo;
	}
	
	public void setIdMusculo(int idMusculo) {
		this.idMusculo = idMusculo;
	}
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
