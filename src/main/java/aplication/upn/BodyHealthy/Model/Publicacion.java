package aplication.upn.BodyHealthy.Model;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publicacion")
public class Publicacion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idPublicacion;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String mensaje;
    private String imagen;
    private Date fecha;

    @OneToMany(mappedBy = "publicacion")
    private Set<Comentario> comentarios = new HashSet<>();

    public Publicacion() {
    }

    public Publicacion(int idPublicacion, Usuario usuario, String mensaje, String imagen, Date fecha, Set<Comentario> comentarios) {
        this.idPublicacion = idPublicacion;
        this.usuario = usuario;
        this.mensaje = mensaje;
        this.imagen = imagen;
        this.fecha = fecha;
        this.comentarios = comentarios;
    }

    public Publicacion(Usuario usuario, String mensaje, String imagen, Date fecha, Set<Comentario> comentarios) {
        this.usuario = usuario;
        this.mensaje = mensaje;
        this.imagen = imagen;
        this.fecha = fecha;
        this.comentarios = comentarios;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
