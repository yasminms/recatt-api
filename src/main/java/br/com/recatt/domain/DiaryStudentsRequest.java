package br.com.recatt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiaryStudentsRequest implements Serializable {

    private static final long serialVersionUID = -2973096554514162706L;

    private Integer id;
    private List<String> emails;
}
