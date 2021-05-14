package aplication.upn.BodyHealthy.Controller;

import aplication.upn.BodyHealthy.Security.Model.Usuario;
import aplication.upn.BodyHealthy.Security.Service.RolService;
import aplication.upn.BodyHealthy.Security.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/test")
public class TestController {
    @Autowired
    RolService rolService;
    @Autowired
    UsuarioService usuarioService;
    @GetMapping("/usuarios")
    public ResponseEntity<Usuario> getUsuarios() {
        List<Usuario> lista = usuarioService.getAll();
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    @GetMapping("/usuarios/rol/{id}")
    public ResponseEntity<List<Usuario>> getById(@PathVariable("id") int id) {
        List<Usuario> roles = usuarioService.getByRol(id);
        return new ResponseEntity(roles, HttpStatus.OK);
    }
}
