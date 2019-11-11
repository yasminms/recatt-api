package br.com.recatt.service;

import br.com.recatt.entity.Group;
import br.com.recatt.exception.EntityNotFoundException;
import br.com.recatt.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindGroupByIdService {

    @Autowired
    private GroupRepository groupRepository;

    public Group findById(final Integer id) {

        return groupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Turma não encontrada. Tente novamente."));
    }
}
