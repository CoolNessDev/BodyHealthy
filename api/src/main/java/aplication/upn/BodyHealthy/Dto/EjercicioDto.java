package aplication.upn.BodyHealthy.Dto;

import aplication.upn.BodyHealthy.Model.Musculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
public class EjercicioDto {

    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private int duracion;
    @Getter @Setter
    private int series;
    @Getter @Setter
    private int repeticiones;
    @Getter @Setter
    private String imagen ;
    @Getter @Setter
    private String descripcion;
    @Getter @Setter
    private int descanso;
    @Getter @Setter
    private Set<Musculo> musculos;

}
