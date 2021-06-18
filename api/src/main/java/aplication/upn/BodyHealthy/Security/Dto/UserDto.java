package aplication.upn.BodyHealthy.Security.Dto;

import aplication.upn.BodyHealthy.Security.Model.Rol;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
public class UserDto {
    @Getter @Setter
    private String imagen;
    @Getter @Setter
    private String nombres;
    @Getter @Setter
    private String apellidos;
    @Getter @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
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
