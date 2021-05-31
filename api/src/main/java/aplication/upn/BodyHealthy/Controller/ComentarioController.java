package aplication.upn.BodyHealthy.Controller;

import aplication.upn.BodyHealthy.Dto.ComentarioDto;
import aplication.upn.BodyHealthy.Dto.Message;
import aplication.upn.BodyHealthy.Dto.PublicacionDto;
import aplication.upn.BodyHealthy.Model.Comentario;
import aplication.upn.BodyHealthy.Model.Publicacion;
import aplication.upn.BodyHealthy.Security.Model.Usuario;
import aplication.upn.BodyHealthy.Service.ComentarioService;
import aplication.upn.BodyHealthy.Service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/comentario")
public class ComentarioController {
    @Autowired
    ComentarioService comentarioService;
    @Autowired
    PublicacionService publicacionService;

    @GetMapping("/publicacion/{id}")
    public ResponseEntity<List<ComentarioDto>> getByPublicacion(@PathVariable("id") int id) {
        if (!publicacionService.existById(id)) {
            return new ResponseEntity(new Message("Publicacion no encontrada"), HttpStatus.NOT_FOUND);
        }
        Publicacion publicacion = publicacionService.getPublicacion(id);
        if (!comentarioService.existsByPublicacion(publicacion)) {
            return new ResponseEntity(new Message("Comentaries by publication not found"), HttpStatus.NOT_FOUND);
        }
        List<Comentario> comentarios = comentarioService.findByPublicacion(publicacion);
        List<ComentarioDto> comentariosDtos = new ArrayList<>();
        for (Comentario c : comentarios) {
            Usuario usuarioDto = new Usuario();
            usuarioDto.setIdUsuario(c.getUsuario().getIdUsuario());
            usuarioDto.setNombres(c.getUsuario().getNombres());
            usuarioDto.setApellidos(c.getUsuario().getApellidos());
            usuarioDto.setImagen(c.getUsuario().getImagen());
            c.setUsuario(usuarioDto);
            ComentarioDto comentarioDto = new ComentarioDto(c.getIdComentario(),c.getUsuario(),null,c.getMensaje(),c.getFecha());
            comentariosDtos.add(comentarioDto);
        }
        return new ResponseEntity<List<ComentarioDto>>(comentariosDtos, HttpStatus.OK);
    }
}
