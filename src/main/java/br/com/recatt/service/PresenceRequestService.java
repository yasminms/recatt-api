package br.com.recatt.service;

import br.com.recatt.domain.PresenceRequestStatus;
import br.com.recatt.entity.Presence;
import br.com.recatt.entity.PresenceRequest;
import br.com.recatt.entity.Student;
import br.com.recatt.exception.BadRequestException;
import br.com.recatt.repository.PresenceRepository;
import br.com.recatt.repository.PresenceRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.recatt.config.security.CustomUserDetailsService.getUser;
import static br.com.recatt.domain.PresenceStatus.AWAITING_REVIEW;
import static br.com.recatt.domain.PresenceStatus.PRESENT;

@Service
@Slf4j
public class PresenceRequestService {

    @Autowired
    private PresenceRequestRepository presenceRequestRepository;

    @Autowired
    private PresenceRepository presenceRepository;

    @Autowired
    private FindStudentByEmailService findStudentByEmailService;

    @Autowired
    private FindPresenceByIdService findPresenceByIdService;

    public void request(final Integer presenceId) {

        final Student student = findStudentByEmailService.findByEmail(getUser().getEmail());
        final Presence presence = findPresenceByIdService.findById(presenceId);

        if (!presence.getStudent().getEmail().equals(student.getEmail())) {

            log.error("Não é possível solicitar correção de presença para outro aluno: {}", student.getEmail());
            throw new BadRequestException("Só é possível solicitar correção de suas próprias presenças. Tente novamente.");
        }

        if (PRESENT.equals(presence.getStatus()) || AWAITING_REVIEW.equals(presence.getStatus())) {

            throw new BadRequestException("Você não pode solicitar uma correção dessa presença. Tente outra diferente.");
        }


        final PresenceRequest presenceRequest = new PresenceRequest();

        presenceRequest.setPresence(presence);
        presenceRequest.setStatus(PresenceRequestStatus.AWAITING_REVIEW);

        presence.setStatus(AWAITING_REVIEW);
        presenceRepository.save(presence);

        presenceRequestRepository.save(presenceRequest);
    }
}
