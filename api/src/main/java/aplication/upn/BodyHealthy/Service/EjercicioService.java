package aplication.upn.BodyHealthy.Service;

import aplication.upn.BodyHealthy.Model.Ejercicio;
import aplication.upn.BodyHealthy.Repository.EjercicioRepository;
import aplication.upn.BodyHealthy.Repository.RutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EjercicioService {
    @Autowired
    EjercicioRepository ejercicioRepository;
    @Autowired
    RutinaRepository rutinaRepository;

    public List<Ejercicio> getAll(){
        return ejercicioRepository.findAll();
    }
    public Page<Ejercicio> pages(Pageable pageable){
        return ejercicioRepository.findAll(pageable);
    }
    public Optional<Ejercicio> getOne(int id){
        return ejercicioRepository.findById(id);
    }
    public void  save(Ejercicio producto){
        ejercicioRepository.save(producto);
    }
    public Ejercicio getEjercicio(int id){
        return ejercicioRepository.getOne(id);
    }
    public Ejercicio insert(Ejercicio ejercicio) {
        return ejercicioRepository.save(ejercicio);
    }
    public void delete(int id){
        ejercicioRepository.deleteById(id);
    }
    public boolean existsById(int id){
        return ejercicioRepository.existsById(id);
    }

    public List<Ejercicio> getByRutina(int id_rutina){
        /*return ejercicioRepository.findByRutina(id_rutina);*/
        List<Ejercicio> ejercicios = new ArrayList<>(rutinaRepository.findById(id_rutina).get().getEjercicios());
        return  ejercicios;
    }
    public List<Ejercicio> findEjercicio(String in_var){
        /*return ejercicioRepository.findEjercicio(in_var);*/
        List<Ejercicio> ejercicios = ejercicioRepository.findAll();
        List<Ejercicio> ejerciciosEncontrados = new ArrayList<>();
        for (Ejercicio e: ejercicios) {
            if(e.getNombre().toLowerCase().contains(in_var.toLowerCase())){
                ejerciciosEncontrados.add(e);
            }
        }
        return ejerciciosEncontrados;
    }

}
