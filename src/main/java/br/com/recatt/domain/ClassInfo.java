package br.com.recatt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.DayOfWeek;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassInfo implements Serializable {

    private static final long serialVersionUID = -1050327283793891301L;

    @NotNull(message = "Dia da semana deve ser informado.")
    private DayOfWeek dayOfWeek;

    @NotBlank(message = "Hora de início deve ser informada.")
    private String startTime;

    @NotNull(message = "Quantidade de períodos deve ser informada.")
    private Integer period;

}
