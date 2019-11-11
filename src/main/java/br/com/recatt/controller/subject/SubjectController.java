package br.com.recatt.controller.subject;

import br.com.recatt.entity.Subject;
import br.com.recatt.service.FindAllSubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController implements SubjectContract {

    @Autowired
    private FindAllSubjectsService findAllSubjectsService;

    @Override
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Subject> findAll() {
        return findAllSubjectsService.findAll();
    }
}
