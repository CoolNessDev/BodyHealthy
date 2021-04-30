package aplication.upn.BodyHealthy.Service;

import aplication.upn.BodyHealthy.Model.Rutina;
import aplication.upn.BodyHealthy.Repository.RutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutinaService {
    @Autowired
    RutinaRepository rutinaRepository;

    public List<Rutina> getAll(){
        return rutinaRepository.findAll();
    }
    public Rutina getRutina(int id){
        return rutinaRepository.getRutina(id);
    }
    public Rutina insert(Rutina rutina) {
        return rutinaRepository.save(rutina);
    }
    public void delete(Rutina rutina) {
        rutinaRepository.delete(rutina);
    }
}
