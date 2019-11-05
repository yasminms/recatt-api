package br.com.recatt.service;

import br.com.recatt.entity.Classroom;
import br.com.recatt.repository.ClassroomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindAllClassroomsService {

    @Autowired
    private ClassroomRepository classroomRepository;

    public List<Classroom> findAll() {

        log.info("Listando as salas de aula");

        return classroomRepository.findAll();
    }
}
