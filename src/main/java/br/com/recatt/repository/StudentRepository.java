package br.com.recatt.repository;

import br.com.recatt.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Student findByEmail(String email);

    List<Student> findAllByEmailIn(List<String> emails);
}
