package aplication.upn.BodyHealthy.Dto;

import aplication.upn.BodyHealthy.Model.Musculo;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


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
    private Set<Musculo> musculos = new HashSet<>();
    public EjercicioDto() {
    }

    public EjercicioDto(String nombre, int duracion, int series, int repeticiones, String imagen, String descripcion, int descanso) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.series = series;
        this.repeticiones = repeticiones;
        this.imagen = imagen;
        this.descripcion = "descripcion";
        this.descanso = descanso;
    }

    public EjercicioDto(String nombre, int duracion, int series, int repeticiones, String imagen, String descripcion, int descanso, Set<Musculo> musculos) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.series = series;
        this.repeticiones = repeticiones;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.descanso = descanso;
        this.musculos = musculos;
    }
}
