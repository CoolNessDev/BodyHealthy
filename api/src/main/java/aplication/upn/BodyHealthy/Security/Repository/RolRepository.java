package aplication.upn.BodyHealthy.Security.Repository;

import aplication.upn.BodyHealthy.Security.Model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    @Query(value="{call getRol(:in_idRol)}", nativeQuery = true)
    Rol getRol(@Param("in_idRol") Integer in_idRol);
}
