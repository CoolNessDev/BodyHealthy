package aplication.upn.BodyHealthy.Dto;

import aplication.upn.BodyHealthy.Security.Model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class PublicacionDto {
    @Getter @Setter
    private int idPublicacion;
    @Getter @Setter
    private Usuario usuario;
    @Getter @Setter
    private String mensaje;
    @Getter @Setter
    private String imagen;
    @Getter @Setter
    private Date fecha;
}
