package aplication.upn.BodyHealthy.Service;


import aplication.upn.BodyHealthy.Model.Usuario;
import aplication.upn.BodyHealthy.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }
    public Usuario getUsuario(int id){
        return usuarioRepository.getUsuario(id);
    }
    public List<Usuario> getByRol(int idRol){
        return usuarioRepository.getByRol(idRol);
    }
}
