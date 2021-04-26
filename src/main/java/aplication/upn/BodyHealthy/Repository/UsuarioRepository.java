package aplication.upn.BodyHealthy.Repository;

import aplication.upn.BodyHealthy.Model.Rol;
import aplication.upn.BodyHealthy.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value="{call getUsuario(:in_idUsuario)}", nativeQuery = true)
    Usuario getUsuario(@Param("in_idUsuario") Integer in_idUsuario);

    @Query(value="{call getUsuarioByRol(:in_idRol)}", nativeQuery = true)
    List<Usuario> getByRol(@Param("in_idRol") Integer in_idRol);

}
