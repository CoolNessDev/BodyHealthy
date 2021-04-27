package aplication.upn.BodyHealthy.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rutina")
@AllArgsConstructor
@NoArgsConstructor
public class Rutina {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter
    private int idRutina;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String nivel;
    @Getter @Setter
    private float estado;//nivel de progreso de la rutina
    @ManyToMany
    @JoinTable(
            name = "rutina_ejercicio",
            joinColumns = @JoinColumn(name = "id_rutina"),
            inverseJoinColumns = @JoinColumn(name = "id_ejercicio"))
    @Getter @Setter
    private Set<Ejercicio> ejercicios = new HashSet<>();

    @ManyToMany(mappedBy = "rutinas")
    @Getter @Setter
    private Set<Usuario> usuarios = new HashSet<>();
}
