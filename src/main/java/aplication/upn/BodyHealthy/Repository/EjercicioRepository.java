package aplication.upn.BodyHealthy.Repository;

import aplication.upn.BodyHealthy.Model.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer> {
    @Query(value="{call getEjercicio(:in_idEjercicio)}", nativeQuery = true)
    Ejercicio getEjercicio(@Param("in_idEjercicio") Integer in_idEjercicio);
}
