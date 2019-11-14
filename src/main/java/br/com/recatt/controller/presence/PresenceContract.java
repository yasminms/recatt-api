package br.com.recatt.controller.presence;

import br.com.recatt.domain.PresenceDTO;
import br.com.recatt.domain.PresenceRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(description = "Responsável pelos serviços referentes as presenças dos alunos")
public interface PresenceContract {

    @ApiOperation(value = "Lista todas presenças e faltas do aluno logado")
    List<PresenceDTO> findAllPresences();

    @ApiOperation(value = "Solicita uma correção de presença e retorna presenças atualizadas")
    List<PresenceDTO> savePresenceRequest(final Integer presenceId);

    @ApiOperation(value = "Atualiza e finaliza uma solicitação de correção de presença e retorna solicitações atualizadas")
    List<PresenceRequestDTO> updatePresenceRequest(final Integer presenceId, final boolean status);

    @ApiOperation(value = "Lista todas solicitações de correção de presença")
    List<PresenceRequestDTO> findAllRequestPresences();

}
