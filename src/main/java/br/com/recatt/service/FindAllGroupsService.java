package br.com.recatt.service;

import br.com.recatt.entity.Group;
import br.com.recatt.repository.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindAllGroupsService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> findAll() {

        log.info("Listando as turmas");

        return groupRepository.findAll();
    }
}
