package br.com.recatt.service;

import br.com.recatt.domain.Encoding;
import br.com.recatt.domain.StudentClass;
import br.com.recatt.entity.Class;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindEncodingsFromClassroomRestService extends AbstractFaceRecognitionApiService {

    private static final String RESOURCE = "/students/recognition";

    public StudentClass find(final Class actualClass) {

        final String classroomRaspberryIp = actualClass.getDiary().getClassroom().getAddressIp();

        log.info("Requisitando serviços para identificação de alunos", classroomRaspberryIp);

        final Encoding detectedStudents = restTemplate.getForEntity(classroomRaspberryIp, Encoding.class).getBody();

        return restTemplate.exchange(baseUrl + RESOURCE, HttpMethod.POST, new HttpEntity<>(detectedStudents), StudentClass.class).getBody();
    }
}
