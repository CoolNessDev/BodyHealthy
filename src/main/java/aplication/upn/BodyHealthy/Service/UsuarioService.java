package aplication.upn.BodyHealthy.Service;


import aplication.upn.BodyHealthy.Model.Rutina;
import aplication.upn.BodyHealthy.Model.Usuario;
import aplication.upn.BodyHealthy.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(s);
        System.out.println("UNOOOOOOOOOOOOOOOOOO");
        if(usuario == null){
        System.out.println("DOSSSSSSSSSSSSSSSS");
            throw new UsernameNotFoundException("User Not Found");
        }
        System.out.println(usuario.getCorreo());
        return new aplication.upn.BodyHealthy.Security.CustomUserDetails(usuario);
    }
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }
    public Usuario getUsuario(int id){
        return usuarioRepository.getUsuario(id);
    }
    public Usuario insert(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    public List<Usuario> getByRol(int idRol){
        return usuarioRepository.getByRol(idRol);
    }

}
