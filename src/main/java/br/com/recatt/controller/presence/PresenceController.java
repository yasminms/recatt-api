package br.com.recatt.controller.presence;

import br.com.recatt.domain.PresenceDTO;
import br.com.recatt.service.FindAllPresencesService;
import br.com.recatt.service.PresenceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/presence")
public class PresenceController implements PresenceContract {

    @Autowired
    private FindAllPresencesService findAllPresencesService;

    @Autowired
    private PresenceRequestService presenceRequestService;

    @Override
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PresenceDTO> findAllPresences() {
        return findAllPresencesService.findAll();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void requestPresence(@PathVariable("id") final Integer presenceId) {
        presenceRequestService.request(presenceId);
    }
}
