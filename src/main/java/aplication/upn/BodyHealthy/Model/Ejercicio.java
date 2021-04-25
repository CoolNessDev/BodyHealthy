package aplication.upn.BodyHealthy.Model;

import javax.persistence.*;
import java.util.List;
public class Ejercicio {
	private int idEjercicio;
	private String nombre;
	private int duracion;
	private int series;
	private int repeticiones;
	private int descanso;
	private String imagen ;
	private String descripcion;
	private List<Musculo> musculos;
	public Ejercicio(int idEjercicio, String nombre, int duracion, int series, int repeticiones, int descanso, String imagen,
                     String descripcion) {
		this.idEjercicio = idEjercicio;
		this.nombre = nombre;
		this.duracion = duracion;
		this.series = series;
		this.repeticiones = repeticiones;
		this.descanso = descanso;
		this.imagen = imagen;
		this.descripcion = descripcion;
	}
	public Ejercicio(String nombre, int duracion, int series, int repeticiones, int descanso, String imagen,
                     String descripcion, List<Musculo> musculos) {
		this.nombre = nombre;
		this.duracion = duracion;
		this.series = series;
		this.repeticiones = repeticiones;
		this.descanso = descanso;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.musculos = musculos;
	}
	public Ejercicio() {
	}

	public int getIdEjercicio() {
		return idEjercicio;
	}
	public void setIdEjercicio(int id) {
		this.idEjercicio = id;
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
	public int getDescanso() {
		return descanso;
	}
	public void setDescanso(int descanso) {
		this.descanso = descanso;
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

	public List<Musculo> getMusculos() {
		return musculos;
	}

	public void setMusculos(List<Musculo> musculos) {
		this.musculos = musculos;
	}
	
	
}
