package br.com.recatt.mapper;

import br.com.recatt.config.security.UserPrincipal;
import br.com.recatt.domain.AuthResponse;

public final class AuthResponseMapper {

    public static AuthResponse apply(final String token, final UserPrincipal userPrincipal) {

        return AuthResponse.builder()
                .token(token)
                .user(UserDTOMapper.apply(userPrincipal))
                .build();
    }
}
