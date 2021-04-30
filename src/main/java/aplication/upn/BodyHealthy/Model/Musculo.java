package aplication.upn.BodyHealthy.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "musculo")
@AllArgsConstructor
@NoArgsConstructor
public class Musculo {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Getter @Setter
	private int idMusculo;
	@Getter @Setter
	private String nombre;

	@ManyToMany(mappedBy = "musculos")
	@Getter @Setter
	private Set<Ejercicio> ejercicios = new HashSet<>();
}
