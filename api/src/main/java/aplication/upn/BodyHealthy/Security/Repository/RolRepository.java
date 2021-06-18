package aplication.upn.BodyHealthy.Security.Repository;

import aplication.upn.BodyHealthy.Security.Model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

}
