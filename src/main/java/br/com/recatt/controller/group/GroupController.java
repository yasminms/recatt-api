package br.com.recatt.controller.group;

import br.com.recatt.entity.Group;
import br.com.recatt.service.FindAllGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController implements GroupContract {

    @Autowired
    private FindAllGroupsService findAllGroupsService;

    @Override
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Group> findAll() {
        return findAllGroupsService.findAll();
    }
}
