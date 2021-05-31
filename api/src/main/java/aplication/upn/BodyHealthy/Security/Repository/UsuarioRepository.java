package aplication.upn.BodyHealthy.Security.Repository;

import aplication.upn.BodyHealthy.Security.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value="{call getUsuario(:in_idUsuario)}", nativeQuery = true)
    Usuario getUsuario(@Param("in_idUsuario") Integer in_idUsuario);

    @Query(value="{call getUsuarioByEmail(:in_email)}", nativeQuery = true)
    Usuario getByEmail(@Param("in_email") String in_email);

    @Query(value="{call getUsuarioByRol(:in_idRol)}", nativeQuery = true)
    List<Usuario> getByRol(@Param("in_idRol") Integer in_idRol);
    boolean existsByCorreo(String Correo);
    Usuario findByCorreo(String correo);

}
