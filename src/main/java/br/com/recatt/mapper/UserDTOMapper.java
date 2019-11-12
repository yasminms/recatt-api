package br.com.recatt.mapper;

import br.com.recatt.config.security.UserPrincipal;
import br.com.recatt.domain.UserDTO;
import br.com.recatt.entity.User;

import static br.com.recatt.utils.DateTimeUtils.localDateToString;

public final class UserDTOMapper {

    public static UserDTO apply(final User user) {

        return UserDTO.builder()
                .email(user.getEmail())
                .fullName(user.getFullName())
                .birthDate(localDateToString(user.getBirthDate()))
                .gender(user.getGender())
                .role(user.getRole())
                .build();
    }

    public static UserDTO apply(final UserPrincipal userPrincipal) {

        return UserDTO.builder()
                .email(userPrincipal.getEmail())
                .fullName(userPrincipal.getFullName())
                .birthDate(localDateToString(userPrincipal.getBirthDate()))
                .gender(userPrincipal.getGender())
                .role(userPrincipal.getRole())
                .build();
    }
}
