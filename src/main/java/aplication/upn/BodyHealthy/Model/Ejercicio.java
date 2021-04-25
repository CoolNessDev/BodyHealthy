package aplication.upn.BodyHealthy.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ejercicio")
public class Ejercicio {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idEjercicio;

	private String nombre;
	private int duracion;
	private int series;
	private int repeticiones;
	private String imagen ;
	private String descripcion;
	private int descanso;

	@ManyToMany
	@JoinTable(
			name = "ejercicio_musculo",
			joinColumns = @JoinColumn(name = "id_ejercicio"),
			inverseJoinColumns = @JoinColumn(name = "id_musculo"))
	private Set<Musculo> musculos = new HashSet<>();

	@ManyToMany(mappedBy = "ejercicios")
	private Set<Rutina> rutinas = new HashSet<>();


	public Ejercicio(String nombre, int duracion, int series, int repeticiones, String imagen, String descripcion, int descanso, Set<Musculo> musculos, Set<Rutina> rutinas) {
		this.nombre = nombre;
		this.duracion = duracion;
		this.series = series;
		this.repeticiones = repeticiones;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.descanso = descanso;
		this.musculos = musculos;
		this.rutinas = rutinas;
	}

	public Ejercicio() {
	}

	public Ejercicio(int idEjercicio, String nombre, int duracion, int series, int repeticiones, String imagen, String descripcion, int descanso, Set<Musculo> musculos, Set<Rutina> rutinas) {
		this.idEjercicio = idEjercicio;
		this.nombre = nombre;
		this.duracion = duracion;
		this.series = series;
		this.repeticiones = repeticiones;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.descanso = descanso;
		this.musculos = musculos;
		this.rutinas = rutinas;
	}

	public Set<Rutina> getRutinas() {
		return rutinas;
	}

	public void setRutinas(Set<Rutina> rutinas) {
		this.rutinas = rutinas;
	}

	public int getIdEjercicio() {
		return idEjercicio;
	}

	public void setIdEjercicio(int idEjercicio) {
		this.idEjercicio = idEjercicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public int getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(int repeticiones) {
		this.repeticiones = repeticiones;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDescanso() {
		return descanso;
	}

	public void setDescanso(int descanso) {
		this.descanso = descanso;
	}

	public Set<Musculo> getMusculos() {
		return musculos;
	}

	public void setMusculos(Set<Musculo> musculos) {
		this.musculos = musculos;
	}
}
