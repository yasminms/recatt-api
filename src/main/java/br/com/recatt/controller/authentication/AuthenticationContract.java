package br.com.recatt.controller.authentication;

import br.com.recatt.domain.AuthRequest;
import br.com.recatt.domain.AuthResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Responsável pelos serviços referentes a autenticação")
public interface AuthenticationContract {

    @ApiOperation("Autentica o usuário, retornando um token de acesso")
    AuthResponse authenticate(final AuthRequest authRequest);
}
