import { Exercise } from "./exercise";

export class Routine{
  idRutina?: number;
  nombre: string;
  nivel: string;
  idUsuario: number;
  ejercicios: Exercise[];
  id_ejercicio?: number[];
  constructor() {
  }
}
