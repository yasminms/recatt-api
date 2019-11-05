package br.com.recatt.controller.classroom;

import br.com.recatt.entity.Classroom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(description = "Responsável pelos serviços referentes a sala de aula")
public interface ClassroomContract {

    @ApiOperation(value = "Lista todas as salas de aula existentes")
    List<Classroom> findAll();
}
