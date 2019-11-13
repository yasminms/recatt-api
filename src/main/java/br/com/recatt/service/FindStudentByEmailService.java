package br.com.recatt.service;

import br.com.recatt.entity.Student;
import br.com.recatt.exception.EntityNotFoundException;
import br.com.recatt.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class FindStudentByEmailService {

    @Autowired
    private StudentRepository studentRepository;

    public Student findByEmail(final String email) {

        return ofNullable(studentRepository.findByEmail(email)).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado. Tente novamente."));
    }

}
