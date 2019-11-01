package br.com.recatt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
public class TempPresence implements Serializable {

    private static final long serialVersionUID = 1018016965167822000L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class actualClass;

    @ManyToOne
    @JoinColumn(name = "student_email")
    private Student student;

    private LocalDateTime dateTime;

    private boolean present;

}
