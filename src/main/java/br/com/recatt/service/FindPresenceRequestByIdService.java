package br.com.recatt.service;

import br.com.recatt.entity.PresenceRequest;
import br.com.recatt.exception.EntityNotFoundException;
import br.com.recatt.repository.PresenceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindPresenceRequestByIdService {

    @Autowired
    private PresenceRequestRepository presenceRequestRepository;

    public PresenceRequest findById(final Integer id) {

        return presenceRequestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Solicitação não encontrada. Tente novamente."));
    }

}
