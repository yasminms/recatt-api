package br.com.recatt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClassroomFaceRecognitionService {

    @Transactional
    public void get() {

        log.info("Capturando imagens e identificando alunos");
    }

}
