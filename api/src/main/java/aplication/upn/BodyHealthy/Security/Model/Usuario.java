package aplication.upn.BodyHealthy.Security.Model;
import aplication.upn.BodyHealthy.Model.Comentario;
import aplication.upn.BodyHealthy.Model.Publicacion;
import aplication.upn.BodyHealthy.Model.Rutina;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Getter @Setter
	private int idUsuario;


	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_rol")
	@Getter @Setter
	private Rol rol;
	@Getter @Setter
	private String imagen;
	@Getter @Setter
	private String nombres;
	@Getter @Setter
	private String apellidos;
	@Getter @Setter
	private Date fechaNacimiento;
	@Getter @Setter
	private float altura;
	@Getter @Setter
	private float peso;
	@Getter @Setter
	private String correo;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Getter @Setter
	private String contra;

	@OneToMany(fetch =FetchType.LAZY ,mappedBy = "usuario")
	@Getter @Setter
	private Set<Publicacion> publicaciones = new HashSet<>();

	@OneToMany(fetch =FetchType.LAZY,mappedBy = "usuario")
	@Getter @Setter
	private Set<Comentario> comentarios = new HashSet<>();
//	@ManyToMany
//	@JoinTable(
//			name = "rutina_usuario",
//			joinColumns = @JoinColumn(name = "id_usuario"),
//			inverseJoinColumns = @JoinColumn(name = "id_rutina"))
//	@Getter @Setter
//	private Set<Rutina> rutinas = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    @Getter @Setter
    private Set<Rutina> rutinas = new HashSet<>();

	public Usuario(String imagen, String nombres, String apellidos, Date fechaNacimiento, float altura, float peso, String correo, String contra) {
		this.imagen = imagen;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.altura = altura;
		this.peso = peso;
		this.correo = correo;
		this.contra = contra;
	}
}
