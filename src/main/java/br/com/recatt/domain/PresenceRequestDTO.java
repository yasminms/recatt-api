package br.com.recatt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PresenceRequestDTO implements Serializable {

    private static final long serialVersionUID = -2759850746778016885L;

    private Integer id;
    private Integer detection;
    private Integer capture;
    private UserDTO student;
    private String group;
    private String subject;
    private LocalDateTime startTime;

}
