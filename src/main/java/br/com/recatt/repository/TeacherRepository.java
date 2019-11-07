package br.com.recatt.repository;

import br.com.recatt.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

    Teacher findByEmail(String email);
}
