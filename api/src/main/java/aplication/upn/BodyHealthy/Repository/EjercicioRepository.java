package aplication.upn.BodyHealthy.Repository;

import aplication.upn.BodyHealthy.Model.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer> {

}
