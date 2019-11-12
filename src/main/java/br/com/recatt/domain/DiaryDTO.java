package br.com.recatt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiaryDTO implements Serializable {

    private static final long serialVersionUID = 8237637566973673045L;

    private Integer id;
    private Integer year;
    private String classroom;
    private String group;
    private String subject;
    private UserDTO teacher;
    private List<UserDTO> students;

}
