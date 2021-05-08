package aplication.upn.BodyHealthy.Controller;

import aplication.upn.BodyHealthy.Model.Person;
import aplication.upn.BodyHealthy.Model.Rol;
import aplication.upn.BodyHealthy.Model.Usuario;
import aplication.upn.BodyHealthy.Service.RolService;
import aplication.upn.BodyHealthy.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/rol")
public class RolController {

    @Autowired
    RolService rolService;

    @GetMapping("/lista")
    public ResponseEntity<List<Rol>> listar() {
        List<Rol> lista = rolService.getAll();
        return new ResponseEntity(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getById(@PathVariable("id") int id) {
        Rol rol = rolService.get(id);
        return new ResponseEntity(rol, HttpStatus.OK);
    }

}