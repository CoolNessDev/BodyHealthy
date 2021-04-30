package aplication.upn.BodyHealthy.Repository;

import aplication.upn.BodyHealthy.Model.Musculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MusculoRepository extends JpaRepository<Musculo, Integer> {
    @Query(value="{call getMusculo(:in_idMusculo)}", nativeQuery = true)
    Musculo getMusculo(@Param("in_idMusculo") Integer in_idMusculo);
}
