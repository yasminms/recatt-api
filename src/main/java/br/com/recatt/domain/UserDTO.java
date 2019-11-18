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
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -3583627988815344710L;

    private String email;

    private String fullName;

    private String birthDate;

    private Gender gender;

    private Role role;

}
