package aplication.upn.BodyHealthy.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter
    private int idRol;
    @Getter @Setter
    private String cargo;
    @Getter @Setter
    private boolean estado;

    @OneToMany(mappedBy = "rol")
    @Getter @Setter
    private Set<Usuario> usuarios = new HashSet<>();

    public Rol() {
    }

    public Rol(String cargo, boolean estado) {
        this.cargo = cargo;
        this.estado = estado;
    }

    public Rol(int idRol, String cargo, boolean estado) {
        this.idRol = idRol;
        this.cargo = cargo;
        this.estado = estado;
    }

    public Rol(int idRol, String cargo, boolean estado, Set<Usuario> usuarios) {
        this.idRol = idRol;
        this.cargo = cargo;
        this.estado = estado;
        this.usuarios = usuarios;
    }
}
