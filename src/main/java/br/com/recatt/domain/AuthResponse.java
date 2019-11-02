package br.com.recatt.domain;

import br.com.recatt.config.security.UserPrincipal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthResponse implements Serializable {

    private static final long serialVersionUID = -1508070365946705002L;

    private String token;
    private UserDTO user;

    public AuthResponse(String token, UserPrincipal userPrincipal) {
        this.token = token;
        this.user = new UserDTO(userPrincipal);
    }

}
