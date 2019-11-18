package br.com.recatt.service;

import br.com.recatt.domain.DiaryDTO;
import br.com.recatt.entity.Teacher;
import br.com.recatt.mapper.DiaryDTOMapper;
import br.com.recatt.repository.DiaryRepository;
import br.com.recatt.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.recatt.config.security.CustomUserDetailsService.getUser;
import static java.util.Objects.nonNull;

@Service
@Slf4j
public class FindAllDiariesService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<DiaryDTO> findAll() {

        final Teacher teacher = teacherRepository.findByEmail(getUser().getEmail());

        if (nonNull(teacher)) {

            return diaryRepository.findAll(teacher).stream()
                    .map(DiaryDTOMapper::apply)
                    .collect(Collectors.toList());
        }

        log.info("Usuário administrador logado. Consultando todos os diários.");

        return diaryRepository.findAll().stream()
                .map(DiaryDTOMapper::apply)
                .collect(Collectors.toList());
    }
}
