package aplication.upn.BodyHealthy.Model;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idUsuario;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_rol")
	private Rol rol;

	private String imagen;
	private String nombres;
	private String apellidos;
	private Date fechaNacimiento;
	private float altura;
	private float peso;
	private String correo;
	private String contra;

	@OneToMany(mappedBy = "usuario")
	private Set<Publicacion> publicaciones = new HashSet<>();

	@OneToMany(mappedBy = "usuario")
	private Set<Comentario> comentarios = new HashSet<>();
	@ManyToMany
	@JoinTable(
			name = "rutina_usuario",
			joinColumns = @JoinColumn(name = "id_usuario"),
			inverseJoinColumns = @JoinColumn(name = "id_rutina"))
	private Set<Rutina> rutinas = new HashSet<>();

	public Usuario(int idUsuario, Rol rol, String imagen, String nombres, String apellidos, Date fechaNacimiento, float altura, float peso, String correo, String contra, Set<Publicacion> publicaciones, Set<Comentario> comentarios, Set<Rutina> rutinas) {
		this.idUsuario = idUsuario;
		this.rol = rol;
		this.imagen = imagen;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.altura = altura;
		this.peso = peso;
		this.correo = correo;
		this.contra = contra;
		this.publicaciones = publicaciones;
		this.comentarios = comentarios;
		this.rutinas = rutinas;
	}

	public Usuario(Rol rol, String imagen, String nombres, String apellidos, Date fechaNacimiento, float altura, float peso, String correo, String contra, Set<Publicacion> publicaciones, Set<Comentario> comentarios, Set<Rutina> rutinas) {
		this.rol = rol;
		this.imagen = imagen;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.altura = altura;
		this.peso = peso;
		this.correo = correo;
		this.contra = contra;
		this.publicaciones = publicaciones;
		this.comentarios = comentarios;
		this.rutinas = rutinas;
	}

	public Usuario() {
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}
}
