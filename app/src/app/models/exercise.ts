export class Exercise {
    id?: number;
    nombre: string;
    duracion: number;
    series: number;
    repeticiones: number;
    imagen: string;
    descripcion: string;
    descanso: number;

    constructor(nombre: string, duracion: number, series: number, repeticiones: number,
        imagen: string, descripcion: string, descanso: number) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.series = series;
        this.repeticiones = repeticiones;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.descanso = descanso;
    }
}
