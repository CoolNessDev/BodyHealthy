package aplication.upn.BodyHealthy.Security.Dto;

import lombok.Getter;
import lombok.Setter;

public class LoginUserDto {
    @Getter @Setter
    private String correo;
    @Getter @Setter
    private String contra;
}
