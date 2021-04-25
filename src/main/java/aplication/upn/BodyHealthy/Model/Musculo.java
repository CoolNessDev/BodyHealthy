package aplication.upn.BodyHealthy.Model;

public class Musculo {
	private int idMusculo;
	private String nombre;

	public Musculo(int idMusculo, String nombre ) {
		this.idMusculo = idMusculo;
		this.nombre = nombre;
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
