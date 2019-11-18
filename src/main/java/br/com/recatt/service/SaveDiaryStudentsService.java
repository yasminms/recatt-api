package br.com.recatt.service;

import br.com.recatt.domain.DiaryDTO;
import br.com.recatt.domain.DiaryStudentsRequest;
import br.com.recatt.entity.Diary;
import br.com.recatt.entity.Student;
import br.com.recatt.repository.DiaryRepository;
import br.com.recatt.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveDiaryStudentsService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FindDiaryByIdService findDiaryByIdService;

    @Autowired
    private FindAllDiariesService findAllDiariesService;

    public List<DiaryDTO> save(DiaryStudentsRequest request) {

        final Diary diary = findDiaryByIdService.findById(request.getId());
        final List<Student> students = studentRepository.findAllByEmailIn(request.getEmails());

        diary.setStudents(students);
        diaryRepository.save(diary);

        return findAllDiariesService.findAll();
    }
}
