package br.com.recatt.controller.group;

import br.com.recatt.entity.Group;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(description = "Responsável pelos serviços referentes a turma")
public interface GroupContract {

    @ApiOperation(value = "Lista todas as turmas existentes")
    List<Group> findAll();
}
