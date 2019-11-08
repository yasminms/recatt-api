package br.com.recatt.service;

import br.com.recatt.entity.Teacher;
import br.com.recatt.exception.EntityNotFoundException;
import br.com.recatt.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class FindTeacherByEmailService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher findByEmail(final String email) {

        return ofNullable(teacherRepository.findByEmail(email)).orElseThrow(() -> new EntityNotFoundException("Professor não encontrado. Tente novamente."));
    }
}
