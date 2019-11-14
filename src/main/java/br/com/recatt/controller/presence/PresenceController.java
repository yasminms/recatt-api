package br.com.recatt.controller.presence;

import br.com.recatt.domain.PresenceDTO;
import br.com.recatt.domain.PresenceRequestDTO;
import br.com.recatt.service.FindAllPresenceRequest;
import br.com.recatt.service.FindAllPresencesService;
import br.com.recatt.service.PresenceRequestService;
import br.com.recatt.service.UpdatePresenceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private FindAllPresenceRequest findAllPresenceRequest;

    @Autowired
    private UpdatePresenceRequest updatePresenceRequest;

    @Override
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PresenceDTO> findAllPresences() {
        return findAllPresencesService.findAll();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @PostMapping("/{id}/request")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void savePresenceRequest(@PathVariable("id") final Integer presenceId) {
        presenceRequestService.request(presenceId);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @GetMapping("/request")
    @ResponseStatus(HttpStatus.OK)
    public List<PresenceRequestDTO> findAllRequestPresences() {
        return findAllPresenceRequest.findAll();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @PutMapping("/{id}/request")
    @ResponseStatus(HttpStatus.OK)
    public void updatePresenceRequest(@PathVariable("id") final Integer presenceRequestId, @RequestParam("status") boolean status) {
        updatePresenceRequest.update(presenceRequestId, status);
    }
}
