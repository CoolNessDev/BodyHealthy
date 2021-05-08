package aplication.upn.BodyHealthy.Controller;

import aplication.upn.BodyHealthy.Model.Ejercicio;
import aplication.upn.BodyHealthy.Repository.EjercicioRepository;
import aplication.upn.BodyHealthy.Service.EjercicioService;
import aplication.upn.BodyHealthy.dto.EjercicioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ejercicio")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EjercicioController {

    @Autowired
    EjercicioService ejercicioService;
    @Autowired
    EjercicioRepository ejercicioRepository;

    @GetMapping("/lista")
    public ResponseEntity<List<Ejercicio>> listar() {
        System.out.println("HOLAAAAAAAAAAAAAAAAAAA");
        List<Ejercicio> lista = ejercicioService.getAll();
        return new ResponseEntity(lista, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EjercicioDto ejercicioDto){
//        if(StringUtils.isBlank(ejercicioDto.getNombre()))
//            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
//        if(productoDto.getPrecio()==null || ejercicioDto.getPrecio()<0 )
//            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
//        if(productoService.existsByNombre(productoDto.getNombre()))
//            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        System.out.println("POST");
        Ejercicio ejercicio = new Ejercicio(ejercicioDto.getNombre(), ejercicioDto.getDuracion(),ejercicioDto.getSeries(),ejercicioDto.getRepeticiones(),ejercicioDto.getImagen(),
                ejercicioDto.getDescripcion(), ejercicioDto.getDescanso());
        ejercicioService.insert(ejercicio);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Ejercicio> getById(@PathVariable("id") int id) {
//        Ejercicio ejercicio = ejercicioService.get(id);
//        return new ResponseEntity(ejercicio, HttpStatus.OK);
//    }
}