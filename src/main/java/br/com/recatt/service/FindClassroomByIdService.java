package br.com.recatt.service;

import br.com.recatt.entity.Classroom;
import br.com.recatt.exception.EntityNotFoundException;
import br.com.recatt.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindClassroomByIdService {

    @Autowired
    private ClassroomRepository classroomRepository;

    public Classroom findById(final Integer id) {

        return classroomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sala de aula não encontrada. Tente novamente."));
    }
}
