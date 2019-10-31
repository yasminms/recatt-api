package br.com.recatt.service;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class SaveEncodingsRestService extends AbstractFaceRecognitionApiService {

    private static final String RESOURCE = "/users?email={email}";

    public void save(final String email, final List<MultipartFile> faceImages) throws IOException {

        log.info("Requisitando serviço de cadastro de imagens faciais: {}", baseUrl + RESOURCE);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        final MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        for (MultipartFile faceImage : faceImages) {
            body.add("faceImages", createTempFileResource(faceImage.getBytes()));
        }

        final HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        final Map<String, String> parameters = ImmutableMap.of("email", email);

        restTemplate.postForObject(baseUrl + RESOURCE, requestEntity, Void.class, parameters);
    }

    static Resource createTempFileResource(final byte[] content) throws IOException {
        Path tempFile = Files.createTempFile(String.valueOf(new Random().nextInt(999)), ".jpeg");
        Files.write(tempFile, content);
        return new FileSystemResource(tempFile.toFile());
    }
}
