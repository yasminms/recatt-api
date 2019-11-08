package br.com.recatt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableScheduling
@EnableSwagger2
@SpringBootApplication
public class RecattApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecattApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(RecattApplication.class.getPackage().getName()))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Recatt - API",
                "API referente ao projeto Recatt",
                "API 1.0",
                "Terms of service",
                new Contact("Guilherme Francisco e Yasmin Moraes", "", "guisanfrancisco@gmail.com / yasminms46@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

}
