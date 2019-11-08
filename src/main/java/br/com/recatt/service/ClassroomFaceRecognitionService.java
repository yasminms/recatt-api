package br.com.recatt.service;

import br.com.recatt.domain.PresentStudent;
import br.com.recatt.entity.Class;
import br.com.recatt.entity.Student;
import br.com.recatt.entity.TempPresence;
import br.com.recatt.repository.TempPresenceRepository;
import br.com.recatt.utils.ParseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ClassroomFaceRecognitionService extends AbstractFaceRecognitionApiService {

    @Value("${face-recognition.interval}")
    private Integer attendanceInterval;

    @Value("${face-recognition.min-distance-allowed}")
    private Double maxDistance;

    @Autowired
    private FindByActiveClasses findByActiveClasses;

    @Autowired
    private FindEncodingsFromClassroomRestService classroomEncodingsRestService;

    @Autowired
    private FinishClassService finishClassService;

    @Autowired
    private TempPresenceRepository tempPresenceRepository;

    @Transactional
    public void get() {

        final LocalDateTime currentTime = LocalDateTime.now();

        final List<Class> classes = findByActiveClasses.findAll(currentTime);

        for (Class actualClass : classes) {

            final List<PresentStudent> students = classroomEncodingsRestService.find(actualClass).getStudents();
            final List<Student> classStudents = actualClass.getDiary().getStudents();

            final List<TempPresence> presentStudents = classStudents.stream()
                    .filter(student -> students.stream()
                            .filter(this::verifyStudent)
                            .anyMatch(presentStudent -> presentStudent.getEmail().equals(student.getEmail())))
                    .map(student -> new TempPresence(student, actualClass, currentTime, true))
                    .collect(Collectors.toList());

            final List<TempPresence> notPresentStudents = classStudents.stream()
                    .filter(student -> students.stream()
                            .filter(this::verifyStudent)
                            .noneMatch(presentStudent -> presentStudent.getEmail().equals(student.getEmail())))
                    .map(student -> new TempPresence(student, actualClass, currentTime, false))
                    .collect(Collectors.toList());

            final List<TempPresence> tempPresences = Stream.concat(presentStudents.stream(), notPresentStudents.stream()).collect(Collectors.toList());

            tempPresenceRepository.saveAll(tempPresences);

            final boolean isLastClassUpdate = currentTime.plusMinutes(attendanceInterval).isAfter(actualClass.getEndTime());

            if (isLastClassUpdate) {

                finishClassService.finish(classStudents, actualClass);
            }
        }

    }

    private boolean verifyStudent(final PresentStudent presentStudent) {

        return ParseUtils.round(presentStudent.getDistance(), 1) <= maxDistance;
    }

}
