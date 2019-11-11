package br.com.recatt.controller.student;

import br.com.recatt.domain.UserDTO;
import br.com.recatt.domain.UserRegisterRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(description = "Responsável pelos serviços referentes ao aluno")
public interface StudentContract {

    @ApiOperation(value = "Insere um aluno")
    UserDTO save(final UserRegisterRequest request);

    @ApiOperation(value = "Insere uma ou mais imagens de um aluno")
    void saveFaceImages(final String email, final List<MultipartFile> faceImages);

    @ApiOperation(value = "Lista todos os alunos cadastrados")
    List<UserDTO> findAll();
}
