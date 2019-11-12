package br.com.recatt.service;

import br.com.recatt.domain.UserDTO;
import br.com.recatt.domain.UserRegisterRequest;
import br.com.recatt.entity.Student;
import br.com.recatt.entity.User;
import br.com.recatt.exception.EmailAlreadyInUseException;
import br.com.recatt.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.recatt.mapper.UserDTOMapper.apply;
import static br.com.recatt.utils.DateTimeUtils.stringToLocalDate;
import static java.util.Objects.nonNull;

@Service
@Slf4j
public class SaveStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO save(final UserRegisterRequest request) {

        log.info("Inserindo novo aluno: {}", request.getEmail());

        final User existingUser = studentRepository.findByEmail(request.getEmail());

        if (nonNull(existingUser)) {

            log.error("E-mail já existente na base de dados.");
            throw new EmailAlreadyInUseException("Este e-mail já está sendo utilizado. Tente outro diferente.");
        }

        Student student = new Student();

        student.setEmail(request.getEmail());
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setFullName(request.getFullName());
        student.setCpf(request.getCpf());
        student.setRg(request.getRg());
        student.setBirthDate(stringToLocalDate(request.getBirthDate()));
        student.setGender(request.getGender());

        return apply(studentRepository.save(student));
    }
}
