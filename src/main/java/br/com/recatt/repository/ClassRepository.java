package br.com.recatt.repository;

import br.com.recatt.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {

    @Query("FROM Class c " +
            "WHERE c.active = true " +
            "AND :currentTime BETWEEN c.startTime AND c.endTime")
    List<Class> findByActiveClasses(@Param("currentTime") LocalDateTime currentTime);
}
