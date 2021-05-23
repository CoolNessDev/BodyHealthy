package aplication.upn.BodyHealthy.Service;

import aplication.upn.BodyHealthy.Model.Ejercicio;
import aplication.upn.BodyHealthy.Repository.EjercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EjercicioService {
    @Autowired
    EjercicioRepository ejercicioRepository;

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
        return ejercicioRepository.getEjercicio(id);
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
    public List<Ejercicio> getByMusculo(int id_musculo){
        return ejercicioRepository.findByMusculos(id_musculo);
    }

}
