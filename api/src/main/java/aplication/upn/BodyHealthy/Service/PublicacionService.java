package aplication.upn.BodyHealthy.Service;

import aplication.upn.BodyHealthy.Model.Publicacion;
import aplication.upn.BodyHealthy.Repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacionService {
    @Autowired
    PublicacionRepository publicacionRepository;

    public List<Publicacion> getAll(){
        return publicacionRepository.findAll();
    }
    public Publicacion getPublicacion(int id){
        return publicacionRepository.getPublicacion(id);
    }
    public Publicacion insert(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }
    public void delete(Publicacion publicacion) {
        publicacionRepository.delete(publicacion);
    }
}
