package br.com.recatt.service;

import br.com.recatt.domain.PresenceDTO;
import br.com.recatt.entity.Student;
import br.com.recatt.mapper.PresenceDTOMapper;
import br.com.recatt.repository.PresenceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.recatt.config.security.CustomUserDetailsService.getUser;

@Service
@Slf4j
public class FindAllPresencesService {

    @Autowired
    private PresenceRepository presenceRepository;

    @Autowired
    private FindStudentByEmailService findStudentByEmailService;

    public List<PresenceDTO> findAll() {

        final Student student = findStudentByEmailService.findByEmail(getUser().getEmail());
        return presenceRepository.findAllPersonalPresences(student).stream()
                .map(PresenceDTOMapper::apply)
                .collect(Collectors.toList());
    }
}
