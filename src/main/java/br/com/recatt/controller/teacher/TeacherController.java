package br.com.recatt.controller.teacher;

import br.com.recatt.domain.UserDTO;
import br.com.recatt.domain.UserRegisterRequest;
import br.com.recatt.service.SaveTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/teacher")
public class TeacherController implements TeacherContract {

    @Autowired
    private SaveTeacherService saveTeacherService;

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@Valid @RequestBody final UserRegisterRequest request) {
        return saveTeacherService.save(request);
    }

}
