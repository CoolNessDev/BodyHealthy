package aplication.upn.BodyHealthy.Security.Dto;

import aplication.upn.BodyHealthy.Security.Model.Rol;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class UserDto {
    @Getter @Setter
    private String imagen;
    @Getter @Setter
    private String nombres;
    @Getter @Setter
    private String apellidos;
    @Getter @Setter
    private Date fechaNacimiento;
    @Getter @Setter
    private float altura;
    @Getter @Setter
    private float peso;
    @Getter @Setter
    private String correo;
    @Getter @Setter
    private String contra;
}
