package aplication.upn.BodyHealthy.Model;

import aplication.upn.BodyHealthy.Security.Model.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comentario")
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter
    private int idComentario;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_usuario")
    @Getter @Setter
    private Usuario usuario;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_publicacion")
    @Getter @Setter
    private Publicacion publicacion;
    @Getter @Setter
    private String mensaje;
    @Getter @Setter
    private Date fecha;
}
