package br.com.recatt.controller.subject;

import br.com.recatt.entity.Subject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(description = "Responsável pelos serviços referentes a disciplina")
public interface SubjectContract {

    @ApiOperation(value = "Lista todas as disciplinas existentes")
    List<Subject> findAll();
}
