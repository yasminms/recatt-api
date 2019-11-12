package br.com.recatt.mapper;

import br.com.recatt.domain.PresenceDTO;
import br.com.recatt.entity.Presence;

public final class PresenceDTOMapper {

    public static PresenceDTO apply(final Presence presence) {

        return PresenceDTO.builder()
                .id(presence.getId())
                .classroom(presence.getActualClass().getDiary().getClassroom().getName())
                .dateTime(presence.getActualClass().getStartTime())
                .missedClasses(presence.getMissedClasses())
                .period(presence.getActualClass().getPeriod())
                .status(presence.getStatus())
                .subject(presence.getActualClass().getDiary().getSubject().getName())
                .build();
    }
}
