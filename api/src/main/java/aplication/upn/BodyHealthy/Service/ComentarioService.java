package aplication.upn.BodyHealthy.Service;

import aplication.upn.BodyHealthy.Model.Comentario;
import aplication.upn.BodyHealthy.Model.Publicacion;
import aplication.upn.BodyHealthy.Repository.ComentarioRepository;
import aplication.upn.BodyHealthy.Security.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {
    @Autowired
    ComentarioRepository comentarioRepository;

    public List<Comentario> getAll(){
        return comentarioRepository.findAll();
    }
    public Comentario getComentario(int id){
        return comentarioRepository.getComentario(id);
    }

    public boolean existsByPublicacion(Publicacion publicacion){
        return comentarioRepository.existsByPublicacion(publicacion);
    }public List<Comentario> findByPublicacion(Publicacion publicacion){
        return comentarioRepository.findByPublicacion(publicacion);
    }
    public boolean existByUsuario(Usuario usuario){
        return comentarioRepository.existsByUsuario(usuario);
    }
    public List<Comentario> findByUsuario(Usuario usuario){
        return comentarioRepository.findByUsuario(usuario);
    }
    public boolean existsById(int id){
        return comentarioRepository.existsById(id);
    }
    public boolean existById(int id){
        return comentarioRepository.existsById(id);
    }
    public Comentario insert(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }
    public void delete(Comentario comentario) {
        comentarioRepository.delete(comentario);
    }
}
