package aplication.upn.BodyHealthy.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rutina")
public class Rutina {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idRutina;
    private String nombre;
    private String nivel;
    private float estado;//nivel de progreso de la rutina
    @ManyToMany
    @JoinTable(
            name = "rutina_ejercicio",
            joinColumns = @JoinColumn(name = "id_rutina"),
            inverseJoinColumns = @JoinColumn(name = "id_ejercicio"))
    private Set<Ejercicio> ejercicios = new HashSet<>();

    @ManyToMany(mappedBy = "rutinas")
    private Set<Usuario> usuarios = new HashSet<>();

    public Rutina() {
    }

    public Rutina(int idRutina, String nombre, String nivel, float estado, Set<Ejercicio> ejercicios, Set<Usuario> usuarios) {
        this.idRutina = idRutina;
        this.nombre = nombre;
        this.nivel = nivel;
        this.estado = estado;
        this.ejercicios = ejercicios;
        this.usuarios = usuarios;
    }

    public Rutina(String nombre, String nivel, float estado, Set<Ejercicio> ejercicios, Set<Usuario> usuarios) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.estado = estado;
        this.ejercicios = ejercicios;
        this.usuarios = usuarios;
    }

    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public float getEstado() {
        return estado;
    }

    public void setEstado(float estado) {
        this.estado = estado;
    }

    public Set<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(Set<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
