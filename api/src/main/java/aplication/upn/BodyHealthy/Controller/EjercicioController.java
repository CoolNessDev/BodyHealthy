package aplication.upn.BodyHealthy.Controller;

import aplication.upn.BodyHealthy.Dto.Message;
import aplication.upn.BodyHealthy.Model.Ejercicio;
import aplication.upn.BodyHealthy.Service.EjercicioService;
import aplication.upn.BodyHealthy.Dto.EjercicioDto;
import aplication.upn.BodyHealthy.Service.MusculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ejercicio")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EjercicioController {

    @Autowired
    EjercicioService ejercicioService;

    @Autowired
    MusculoService musculoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Ejercicio>> listar() {
        List<Ejercicio> lista = ejercicioService.getAll();
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<Page<Ejercicio>> paginas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String order,
            @RequestParam(defaultValue = "true") boolean asc
    ){
        Page<Ejercicio> ejercicios = ejercicioService.pages(
                PageRequest.of(page, size, Sort.by(order)));
        if(!asc)
            ejercicios = ejercicioService.pages(
                    PageRequest.of(page, size, Sort.by(order).descending()));
        return new ResponseEntity<Page<Ejercicio>>(ejercicios, HttpStatus.OK);
    }
    @GetMapping("/find")
    public ResponseEntity<List<Ejercicio>> findEjercicio(@RequestParam(defaultValue = "")String var){
        List<Ejercicio> lista = ejercicioService.findEjercicio(var);
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    @GetMapping("/musculo/{id}")
    public ResponseEntity<List<Ejercicio>> listarByMusculo(@PathVariable("id") int id) {
        List<Ejercicio> lista = musculoService.getAllEjeciciosByMusculos(id);
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    @GetMapping("/rutina/{id}")
    public ResponseEntity<List<Ejercicio>> listarByRutina(@PathVariable("id") int id) {
        List<Ejercicio> lista = ejercicioService.getByRutina(id);
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ejercicio> getById(@PathVariable("id") int id) {
        if (!ejercicioService.existsById(id))
            return new ResponseEntity(new Message("Exercise not found"), HttpStatus.NOT_FOUND);
        Ejercicio ejercicio = ejercicioService.getEjercicio(id);
        return new ResponseEntity(ejercicio, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EjercicioDto ejercicioDto) {
        if (ejercicioDto.getNombre().equals(""))
            return new ResponseEntity(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (ejercicioDto.getSeries() < 0)
            return new ResponseEntity(new Message("el valor debe ser mayor a 0"), HttpStatus.BAD_REQUEST);
        Ejercicio ejercicio = new Ejercicio(ejercicioDto.getNombre(), ejercicioDto.getDuracion(), ejercicioDto.getSeries(), ejercicioDto.getRepeticiones(), ejercicioDto.getImagen(),
                ejercicioDto.getDescripcion(), ejercicioDto.getDescanso(), ejercicioDto.getMusculos());
        ejercicioService.insert(ejercicio);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody EjercicioDto ejercicioDto) {
        if (!ejercicioService.existsById(id))
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        if (ejercicioDto.getNombre().length() < 4)
            return new ResponseEntity(new Message("el nombre requiere mas de 4 caracteres"), HttpStatus.BAD_REQUEST);
        if (ejercicioDto.getSeries() < 0)
            return new ResponseEntity(new Message("el valor debe ser mayor a 0"), HttpStatus.BAD_REQUEST);
        Ejercicio ejercicio = ejercicioService.getOne(id).get();
        ejercicio.setNombre(ejercicioDto.getNombre());
        ejercicio.setDuracion(ejercicioDto.getDuracion());
        ejercicio.setSeries(ejercicioDto.getSeries());
        ejercicio.setRepeticiones(ejercicioDto.getRepeticiones());
        ejercicio.setImagen(ejercicioDto.getImagen());
        ejercicio.setDescripcion(ejercicioDto.getDescripcion());
        ejercicio.setDescanso(ejercicioDto.getDescanso());
        ejercicio.setMusculos(ejercicioDto.getMusculos());
        ejercicioService.save(ejercicio);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        ejercicioService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}