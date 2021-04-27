package aplication.upn.BodyHealthy.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "musculo")
public class Musculo {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Getter @Setter
	private int idMusculo;
	@Getter @Setter
	private String nombre;

	@ManyToMany(mappedBy = "musculos")
	@Getter @Setter
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

}
