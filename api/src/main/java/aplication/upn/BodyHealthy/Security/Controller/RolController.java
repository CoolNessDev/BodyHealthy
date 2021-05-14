package aplication.upn.BodyHealthy.Security.Controller;

import aplication.upn.BodyHealthy.Security.Model.Rol;
import aplication.upn.BodyHealthy.Security.Service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rol")
public class RolController {

    @Autowired
    RolService rolService;
    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
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