package aplication.upn.BodyHealthy.Controller;

import aplication.upn.BodyHealthy.Dto.ComentarioDto;
import aplication.upn.BodyHealthy.Dto.Message;
import aplication.upn.BodyHealthy.Dto.PublicacionDto;
import aplication.upn.BodyHealthy.Model.Comentario;
import aplication.upn.BodyHealthy.Model.Publicacion;
import aplication.upn.BodyHealthy.Security.Model.Usuario;
import aplication.upn.BodyHealthy.Security.Service.UsuarioService;
import aplication.upn.BodyHealthy.Service.ComentarioService;
import aplication.upn.BodyHealthy.Service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/publicacion/{id}")
    public ResponseEntity<List<ComentarioDto>> getByPublicacion(@PathVariable("id") int id) {
        if (!publicacionService.existsById(id)) {
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
            ComentarioDto comentarioDto = new ComentarioDto(c.getIdComentario(), c.getUsuario(), null, c.getMensaje(), c.getFecha());
            comentariosDtos.add(comentarioDto);
        }
        return new ResponseEntity<List<ComentarioDto>>(comentariosDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ComentarioDto comentarioDto) {
        if (comentarioDto.getMensaje().equals(""))
            return new ResponseEntity(new Message("el mensaje es obligatorio"), HttpStatus.BAD_REQUEST);
        if (!usuarioService.existsById(comentarioDto.getUsuario().getIdUsuario()))
            return new ResponseEntity(new Message("el usuario relacionado no es encontrado"), HttpStatus.NOT_FOUND);
        if (!publicacionService.existsById(comentarioDto.getPublicacion().getIdPublicacion()))
            return new ResponseEntity(new Message("la publicacion relacionada no ha sido encontrada"), HttpStatus.NOT_FOUND);
        Comentario comentario = new Comentario();
        comentario.setFecha(comentarioDto.getFecha());
        comentario.setMensaje(comentarioDto.getMensaje());
        comentario.setUsuario(comentarioDto.getUsuario());
        comentario.setPublicacion(comentarioDto.getPublicacion());
        comentarioService.insert(comentario);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(defaultValue = "0") int id, @RequestParam(defaultValue = "0") int idUsuario) {
        if (id<=0||idUsuario<=0)
            return new ResponseEntity(new Message("Campos invalidos"), HttpStatus.BAD_REQUEST);
        if (!usuarioService.existsById(idUsuario))
            return new ResponseEntity(new Message("El usuario relacionado no es encontrado"), HttpStatus.NOT_FOUND);
        if (!comentarioService.existsById(id))
            return new ResponseEntity(new Message("Comentario no encontrado"), HttpStatus.NOT_FOUND);
        Comentario comentario = comentarioService.getComentario(id);
        if (comentario.getUsuario().getIdUsuario()!=idUsuario)
            return new ResponseEntity(new Message("El usuario no es dueño de este comentario"), HttpStatus.BAD_REQUEST);
        comentarioService.delete(comentario);
        return new ResponseEntity(HttpStatus.OK);
    }
}
