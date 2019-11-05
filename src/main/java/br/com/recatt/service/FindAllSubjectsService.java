package br.com.recatt.service;

import br.com.recatt.entity.Subject;
import br.com.recatt.repository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindAllSubjectsService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> findAll() {

        log.info("Listando as disciplinas");

        return subjectRepository.findAll();
    }
}
