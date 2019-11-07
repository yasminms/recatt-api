package br.com.recatt.domain;

import br.com.recatt.config.security.UserPrincipal;
import br.com.recatt.entity.User;
import br.com.recatt.utils.LocalDateUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -3583627988815344710L;

    private String email;

    private String fullName;

    private String birthDate;

    private Gender gender;

    private Role role;

    public UserDTO(final User user) {
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.birthDate = LocalDateUtils.localDateToString(user.getBirthDate());
        this.gender = user.getGender();
        this.role = user.getRole();
    }

    public UserDTO(final UserPrincipal userPrincipal) {
        this.email = userPrincipal.getEmail();
        this.fullName = userPrincipal.getFullName();
        this.birthDate = LocalDateUtils.localDateToString(userPrincipal.getBirthDate());
        this.gender = userPrincipal.getGender();
        this.role = userPrincipal.getRole();
    }

}
