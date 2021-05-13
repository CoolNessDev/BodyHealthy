package aplication.upn.BodyHealthy.Service;

import aplication.upn.BodyHealthy.Model.Rol;
import aplication.upn.BodyHealthy.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {
    @Autowired
    RolRepository rolRepository;

    public List<Rol> getAll() {
        return rolRepository.findAll();
    }

    public Rol get(int id) {
        return rolRepository.getRol(id);
    }

    public Rol insert(Rol rol) {
        return rolRepository.save(rol);
    }
    public void delete(Rol rol) {
        rolRepository.delete(rol);
    }
}
