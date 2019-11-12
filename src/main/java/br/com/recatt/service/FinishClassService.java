package br.com.recatt.service;

import br.com.recatt.domain.PresenceStatus;
import br.com.recatt.entity.Class;
import br.com.recatt.entity.Presence;
import br.com.recatt.entity.Student;
import br.com.recatt.entity.TempPresence;
import br.com.recatt.repository.PresenceRepository;
import br.com.recatt.repository.TempPresenceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.com.recatt.domain.PresenceStatus.NOT_PRESENT;
import static br.com.recatt.domain.PresenceStatus.PARCIAL;
import static br.com.recatt.domain.PresenceStatus.PRESENT;
import static br.com.recatt.utils.ParseUtils.getPercentage;
import static org.hibernate.type.IntegerType.ZERO;

@Service
@Slf4j
public class FinishClassService {

    @Value("${attendance.full-presence}")
    private Integer fullPresence;

    @Value("${attendance.parcial-presence}")
    private Integer parcialPresence;

    @Autowired
    private PresenceRepository presenceRepository;

    @Autowired
    private TempPresenceRepository tempPresenceRepository;

    public void finish(final List<Student> classStudents, final Class actualClass) {

        log.info("Atribuindo presenças e faltas para a aula: {}", actualClass.getId());

        final List<Presence> studentPresences = new ArrayList<>();
        Presence presence;

        for (Student student : classStudents) {

            final List<TempPresence> studentTemporaryPresences = tempPresenceRepository.findByStudentAndActualClass(student, actualClass);

            long recognitions = studentTemporaryPresences.stream().filter(TempPresence::isPresent).count();

            final Double presencePercentage = getPercentage(recognitions, studentTemporaryPresences.size());

            presence = new Presence();
            presence.setActualClass(actualClass);
            presence.setStudent(student);
            presence.setMissedClasses(getTotalMissedClasses(presencePercentage, actualClass));
            presence.setStatus(getPresenceStatus(presence));

            studentPresences.add(presence);
        }

        presenceRepository.saveAll(studentPresences);
    }

    private Integer getTotalMissedClasses(final Double presencePercentage, final Class actualClass) {

        Integer missedClasses = actualClass.getPeriod();

        if (presencePercentage >= fullPresence) {
            missedClasses = ZERO;
        } else if (presencePercentage >= parcialPresence) {
            missedClasses = actualClass.getPeriod() / 2;
        }

        return missedClasses;
    }

    private PresenceStatus getPresenceStatus(final Presence presence) {

        final Integer missedClasses = presence.getMissedClasses();
        final Integer period = presence.getActualClass().getPeriod();

        if (ZERO.equals(missedClasses)) {

            return PRESENT;
        } else if (period.equals(missedClasses)) {

            return NOT_PRESENT;
        }

        return PARCIAL;
    }
}
