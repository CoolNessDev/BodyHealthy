package aplication.upn.BodyHealthy.Repository;

import aplication.upn.BodyHealthy.Model.Comentario;
import aplication.upn.BodyHealthy.Model.Publicacion;
import aplication.upn.BodyHealthy.Security.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    boolean existsByUsuario(Usuario user);
    List<Comentario> findByUsuario(Usuario user);

    boolean existsByPublicacion(Publicacion publicacion);
    List<Comentario> findByPublicacion(Publicacion publicacion);
}
