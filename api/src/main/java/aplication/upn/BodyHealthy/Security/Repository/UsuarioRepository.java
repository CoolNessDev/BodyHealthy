package aplication.upn.BodyHealthy.Security.Repository;

import aplication.upn.BodyHealthy.Security.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByCorreo(String Correo);
    Usuario findByCorreo(String correo);

}
