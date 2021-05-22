package aplication.upn.BodyHealthy.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idMusculo")
//    @JsonIdentityReference(alwaysAsId = true)
//    @JsonProperty("id_musculo")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "ejercicio_musculo",
			joinColumns = @JoinColumn(name = "id_ejercicio"),
			inverseJoinColumns = @JoinColumn(name = "id_musculo"))
	@Getter @Setter
	private Set<Musculo> musculos = new HashSet<>();

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idRutina")
//	@JsonIdentityReference(alwaysAsId = true)
//	@JsonProperty("id_rutina")
	@ManyToMany(mappedBy = "ejercicios")
	@Getter @Setter
	private Set<Rutina> rutinas = new HashSet<>();

	public Ejercicio(String nombre, int duracion, int series, int repeticiones, String imagen, String descripcion, int descanso) {
		this.nombre = nombre;
		this.duracion = duracion;
		this.series = series;
		this.repeticiones = repeticiones;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.descanso = descanso;
	}
	public Ejercicio(String nombre, int duracion, int series, int repeticiones, String imagen, String descripcion, int descanso,Set<Musculo> musculos) {
		this.nombre = nombre;
		this.duracion = duracion;
		this.series = series;
		this.repeticiones = repeticiones;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.descanso = descanso;
		this.musculos = musculos;
	}
}
