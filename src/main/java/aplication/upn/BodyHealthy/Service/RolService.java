package aplication.upn.BodyHealthy.Service;

import aplication.upn.BodyHealthy.Model.Rol;
import aplication.upn.BodyHealthy.Model.Usuario;
import aplication.upn.BodyHealthy.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {
    @Autowired
    RolRepository rolRepository;

    public List<Rol> lista(){

        return rolRepository.findAll();
//        return rolRepository.findByCargo("Administrador");
    }
    public Rol getRol(int id){
//        rolRepository.save(new Rol("PRUEBA",true));
//        System.out.println("ROL AGREGADO");
        return rolRepository.getRol(id);
    }
}
