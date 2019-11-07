package br.com.recatt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractFaceRecognitionApiService {

    @Autowired
    protected RestTemplate restTemplate;

    @Value("${services.url.faceRecognitionApi}")
    protected String baseUrl;

}
