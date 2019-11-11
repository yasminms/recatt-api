package br.com.recatt.service;

import br.com.recatt.entity.Subject;
import br.com.recatt.exception.EntityNotFoundException;
import br.com.recatt.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindSubjectByIdService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject findById(final Integer id) {

        return subjectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada. Tente novamente."));
    }
}
