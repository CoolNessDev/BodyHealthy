package aplication.upn.BodyHealthy.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idComentario;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_publicacion")
    private Publicacion publicacion;
    private String mensaje;
    private Date fecha;

    public Comentario(int idComentario, Usuario usuario, Publicacion publicacion, String mensaje, Date fecha) {
        this.idComentario = idComentario;
        this.usuario = usuario;
        this.publicacion = publicacion;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public Comentario(Usuario usuario, Publicacion publicacion, String mensaje, Date fecha) {
        this.usuario = usuario;
        this.publicacion = publicacion;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public Comentario() {
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
