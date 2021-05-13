package aplication.upn.BodyHealthy.Service;

import aplication.upn.BodyHealthy.Model.Comentario;
import aplication.upn.BodyHealthy.Repository.ComentarioRepository;
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
    public Comentario insert(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }
    public void delete(Comentario comentario) {
        comentarioRepository.delete(comentario);
    }
}
