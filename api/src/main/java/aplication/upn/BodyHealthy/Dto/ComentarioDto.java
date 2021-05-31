package aplication.upn.BodyHealthy.Dto;

import aplication.upn.BodyHealthy.Model.Publicacion;
import aplication.upn.BodyHealthy.Security.Model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDto {
    @Getter
    @Setter
    private int idComentario;
    @Getter @Setter
    private Usuario usuario;
    @Getter @Setter
    private Publicacion publicacion;
    @Getter @Setter
    private String mensaje;
    @Getter @Setter
    private Date fecha;
}
