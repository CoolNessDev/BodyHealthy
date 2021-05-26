package aplication.upn.BodyHealthy.Controller;

import aplication.upn.BodyHealthy.Dto.Message;
import aplication.upn.BodyHealthy.Dto.RutinaDto;
import aplication.upn.BodyHealthy.Model.Rutina;
import aplication.upn.BodyHealthy.Security.Model.Usuario;
import aplication.upn.BodyHealthy.Security.Service.UsuarioService;
import aplication.upn.BodyHealthy.Service.RutinaService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/rutina")
public class RutinaController {
    @Autowired
    RutinaService rutinaService;
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/list")
    public ResponseEntity<List<Rutina>> list() {
        List<Rutina> lista = rutinaService.getAll();
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Rutina> getRutina(@PathVariable("id") int id){
        if(!rutinaService.existsById(id)){
            return new ResponseEntity(new Message("Rutina not found"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(rutinaService.getRutina(id),HttpStatus.OK);
    }
    @GetMapping("/level/{level}")
    public ResponseEntity<List<Rutina>> getRutinaByLevel(@PathVariable("level") String level){
        if(!rutinaService.existsByNivel(level)){
            return new ResponseEntity(new Message("Rutina by level not found"),HttpStatus.NOT_FOUND);
        }
        List<Rutina> lista = rutinaService.getAllByLevel(level);
        return new ResponseEntity(lista,HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<Rutina> getRutinaByUser(@PathVariable("id") int id){
        if(!usuarioService.existsById(id)){
            return new ResponseEntity(new Message("User not found"),HttpStatus.NOT_FOUND);
        }
        Usuario usuario = usuarioService.getUsuario(id);
        if(!rutinaService.existsByUsuario(usuario)){
            return new ResponseEntity(new Message("Rutina by user not found"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(rutinaService.getAllByUser(usuario),HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createRutina(@RequestBody RutinaDto rutinaDto){
        if(rutinaDto.getNivel()!="Principiante"&&rutinaDto.getNivel()!="Intermedio"&&rutinaDto.getNivel()!="Avanzado"){
            return new ResponseEntity(new Message("Nivel invalido"), HttpStatus.BAD_REQUEST);
        }
        if(rutinaDto.getNombre().length()<4){
            return new ResponseEntity(new Message("Nombre invalido"), HttpStatus.BAD_REQUEST);
        }
        if(rutinaDto.getEjercicios().isEmpty()){
            return new ResponseEntity(new Message("Rutina vacia"), HttpStatus.BAD_REQUEST);
        }if(!usuarioService.existsById(rutinaDto.getIdUsuario())){
            return new ResponseEntity(new Message("Usuario no existe"), HttpStatus.BAD_REQUEST);
        }
        Rutina rutina = new Rutina();
        rutina.setNombre(rutinaDto.getNombre());
        rutina.setNivel(rutinaDto.getNivel());
        rutina.setEstado(0);
        rutina.setEjercicios(rutinaDto.getEjercicios());
        Usuario usuario = usuarioService.getUsuario(rutinaDto.getIdUsuario());
        rutina.setUsuario(usuario);
        rutinaService.save(rutina);
        return new ResponseEntity(new Message("Rutina creada"), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/default")
    public ResponseEntity<?> createRutinaDefault(@RequestBody RutinaDto rutinaDto){
        if(rutinaDto.getNivel()!="Principiante"&&rutinaDto.getNivel()!="Intermedio"&&rutinaDto.getNivel()!="Avanzado"){
            return new ResponseEntity(new Message("Nivel invalido"), HttpStatus.BAD_REQUEST);
        }
        if(rutinaDto.getNombre().length()<4){
            return new ResponseEntity(new Message("Nombre invalido"), HttpStatus.BAD_REQUEST);
        }
        if(rutinaDto.getEjercicios().isEmpty()){
            return new ResponseEntity(new Message("Rutina vacia"), HttpStatus.BAD_REQUEST);
        }if(!usuarioService.existsById(rutinaDto.getIdUsuario())){
            return new ResponseEntity(new Message("Usuario no existe"), HttpStatus.BAD_REQUEST);
        }
        Rutina rutina = new Rutina();
        rutina.setNombre(rutinaDto.getNombre());
        rutina.setNivel(rutinaDto.getNivel());
        rutina.setEstado(200);
        rutina.setEjercicios(rutinaDto.getEjercicios());
        Usuario usuario = usuarioService.getUsuario(rutinaDto.getIdUsuario());
        rutina.setUsuario(usuario);
        rutinaService.save(rutina);
        return new ResponseEntity(new Message("Rutina por defecto creada"), HttpStatus.OK);
    }

}
