package aplication.upn.BodyHealthy.Model;


import aplication.upn.BodyHealthy.Security.Model.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publicacion")
@AllArgsConstructor
@NoArgsConstructor
public class Publicacion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter
    private int idPublicacion;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_usuario")
    @Getter @Setter
    private Usuario usuario;
    @Getter @Setter
    private String mensaje;
    @Getter @Setter
    private String imagen;
    @Getter @Setter
    private Date fecha;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "publicacion")
    @Getter @Setter
    private Set<Comentario> comentarios = new HashSet<>();
}
