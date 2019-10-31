package br.com.recatt.controller.user;

import br.com.recatt.domain.UserDTO;
import br.com.recatt.domain.UserRegisterRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(description = "Responsável pelos serviços referentes ao usuário")
public interface UserContract {

    @ApiOperation(value = "Insere um usuário")
    UserDTO saveStudent(final UserRegisterRequest request);

    @ApiOperation(value = "Insere um usuário")
    UserDTO saveTeacher(final UserRegisterRequest request);

    @ApiOperation(value = "Insere uma ou mais imagens de um usuário")
    void saveFaceImages(final String email, final List<MultipartFile> faceImages);

}
