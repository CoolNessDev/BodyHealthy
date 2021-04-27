package aplication.upn.BodyHealthy.Model;

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

    @OneToMany(cascade = CascadeType.ALL
            , fetch = FetchType.EAGER, mappedBy = "rol")
    @Getter @Setter
    private Set<Usuario> usuarios = new HashSet<>();
}
