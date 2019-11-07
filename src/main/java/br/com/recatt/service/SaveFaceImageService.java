package br.com.recatt.service;

import br.com.recatt.entity.User;
import br.com.recatt.exception.EmailNotFoundException;
import br.com.recatt.exception.ServiceUnavailableException;
import br.com.recatt.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class SaveFaceImageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SaveEncodingsRestService restService;

    public void save(final String email, final List<MultipartFile> faceImages) {

        log.info("Inserindo imagens faciais para usuário: {}", email);

        final User existingUser = userRepository.findByEmail(email);

        if (isNull(existingUser)) {

            log.error("E-mail informado não existe.");
            throw new EmailNotFoundException("O usuário que você está tentando alterar não foi encontrado. Tente novamente.");
        }

        try {
            restService.save(email, faceImages);
        } catch (Exception e) {
            throw new ServiceUnavailableException("O serviço está indisponível no momento. Tente novamente mais tarde.");
        }
    }
}

