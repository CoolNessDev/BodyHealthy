package aplication.upn.BodyHealthy.Repository;

import aplication.upn.BodyHealthy.Model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {
    @Query(value="{call getPublicacion(:in_idPublicacion)}", nativeQuery = true)
    Publicacion getPublicacion(@Param("in_idPublicacion") Integer in_idPublicacion);
}
