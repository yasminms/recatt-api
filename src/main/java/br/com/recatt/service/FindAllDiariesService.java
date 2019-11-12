package br.com.recatt.service;

import br.com.recatt.domain.DiaryDTO;
import br.com.recatt.mapper.DiaryDTOMapper;
import br.com.recatt.repository.DiaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FindAllDiariesService {

    @Autowired
    private DiaryRepository diaryRepository;

    public List<DiaryDTO> findAll() {

        return diaryRepository.findAll().stream()
                .map(DiaryDTOMapper::apply)
                .collect(Collectors.toList());
    }
}
