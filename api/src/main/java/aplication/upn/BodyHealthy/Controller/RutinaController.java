package aplication.upn.BodyHealthy.Controller;

import aplication.upn.BodyHealthy.Dto.Message;
import aplication.upn.BodyHealthy.Dto.RutinaDto;
import aplication.upn.BodyHealthy.Model.Rutina;
import aplication.upn.BodyHealthy.Security.Model.Usuario;
import aplication.upn.BodyHealthy.Security.Service.UsuarioService;
import aplication.upn.BodyHealthy.Service.RutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

        List<Rutina> lista = rutinaService.findAllDefault();
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    @GetMapping("/listdefault/{level}")
    public ResponseEntity<List<Rutina>> listDefault(@PathVariable("level") String level) {
        if (!rutinaService.existsByNivel(level)) {
            return new ResponseEntity(new Message("Rutina by level not found"), HttpStatus.NOT_FOUND);
        }
        List<Rutina> lista = rutinaService.findDefaultByLevel(level);
        return new ResponseEntity(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rutina> getRutina(@PathVariable("id") int id) {
        if (!rutinaService.existsById(id)) {
            return new ResponseEntity(new Message("Rutina not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(rutinaService.getRutina(id), HttpStatus.OK);
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<List<Rutina>> getRutinaByLevel(@PathVariable("level") String level) {
        if (!rutinaService.existsByNivel(level)) {
            return new ResponseEntity(new Message("Rutina by level not found"), HttpStatus.NOT_FOUND);
        }
        List<Rutina> lista = rutinaService.getAllByLevel(level);
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    @GetMapping("/user/{id}")
    public ResponseEntity<Rutina> getRutinaByUser(@PathVariable("id") int id) {
        if (!usuarioService.existsById(id)) {
            return new ResponseEntity(new Message("User not found"), HttpStatus.NOT_FOUND);
        }
        Usuario usuario = usuarioService.getUsuario(id);
        if (!rutinaService.existsByUsuario(usuario)) {
            return new ResponseEntity(new Message("Rutina by user not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(rutinaService.getAllByUser(usuario), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createRutina(@RequestBody RutinaDto rutinaDto) {
        if (!rutinaDto.getNivel().equalsIgnoreCase("Principiante") &&
                !rutinaDto.getNivel().equalsIgnoreCase("Intermedio") && !rutinaDto.getNivel().equalsIgnoreCase("Avanzado")) {
            return new ResponseEntity(new Message("Nivel invalido"), HttpStatus.BAD_REQUEST);
        }
        if (rutinaDto.getNombre().length() < 4) {
            return new ResponseEntity(new Message("Nombre invalido"), HttpStatus.BAD_REQUEST);
        }
        if (rutinaDto.getEjercicios().isEmpty()) {
            return new ResponseEntity(new Message("Rutina vacia"), HttpStatus.BAD_REQUEST);
        }
        if (!usuarioService.existsByEmail(rutinaDto.getUserName())) {
            return new ResponseEntity(new Message("Usuario no existe"), HttpStatus.BAD_REQUEST);
        }
        Rutina rutina = new Rutina();
        rutina.setNombre(rutinaDto.getNombre());
        rutina.setNivel(rutinaDto.getNivel());
        rutina.setEstado(0);
        rutina.setEjercicios(rutinaDto.getEjercicios());
        Usuario usuario = usuarioService.findByCorreo(rutinaDto.getUserName());
        rutina.setUsuario(usuario);
        rutinaService.save(rutina);
        return new ResponseEntity(new Message("Rutina creada"), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/default")
    public ResponseEntity<?> createRutinaDefault(@RequestBody RutinaDto rutinaDto) {
        if (!rutinaDto.getNivel().equalsIgnoreCase("Principiante") &&
                !rutinaDto.getNivel().equalsIgnoreCase("Intermedio") && !rutinaDto.getNivel().equalsIgnoreCase("Avanzado")) {
            return new ResponseEntity(new Message("Nivel invalido"), HttpStatus.BAD_REQUEST);
        }
        if (rutinaDto.getNombre().length() < 4) {
            return new ResponseEntity(new Message("Nombre invalido"), HttpStatus.BAD_REQUEST);
        }
        if (rutinaDto.getEjercicios().isEmpty()) {
            return new ResponseEntity(new Message("Rutina vacia"), HttpStatus.BAD_REQUEST);
        }
        if (!usuarioService.existsByEmail(rutinaDto.getUserName())) {
            return new ResponseEntity(new Message("Usuario no existe"), HttpStatus.BAD_REQUEST);
        }
        Rutina rutina = new Rutina();
        rutina.setNombre(rutinaDto.getNombre());
        rutina.setNivel(rutinaDto.getNivel());
        rutina.setEstado(200);
        rutina.setEjercicios(rutinaDto.getEjercicios());
        Usuario usuario = usuarioService.findByCorreo(rutinaDto.getUserName());
        rutina.setUsuario(usuario);
        rutinaService.save(rutina);
        return new ResponseEntity(new Message("Rutina por defecto creada"), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRutina(@PathVariable("id") int id,@RequestBody RutinaDto rutinaDto) {
        if (!rutinaDto.getNivel().equalsIgnoreCase("Principiante") &&
                !rutinaDto.getNivel().equalsIgnoreCase("Intermedio") && !rutinaDto.getNivel().equalsIgnoreCase("Avanzado")) {
            return new ResponseEntity(new Message("Nivel invalido"), HttpStatus.BAD_REQUEST);
        }
        if (rutinaDto.getNombre().length() < 4) {
            return new ResponseEntity(new Message("Nombre invalido"), HttpStatus.BAD_REQUEST);
        }
        if (rutinaDto.getEjercicios().isEmpty()) {
            return new ResponseEntity(new Message("Rutina vacia"), HttpStatus.BAD_REQUEST);
        }
        if(!rutinaService.existsById(id)){
            return new ResponseEntity(new Message("Rutina not found"), HttpStatus.BAD_REQUEST);
        }
        if (!usuarioService.existsByEmail(rutinaDto.getUserName())) {
            return new ResponseEntity(new Message("Usuario no existe"), HttpStatus.BAD_REQUEST);
        }
        Rutina rutina = rutinaService.getRutina(id);
        Usuario usuario = usuarioService.findByCorreo(rutinaDto.getUserName());
        if(usuario.getRol().getCargo().equals("ADMIN")){
            rutina.setEstado(200);
        }else{
            rutina.setEstado(0);
        }
        rutina.setNombre(rutinaDto.getNombre());
        rutina.setNivel(rutinaDto.getNivel());
        rutina.setEjercicios(rutinaDto.getEjercicios());
        rutina.setUsuario(usuario);
        rutinaService.save(rutina);
        return new ResponseEntity(new Message("Rutina actualizada"), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRutina(@RequestParam(defaultValue = "-1")int idRutina,
                                          @RequestParam(defaultValue = "-1")int idUsuario){
        if(!rutinaService.existsById(idRutina)){
            return new ResponseEntity(new Message("Rutina no encontrada"), HttpStatus.NOT_FOUND);
        }
        if(!usuarioService.existsById(idUsuario)){
            return new ResponseEntity(new Message("Usuario no encontrado"), HttpStatus.NOT_FOUND);
        }
        Rutina rutina = rutinaService.getRutina(idRutina);
        if(rutina.getUsuario().getIdUsuario()!=idUsuario){
            return new ResponseEntity(new Message("La rutina no corresponde al usuario"), HttpStatus.NOT_FOUND);
        }
        rutinaService.delete(rutina);
        return new ResponseEntity(new Message("Rutina eliminada"), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("deletedefault/{id}")
    public ResponseEntity<?> deleteDefaultRoutine(@PathVariable("id") int id){
        if(!rutinaService.existsById(id)){
            return new ResponseEntity(new Message("Rutina no encontrada"), HttpStatus.NOT_FOUND);
        }
        rutinaService.delete(rutinaService.getRutina(id));
        return new ResponseEntity(new Message("Rutina elimnada"),HttpStatus.OK);
    }
}
