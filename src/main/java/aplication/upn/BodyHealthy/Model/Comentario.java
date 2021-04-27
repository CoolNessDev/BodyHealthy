package aplication.upn.BodyHealthy.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter
    private int idComentario;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    @Getter @Setter
    private Usuario usuario;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_publicacion")
    @Getter @Setter
    private Publicacion publicacion;
    @Getter @Setter
    private String mensaje;
    @Getter @Setter
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
    
}
