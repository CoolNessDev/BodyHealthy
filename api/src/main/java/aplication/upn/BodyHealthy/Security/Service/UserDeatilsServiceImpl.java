package aplication.upn.BodyHealthy.Security.Service;

import aplication.upn.BodyHealthy.Security.Model.CustomUserDetails;
import aplication.upn.BodyHealthy.Security.Model.Usuario;
import aplication.upn.BodyHealthy.Security.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDeatilsServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioService usuarioService;
    
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.findByCorreo(s);
        if(usuario == null){
            throw new UsernameNotFoundException("User Not Found");
        }
        System.out.println(usuario.getCorreo());
        return new CustomUserDetails(usuario);
    }
//    @Override
//    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
//        Usuario usuario = usuarioService.findByCorreo(nombreUsuario);
//        return new CustomUserDetails(usuario);
//    }
}
