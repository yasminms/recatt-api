package br.com.recatt.service;

import br.com.recatt.domain.UserDTO;
import br.com.recatt.mapper.UserDTOMapper;
import br.com.recatt.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FindAllStudentsService {

    @Autowired
    private StudentRepository studentRepository;

    public List<UserDTO> findAll() {

        log.info("Listando os alunos");

        return studentRepository.findAll().stream().map(UserDTOMapper::apply).collect(Collectors.toList());
    }
}
