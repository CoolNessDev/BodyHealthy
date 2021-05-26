package aplication.upn.BodyHealthy.Dto;

import aplication.upn.BodyHealthy.Model.Ejercicio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@AllArgsConstructor
public class RutinaDto {
    @Getter @Setter
    private int idRutina;
    @Getter @Setter
    private int idUsuario;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String nivel;
    @Getter @Setter
    private float estado;//nivel de progreso de la rutina
    @Getter @Setter
    private Set<Ejercicio> ejercicios;



}
