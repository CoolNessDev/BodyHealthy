package aplication.upn.BodyHealthy.Service;

import aplication.upn.BodyHealthy.Model.Publicacion;
import aplication.upn.BodyHealthy.Repository.PublicacionRepository;
import aplication.upn.BodyHealthy.Security.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacionService {
    @Autowired
    PublicacionRepository publicacionRepository;

    public List<Publicacion> getAll(){
        return publicacionRepository.findAll();
    }
    public Page<Publicacion> getAllPageable(Pageable pageable){
        return publicacionRepository.findAll(pageable);
    }
    public List<Publicacion> findByUsuario(Usuario usuario){
        return publicacionRepository.findByUsuario(usuario);
    }
    public boolean existByUsuario(Usuario usuario){
        return publicacionRepository.existsByUsuario(usuario);
    }
    public boolean existById(int id){
        return publicacionRepository.existsById(id);
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
    public void delete(int id){
        publicacionRepository.deleteById(id);
    }
}
