package br.com.recatt.controller.classes;

import br.com.recatt.domain.ClassRegisterRequest;
import br.com.recatt.service.SaveClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/class")
public class ClassController implements ClassContract {

    @Autowired
    private SaveClassService saveClassService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody final ClassRegisterRequest request) {
        saveClassService.save(request);
    }
}
