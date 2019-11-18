package br.com.recatt.service;

import br.com.recatt.entity.Presence;
import br.com.recatt.exception.EntityNotFoundException;
import br.com.recatt.repository.PresenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindPresenceByIdService {

    @Autowired
    private PresenceRepository presenceRepository;

    public Presence findById(final Integer presenceId) {

        return presenceRepository.findById(presenceId).orElseThrow(() -> new EntityNotFoundException("Presença não encontrada. Tente novamente."));
    }

}
