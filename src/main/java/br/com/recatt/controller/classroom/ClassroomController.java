package br.com.recatt.controller.classroom;

import br.com.recatt.entity.Classroom;
import br.com.recatt.service.FindAllClassroomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/classroom")
public class ClassroomController implements ClassroomContract{

    @Autowired
    private FindAllClassroomsService findAllClassroomsService;


    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Classroom> findAll() {
        return findAllClassroomsService.findAll();
    }
}
