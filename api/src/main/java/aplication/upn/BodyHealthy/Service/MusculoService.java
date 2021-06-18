package aplication.upn.BodyHealthy.Service;

import aplication.upn.BodyHealthy.Model.Ejercicio;
import aplication.upn.BodyHealthy.Model.Musculo;
import aplication.upn.BodyHealthy.Repository.EjercicioRepository;
import aplication.upn.BodyHealthy.Repository.MusculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusculoService {
    @Autowired
    MusculoRepository musculoRepository;
    @Autowired
    EjercicioRepository ejercicioRepository;

    public List<Musculo> getAll(){
        return musculoRepository.findAll();
    }
    public List<Ejercicio> getAllEjeciciosByMusculos(int id){
        /*return ejercicioRepository.findByMusculos(id);*/
        List<Ejercicio> ejercicios = new ArrayList<>(musculoRepository.findById(id).get().getEjercicios());
        return ejercicios;
    }
    public Musculo getMusculo(int id){
        return musculoRepository.getOne(id);
    }
    public Musculo insert(Musculo musculo) {
        return musculoRepository.save(musculo);
    }
    public void delete(Musculo musculo) {
        musculoRepository.delete(musculo);
    }
}
