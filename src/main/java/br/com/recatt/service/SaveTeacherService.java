package br.com.recatt.service;

import br.com.recatt.domain.UserDTO;
import br.com.recatt.domain.UserRegisterRequest;
import br.com.recatt.entity.Teacher;
import br.com.recatt.exception.EmailAlreadyInUseException;
import br.com.recatt.repository.TeacherRepository;
import br.com.recatt.utils.LocalDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
@Slf4j
public class SaveTeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO save(final UserRegisterRequest request) {

        log.info("Inserindo novo professor: {}", request.getEmail());

        final Teacher existingUser = teacherRepository.findByEmail(request.getEmail());

        if (nonNull(existingUser)) {

            log.error("E-mail já existente na base de dados.");
            throw new EmailAlreadyInUseException("Este e-mail já está sendo utilizado. Tente outro diferente.");
        }

        Teacher student = new Teacher();

        student.setEmail(request.getEmail());
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setFullName(request.getFullName());
        student.setCpf(request.getCpf());
        student.setRg(request.getRg());
        student.setBirthDate(LocalDateUtils.stringToLocalDate(request.getBirthDate()));

        return new UserDTO(teacherRepository.save(student));
    }
}
