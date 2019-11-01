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

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
public class Presence implements Serializable {

    private static final long serialVersionUID = 9031734288407824147L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_email")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class actualClass;

    private Integer missedClasses;

}
