package br.com.recatt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassRegisterRequest implements Serializable {

    private static final long serialVersionUID = 7971032518626297101L;

    @NotNull(message = "Turma deve ser informada.")
    private Integer groupId;

    @NotNull(message = "Disciplina deve ser informada.")
    private Integer subjectId;

    @NotNull(message = "Sala de aula deve ser informada.")
    private Integer classroomId;

    @NotNull(message = "Ano do diário deve ser informado.")
    private Integer year;

    @NotEmpty(message = "Deve ser informado ao menos um dia de aula na semana.")
    private List<ClassInfo> information;

    private String tolerance;

}
