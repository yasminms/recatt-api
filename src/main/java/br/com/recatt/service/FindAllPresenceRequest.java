package br.com.recatt.service;

import br.com.recatt.domain.PresenceRequestDTO;
import br.com.recatt.entity.Teacher;
import br.com.recatt.mapper.PresenceRequestDTOMapper;
import br.com.recatt.repository.PresenceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.recatt.config.security.CustomUserDetailsService.getUser;

@Service
public class FindAllPresenceRequest {

    @Autowired
    private PresenceRequestRepository presenceRequestRepository;

    @Autowired
    private FindTeacherByEmailService findTeacherByEmailService;

    public List<PresenceRequestDTO> findAll() {

        final Teacher teacher = findTeacherByEmailService.findByEmail(getUser().getEmail());
        return presenceRequestRepository.findAllRequests(teacher).stream()
                .map(PresenceRequestDTOMapper::apply)
                .collect(Collectors.toList());
    }
}
