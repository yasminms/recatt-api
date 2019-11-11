package br.com.recatt.config.security;

import br.com.recatt.domain.Gender;
import br.com.recatt.entity.Admin;
import br.com.recatt.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.util.Optional.ofNullable;

@Component
@Slf4j
public class DefaultAdminService {

    @Value("${administrator.email}")
    private String email;

    @Value("${administrator.password}")
    private String password;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminRepository repository;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {

        log.info("Verificando existência de administrador default: {}", email);
        ofNullable(repository.findByEmail(email)).orElse(repository.save(getDefault()));
    }

    private Admin getDefault() {

        final Admin admin = new Admin();

        admin.setFullName("Administrador Default");
        admin.setBirthDate(LocalDate.now());
        admin.setCpf("000.000.000-00");
        admin.setGender(Gender.MALE);
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(password));

        return admin;
    }
}
