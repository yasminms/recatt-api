package br.com.recatt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthResponse implements Serializable {

    private static final long serialVersionUID = -1508070365946705002L;

    private String token;
    private UserDTO user;

}
