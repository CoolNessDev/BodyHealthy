export class NewUser {
  imagen: string;
  nombres: string;
  apellidos: string;
  fechaNacimiento: Date;
  altura: number;
  peso: number;
  correo: string;
  contra: string;
  constructor(
    imagen: string,
    nombres: string,
    apellidos: string,
    fechaNacimiento: Date,
    altura: number,
    peso: number,
    correo: string,
    contra: string
  ) {
    this.imagen = imagen;
    this.nombres = nombres;
    this.apellidos = apellidos;
    this.fechaNacimiento = fechaNacimiento;
    this.altura = altura;
    this.peso = peso;
    this.correo = correo;
    this.contra = contra;
  }
}
