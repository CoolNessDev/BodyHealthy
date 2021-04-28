package aplication.upn.BodyHealthy.Repository;

import aplication.upn.BodyHealthy.Model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    @Query(value="{call getComentario(:in_idComentario)}", nativeQuery = true)
    Comentario getComentario(@Param("in_idComentario") Integer in_idComentario);
}
