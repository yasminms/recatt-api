package br.com.recatt.repository;

import br.com.recatt.entity.Presence;
import br.com.recatt.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Integer> {

    @Query("FROM Presence p " +
            "WHERE p.student = :student " +
            "ORDER BY p.actualClass.startTime DESC")
    List<Presence> findAllPersonalPresences(@Param("student") Student student);
}
