package br.com.recatt.service;

import br.com.recatt.domain.PresenceRequestStatus;
import br.com.recatt.domain.PresenceStatus;
import br.com.recatt.entity.Presence;
import br.com.recatt.entity.PresenceRequest;
import br.com.recatt.exception.BadRequestException;
import br.com.recatt.repository.PresenceRepository;
import br.com.recatt.repository.PresenceRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.hibernate.type.IntegerType.ZERO;

@Service
@Slf4j
public class UpdatePresenceRequest {

    @Autowired
    private FindPresenceRequestByIdService findPresenceRequestByIdService;

    @Autowired
    private PresenceRepository presenceRepository;

    @Autowired
    private PresenceRequestRepository presenceRequestRepository;

    public void update(final Integer presenceRequestId, final boolean status) {

        final PresenceRequest presenceRequest = findPresenceRequestByIdService.findById(presenceRequestId);

        if (!presenceRequest.getStatus().equals(PresenceRequestStatus.AWAITING_REVIEW)) {

            log.error("Não é possivel atualizar essa solicitação de correção presença: {}", presenceRequest.getId());
            throw new BadRequestException("Você não pode atualizar essa solicitação de correção de presença. Tente outra diferente.");
        }

        final Presence presence = presenceRequest.getPresence();

        if (status) {

            presence.setMissedClasses(ZERO);
        }

        presenceRequest.setStatus(getPresenceRequestStatus(status));
        presence.setStatus(getPresenceStatus(presence));

        presenceRepository.save(presence);
        presenceRequestRepository.save(presenceRequest);
    }

    private PresenceStatus getPresenceStatus(final Presence presence) {

        final Integer missedClasses = presence.getMissedClasses();
        final Integer period = presence.getActualClass().getPeriod();

        if (ZERO.equals(missedClasses)) {

            return PresenceStatus.PRESENT;
        } else if (period.equals(missedClasses)) {

            return PresenceStatus.NOT_PRESENT;
        }

        return PresenceStatus.PARCIAL;
    }

    private PresenceRequestStatus getPresenceRequestStatus(final boolean status) {

        if (status) {

            return PresenceRequestStatus.ACCEPTED;
        }

        return PresenceRequestStatus.REJECTED;
    }
}
