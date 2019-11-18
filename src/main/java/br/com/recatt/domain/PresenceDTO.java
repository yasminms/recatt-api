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
public class PresenceDTO implements Serializable {

    private static final long serialVersionUID = -6749335298904061434L;

    private Integer id;
    private LocalDateTime dateTime;
    private String classroom;
    private String subject;
    private Integer missedClasses;
    private Integer period;
    private PresenceStatus status;

}
