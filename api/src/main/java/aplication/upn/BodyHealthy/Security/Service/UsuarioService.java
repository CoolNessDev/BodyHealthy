package aplication.upn.BodyHealthy.Security.Service;


import aplication.upn.BodyHealthy.Security.Model.Usuario;
import aplication.upn.BodyHealthy.Security.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuario(int id) {
        return usuarioRepository.getOne(id);
    }

    public Usuario insert(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

   /* public List<Usuario> getByRol(int idRol) {
        return usuarioRepository.getByRol(idRol);
    }
    public Usuario getByEmail(String email) {
        return usuarioRepository.getByEmail(email);
    }*/

    public Usuario findByCorreo(String s) {
        return usuarioRepository.findByCorreo(s);
    }

    public boolean existsById(int id) {
        return usuarioRepository.existsById(id);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByCorreo(email);
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }


}
