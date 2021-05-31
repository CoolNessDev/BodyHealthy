import { User } from "./user";

export class Publication{
  idPublicacion?: number;
  imagen?: string;
  mensaje: string;
  fecha: Date;
  usuario?: User;
}
