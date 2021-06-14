package aplication.upn.BodyHealthy.Controller;

import aplication.upn.BodyHealthy.Dto.EjercicioDto;
import aplication.upn.BodyHealthy.Dto.Message;
import aplication.upn.BodyHealthy.Dto.PublicacionDto;
import aplication.upn.BodyHealthy.Model.Ejercicio;
import aplication.upn.BodyHealthy.Model.Publicacion;
import aplication.upn.BodyHealthy.Security.Model.Usuario;
import aplication.upn.BodyHealthy.Security.Service.UsuarioService;
import aplication.upn.BodyHealthy.Service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/publicacion")
public class PublicacionController {
    @Autowired
    PublicacionService publicacionService;
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/list")
    public ResponseEntity<List<Publicacion>> getAllPublicacion() {
        List<Publicacion> publicacions = publicacionService.getAll();
        return new ResponseEntity(publicacions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDto> getPublicacion(@PathVariable("id") int id) {
        if (!publicacionService.existById(id)) {
            return new ResponseEntity(new Message("Publicacion no encontrada"), HttpStatus.NOT_FOUND);
        }
        Publicacion p =publicacionService.getPublicacion(id);
        Usuario usuarioDto = new Usuario();
        usuarioDto.setIdUsuario(p.getUsuario().getIdUsuario());
        usuarioDto.setNombres(p.getUsuario().getNombres());
        usuarioDto.setApellidos(p.getUsuario().getApellidos());
        usuarioDto.setImagen(p.getUsuario().getImagen());
        p.setUsuario(usuarioDto);
        PublicacionDto publicacionDto = new PublicacionDto(p.getIdPublicacion(),p.getUsuario(),p.getMensaje(),p.getImagen(),p.getFecha());
        return new ResponseEntity(publicacionDto, HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<PublicacionDto>> getAllPageable(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size,
                                                            @RequestParam(defaultValue = "fecha") String order,
                                                            @RequestParam(defaultValue = "true") boolean asc) {
        Page<Publicacion> publicacions = publicacionService.getAllPageable(PageRequest.of(page, size, Sort.by(order)));
        if (!asc) {
            publicacions = publicacionService.getAllPageable(PageRequest.of(page, size, Sort.by(order).descending()));
        }
        List<PublicacionDto> publicacionsDto = new ArrayList<>();
        for (Publicacion p : publicacions.getContent()) {
            Usuario usuarioDto = new Usuario();
            usuarioDto.setIdUsuario(p.getUsuario().getIdUsuario());
            usuarioDto.setNombres(p.getUsuario().getNombres());
            usuarioDto.setApellidos(p.getUsuario().getApellidos());
            usuarioDto.setImagen(p.getUsuario().getImagen());
            p.setUsuario(usuarioDto);
            PublicacionDto publicacionDto = new PublicacionDto(p.getIdPublicacion(),p.getUsuario(),p.getMensaje(),p.getImagen(),p.getFecha());
            publicacionsDto.add(publicacionDto);
        }
        Page<PublicacionDto> publicacionDtos = new PageImpl<PublicacionDto>(publicacionsDto);

        return new ResponseEntity<Page<PublicacionDto>>(publicacionDtos, HttpStatus.OK);

    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<PublicacionDto>> getByUsuario(@PathVariable("id") int id) {
        if (!usuarioService.existsById(id)) {
            return new ResponseEntity(new Message("Usuario no encontrado"), HttpStatus.NOT_FOUND);
        }
        Usuario usuario = usuarioService.getUsuario(id);
        if (!publicacionService.existByUsuario(usuario)) {
            return new ResponseEntity(new Message("Publication by user not found"), HttpStatus.NOT_FOUND);
        }
        List<Publicacion> publicacions = publicacionService.findByUsuario(usuario);
        List<PublicacionDto> publicacionsDto = new ArrayList<>();
        for (Publicacion p : publicacions) {
            Usuario usuarioDto = new Usuario();
            usuarioDto.setIdUsuario(p.getUsuario().getIdUsuario());
            usuarioDto.setNombres(p.getUsuario().getNombres());
            usuarioDto.setApellidos(p.getUsuario().getApellidos());
            usuarioDto.setImagen(p.getUsuario().getImagen());
            p.setUsuario(usuarioDto);
            PublicacionDto publicacionDto = new PublicacionDto(p.getIdPublicacion(),p.getUsuario(),p.getMensaje(),p.getImagen(),p.getFecha());
            publicacionsDto.add(publicacionDto);
        }
        return new ResponseEntity<List<PublicacionDto>>(publicacionsDto, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PublicacionDto publicacionDto) {
        if (publicacionDto.getMensaje().equals(""))
            return new ResponseEntity(new Message("el mensaje es obligatorio"), HttpStatus.BAD_REQUEST);
        if (!usuarioService.existsById(publicacionDto.getUsuario().getIdUsuario()))
            return new ResponseEntity(new Message("el usuario relacionado no es encontrado"), HttpStatus.NOT_FOUND);
        Publicacion publicacion = new Publicacion();
        publicacion.setUsuario(publicacionDto.getUsuario());
        publicacion.setImagen(publicacionDto.getImagen());
        publicacion.setFecha(publicacionDto.getFecha());
        publicacion.setMensaje(publicacionDto.getMensaje());
        publicacionService.insert(publicacion);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(defaultValue = "0") int id, @RequestParam(defaultValue = "0") int idUsuario) {
        if (id<=0||idUsuario<=0)
            return new ResponseEntity(new Message("Campos invalidos"), HttpStatus.BAD_REQUEST);
        if (!usuarioService.existsById(idUsuario))
            return new ResponseEntity(new Message("El usuario relacionado no es encontrado"), HttpStatus.NOT_FOUND);
        if (!publicacionService.existById(id))
            return new ResponseEntity(new Message("Publicación no encontrada"), HttpStatus.NOT_FOUND);
        Publicacion publicacio = publicacionService.getPublicacion(id);
        if (publicacio.getUsuario().getIdUsuario()!=idUsuario)
            return new ResponseEntity(new Message("El usuario no es dueño de esta publicaión"), HttpStatus.BAD_REQUEST);
        publicacionService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
