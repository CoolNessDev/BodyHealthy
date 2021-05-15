import { Exercise } from "./exercise";

export class Muscle {
  id?: number;
  nombre: string;
  ejercicios: Exercise[];

  constructor(nombre: string, ejercicios: Exercise[]) {
    this.nombre = nombre;
    this.ejercicios = ejercicios;
  }
}
