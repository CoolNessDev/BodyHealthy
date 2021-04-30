package aplication.upn.BodyHealthy.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ejercicio")
@AllArgsConstructor
@NoArgsConstructor
public class Ejercicio {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Getter @Setter
	private int idEjercicio;
	@Getter @Setter
	private String nombre;
	@Getter @Setter
	private int duracion;
	@Getter @Setter
	private int series;
	@Getter @Setter
	private int repeticiones;
	@Getter @Setter
	private String imagen ;
	@Getter @Setter
	private String descripcion;
	@Getter @Setter
	private int descanso;

	@ManyToMany
	@JoinTable(
			name = "ejercicio_musculo",
			joinColumns = @JoinColumn(name = "id_ejercicio"),
			inverseJoinColumns = @JoinColumn(name = "id_musculo"))
	@Getter @Setter
	private Set<Musculo> musculos = new HashSet<>();

	@ManyToMany(mappedBy = "ejercicios")
	@Getter @Setter
	private Set<Rutina> rutinas = new HashSet<>();
}
