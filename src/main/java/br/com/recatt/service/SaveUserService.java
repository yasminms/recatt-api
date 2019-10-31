package br.com.recatt.service;

import br.com.recatt.domain.Role;
import br.com.recatt.domain.UserDTO;
import br.com.recatt.domain.UserRegisterRequest;
import br.com.recatt.entity.User;
import br.com.recatt.exception.EmailAlreadyInUseException;
import br.com.recatt.repository.UserRepository;
import br.com.recatt.utils.LocalDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
@Slf4j
public class SaveUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO save(final UserRegisterRequest request, final Role role) {

        log.info("Inserindo novo usuário: {}", request.getEmail());

        final User existingUser = userRepository.findByEmail(request.getEmail());

        if (nonNull(existingUser)) {

            log.error("E-mail já existente na base de dados.");
            throw new EmailAlreadyInUseException("Este e-mail já está sendo utilizado. Tente outro diferente.");
        }

        User user = new User();

        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setCpf(request.getCpf());
        user.setRg(request.getRg());
        user.setBirthDate(LocalDateUtils.stringToLocalDate(request.getBirthDate()));
        user.setRole(role);

        return new UserDTO(userRepository.save(user));
    }
}
