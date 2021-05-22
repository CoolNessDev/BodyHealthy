package aplication.upn.BodyHealthy.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToMany(mappedBy = "musculos")
	@Getter @Setter
	private Set<Ejercicio> ejercicios = new HashSet<>();

	@Override
	public String toString() {
		return "Musculo{" +
				"idMusculo=" + idMusculo +
				", nombre='" + nombre + '\'' +
//				", ejercicios=" + ejercicios +
				'}';
	}
}
