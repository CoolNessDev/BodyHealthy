package aplication.upn.BodyHealthy.Repository;

import aplication.upn.BodyHealthy.Model.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer> {
    @Query(value="{call getEjercicio(:in_idEjercicio)}", nativeQuery = true)
    Ejercicio getEjercicio(@Param("in_idEjercicio") Integer in_idEjercicio);

    @Procedure(name = "DeleteEjercicio")
    Ejercicio deleteEjercicio(@Param("id_ejer") Integer id_ejer);

    @Query(value="{call GetEjerciciosByMusculo(:in_in_idMusculo)}", nativeQuery = true)
    List<Ejercicio> findByMusculos(@Param("in_in_idMusculo") Integer in_in_idMusculo);
}
