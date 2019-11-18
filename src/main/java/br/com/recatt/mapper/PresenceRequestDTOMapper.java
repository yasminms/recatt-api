package br.com.recatt.mapper;

import br.com.recatt.domain.PresenceRequestDTO;
import br.com.recatt.entity.PresenceRequest;
import br.com.recatt.entity.TempPresence;

import java.util.List;
import java.util.stream.Collectors;

public final class PresenceRequestDTOMapper {

    public static PresenceRequestDTO apply(final PresenceRequest presenceRequest) {

        final List<TempPresence> tempPresences = presenceRequest.getPresence().getActualClass().getTempPresences().stream()
                .filter(tempPresence -> tempPresence.getStudent().getEmail().equals(presenceRequest.getPresence().getStudent().getEmail()))
                .collect(Collectors.toList());

        return PresenceRequestDTO.builder()
                .id(presenceRequest.getId())
                .capture(tempPresences.size())
                .detection((int) tempPresences.stream().filter(TempPresence::isPresent).count())
                .group(presenceRequest.getPresence().getActualClass().getDiary().getGroup().getName())
                .startTime(presenceRequest.getPresence().getActualClass().getStartTime())
                .student(UserDTOMapper.apply(presenceRequest.getPresence().getStudent()))
                .subject(presenceRequest.getPresence().getActualClass().getDiary().getSubject().getName())
                .build();
    }
}
