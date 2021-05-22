import { Muscle } from "./muscle";
export class Exercise {
    id?: number;
    nombre: string;
    duracion: number;
    series: number;
    repeticiones: number;
    imagen: string;
    descripcion: string;
    descanso: number;
    musculos?: Muscle[];

    constructor() {
    }
}
