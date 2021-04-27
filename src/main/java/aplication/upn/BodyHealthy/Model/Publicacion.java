package aplication.upn.BodyHealthy.Model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publicacion")
public class Publicacion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter
    private int idPublicacion;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    @Getter @Setter
    private Usuario usuario;
    @Getter @Setter
    private String mensaje;
    @Getter @Setter
    private String imagen;
    @Getter @Setter
    private Date fecha;

    @OneToMany(mappedBy = "publicacion")
    @Getter @Setter
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

}
