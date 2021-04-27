package aplication.upn.BodyHealthy.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rol")
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter
    private int idRol;
    @Getter @Setter
    private String cargo;
    @Getter @Setter
    private boolean estado;

//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUsuario")
//    @JsonIdentityReference(alwaysAsId = true)
//    @JsonProperty("id_usuario")
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
//    @Getter @Setter
//    private Set<Usuario> usuarios = new HashSet<>();
}
