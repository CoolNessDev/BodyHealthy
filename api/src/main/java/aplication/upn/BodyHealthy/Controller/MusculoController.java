package aplication.upn.BodyHealthy.Controller;

import aplication.upn.BodyHealthy.Model.Musculo;
import aplication.upn.BodyHealthy.Service.MusculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/musculo")
public class MusculoController {
    @Autowired
    MusculoService musculoService;
    @GetMapping("/list")
    public ResponseEntity<Musculo> getMusculos() {
        List<Musculo> lista = musculoService.getAll();
        return new ResponseEntity(lista, HttpStatus.OK);
    }
}
