package br.com.recatt.controller.teacher;

import br.com.recatt.domain.UserDTO;
import br.com.recatt.domain.UserRegisterRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Responsável pelos serviços referentes ao professor")
public interface TeacherContract {

    @ApiOperation(value = "Insere um professor")
    UserDTO save(final UserRegisterRequest request);

}
