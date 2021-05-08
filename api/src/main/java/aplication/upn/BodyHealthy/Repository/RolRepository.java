package aplication.upn.BodyHealthy.Repository;

import aplication.upn.BodyHealthy.Model.Rol;
import aplication.upn.BodyHealthy.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
//  List<Rol> findByCargo(String cargo);
    @Query(value="{call getRol(:in_idRol)}", nativeQuery = true)
    Rol getRol(@Param("in_idRol") Integer in_idRol);
}
