package aplication.upn.BodyHealthy.Controller;

import aplication.upn.BodyHealthy.Dto.Message;
import aplication.upn.BodyHealthy.Security.Model.Usuario;
import aplication.upn.BodyHealthy.Security.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @GetMapping("/{email}")
    public ResponseEntity<Usuario> getUser(@PathVariable("email") String email){
        if(!usuarioService.existsByEmail(email)){
            return new ResponseEntity(new Message("User not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(usuarioService.getByEmail(email),HttpStatus.OK);

    }
}
