package aplication.upn.BodyHealthy.Service;

import aplication.upn.BodyHealthy.Model.Ejercicio;
import aplication.upn.BodyHealthy.Repository.EjercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjercicioService {
    @Autowired
    EjercicioRepository ejercicioRepository;

    public List<Ejercicio> getAll(){
        return ejercicioRepository.findAll();
    }
    public Ejercicio getEjercicio(int id){
        return ejercicioRepository.getEjercicio(id);
    }
    public Ejercicio insert(Ejercicio ejercicio) {
        return ejercicioRepository.save(ejercicio);
    }
    public void delete(Ejercicio ejercicio) {
        ejercicioRepository.delete(ejercicio);
    }
}
