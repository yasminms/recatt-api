package br.com.recatt.service;

import br.com.recatt.domain.ClassRegisterRequest;
import br.com.recatt.entity.Class;
import br.com.recatt.entity.Classroom;
import br.com.recatt.entity.Diary;
import br.com.recatt.entity.Group;
import br.com.recatt.entity.Subject;
import br.com.recatt.entity.Teacher;
import br.com.recatt.repository.ClassRepository;
import br.com.recatt.repository.DiaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.recatt.config.security.CustomUserDetailsService.getUser;

@Service
@Slf4j
public class SaveClassService {

    @Value("${school.classes.start}")
    private String startClasses;

    @Value("${school.classes.end}")
    private String endClasses;

    @Value("${school.period.duration}")
    private Integer periodDuration;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private FindGroupByIdService groupService;

    @Autowired
    private FindSubjectByIdService subjectService;

    @Autowired
    private FindClassroomByIdService classroomService;

    @Autowired
    private FindTeacherByEmailService teacherService;

    @Autowired
    private ReplicateClassService replicateClassService;

    @Transactional
    public void save(ClassRegisterRequest request) {

        log.info("Inserindo diário e aulas para o ano letivo referente à: {}", request.getYear());

        final Group group = groupService.findById(request.getGroupId());
        final Classroom classroom = classroomService.findById(request.getClassroomId());
        final Subject subject = subjectService.findById(request.getSubjectId());
        final Teacher teacher = teacherService.findByEmail(getUser().getEmail());

        final Diary diary = new Diary();
        diary.setGroup(group);
        diary.setClassroom(classroom);
        diary.setSubject(subject);
        diary.setTeacher(teacher);
        diary.setYear(request.getYear());

        final Diary diaryEntity = diaryRepository.saveAndFlush(diary);

        final List<Class> classes = request.getInformation().stream()
                .map(classInfo -> replicateClassService.replicate(classInfo, diaryEntity, request.getTolerance()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        classRepository.saveAll(classes);
    }

}
