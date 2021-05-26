package aplication.upn.BodyHealthy.Repository;

import aplication.upn.BodyHealthy.Model.Rutina;
import aplication.upn.BodyHealthy.Security.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutinaRepository extends JpaRepository<Rutina, Integer> {
    @Query(value="{call getRutina(:in_idRutina)}", nativeQuery = true)
    Rutina getRutina(@Param("in_idRutina") Integer in_idRutina);

    boolean existsByNivel(String nivel);
    boolean existsByUsuario(Usuario user);

    List<Rutina> findByNivel(String level);
    List<Rutina> findByUsuario(Usuario user);
}
