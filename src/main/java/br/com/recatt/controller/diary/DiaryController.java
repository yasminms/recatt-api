package br.com.recatt.controller.diary;

import br.com.recatt.domain.DiaryDTO;
import br.com.recatt.domain.DiaryStudentsRequest;
import br.com.recatt.service.FindAllDiariesService;
import br.com.recatt.service.SaveDiaryStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/diary")
public class DiaryController implements DiaryContract {

    @Autowired
    private FindAllDiariesService findAllDiariesService;

    @Autowired
    private SaveDiaryStudentsService saveDiaryStudentsService;

    @Override
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DiaryDTO> findAll() {
        return findAllDiariesService.findAll();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DiaryDTO> saveStudents(@RequestBody final DiaryStudentsRequest request) {
        return saveDiaryStudentsService.save(request);
    }

}
