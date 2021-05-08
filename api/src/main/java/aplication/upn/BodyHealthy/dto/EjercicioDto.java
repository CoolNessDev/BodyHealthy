package aplication.upn.BodyHealthy.dto;

import lombok.Getter;
import lombok.Setter;



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
}
