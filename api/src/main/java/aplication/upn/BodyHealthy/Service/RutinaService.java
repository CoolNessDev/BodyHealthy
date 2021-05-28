package aplication.upn.BodyHealthy.Service;

import aplication.upn.BodyHealthy.Model.Rutina;
import aplication.upn.BodyHealthy.Repository.RutinaRepository;
import aplication.upn.BodyHealthy.Security.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutinaService {
    @Autowired
    RutinaRepository rutinaRepository;

    public List<Rutina> findAllDefault() {
        return rutinaRepository.findAllDefault(200);
    }
    public List<Rutina> findDefaultByLevel(String level) {
        return rutinaRepository.findDefaultByLevel(level,200);
    }

    public Rutina getRutina(int id) {
        return rutinaRepository.getRutina(id);
    }
    public List<Rutina> getAllByLevel(String level) {
        return rutinaRepository.findByNivel(level);
    }
    public List<Rutina> getAllByUser(Usuario user) {
        return rutinaRepository.findByUsuario(user);
    }
    public boolean existsById(int id) {
        return rutinaRepository.existsById(id);
    }
    public boolean existsByNivel(String level) {
        return rutinaRepository.existsByNivel(level);
    }
    public boolean existsByUsuario(Usuario usuario) {
        return rutinaRepository.existsByUsuario(usuario);
    }

    public Rutina save(Rutina rutina) {
        return rutinaRepository.save(rutina);
    }

    public void delete(Rutina rutina) {
        rutinaRepository.delete(rutina);
    }
}
