package br.com.recatt.controller.diary;

import br.com.recatt.domain.DiaryDTO;
import br.com.recatt.domain.DiaryStudentsRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(description = "Responsável pelos serviços referentes ao diário")
public interface DiaryContract {

    @ApiOperation(value = "Lista todos os diários existentes")
    List<DiaryDTO> findAll();

    @ApiOperation(value = "Insere alunos em um diário")
    List<DiaryDTO> saveStudents(final DiaryStudentsRequest request);

}
