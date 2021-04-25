package aplication.upn.BodyHealthy.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idRol;
    private String cargo;
    private boolean estado;

    @OneToMany(mappedBy = "rol")
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

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
