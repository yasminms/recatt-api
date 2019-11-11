package br.com.recatt.repository;

import br.com.recatt.entity.Class;
import br.com.recatt.entity.Student;
import br.com.recatt.entity.TempPresence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempPresenceRepository extends JpaRepository<TempPresence, Integer> {

    List<TempPresence> findByStudentAndActualClass(Student student, Class actualClass);

}
