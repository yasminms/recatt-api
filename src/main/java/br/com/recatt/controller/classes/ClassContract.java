package br.com.recatt.controller.classes;

import br.com.recatt.domain.ClassRegisterRequest;
import io.swagger.annotations.Api;

@Api(description = "Responsável pelos serviços referentes a aula")
public interface ClassContract {

    void save(final ClassRegisterRequest request);

}
