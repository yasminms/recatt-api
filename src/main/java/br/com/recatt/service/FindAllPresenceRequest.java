package br.com.recatt.service;

import br.com.recatt.domain.PresenceRequestDTO;
import br.com.recatt.entity.Teacher;
import br.com.recatt.mapper.PresenceRequestDTOMapper;
import br.com.recatt.repository.PresenceRequestRepository;
import br.com.recatt.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.recatt.config.security.CustomUserDetailsService.getUser;
import static java.util.Objects.nonNull;

@Service
@Slf4j
public class FindAllPresenceRequest {

    @Autowired
    private PresenceRequestRepository presenceRequestRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<PresenceRequestDTO> findAll() {

        final Teacher teacher = teacherRepository.findByEmail(getUser().getEmail());

        if (nonNull(teacher)) {

            return presenceRequestRepository.findAllRequests(teacher).stream()
                    .map(PresenceRequestDTOMapper::apply)
                    .collect(Collectors.toList());
        }

        log.info("Usuário administrador logado. Consultando todas as solicitações de correção de presença.");

        return presenceRequestRepository.findAllRequests().stream()
                .map(PresenceRequestDTOMapper::apply)
                .collect(Collectors.toList());
    }
}
