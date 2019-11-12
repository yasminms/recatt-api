package br.com.recatt.service;

import br.com.recatt.entity.Diary;
import br.com.recatt.exception.EntityNotFoundException;
import br.com.recatt.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindDiaryByIdService {

    @Autowired
    private DiaryRepository diaryRepository;

    public Diary findById(final Integer id) {

        return diaryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Diário não encontrado. Tente novamente."));
    }
}
