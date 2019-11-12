package br.com.recatt.controller.presence;

import br.com.recatt.domain.PresenceDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(description = "Responsável pelos serviços referentes as presenças dos alunos")
public interface PresenceContract {

    @ApiOperation(value = "Lista todas presenças e faltas do aluno logado")
    List<PresenceDTO> findAllPresences();

    @ApiOperation(value = "Solicita uma correção de presença")
    void requestPresence(final Integer presenceId);

}
