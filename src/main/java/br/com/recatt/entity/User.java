package br.com.recatt.entity;

import br.com.recatt.domain.Gender;
import br.com.recatt.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Setter
@Table(name = "client")
public abstract class User implements Serializable {

    private static final long serialVersionUID = 6589370084390221603L;

    @Id
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String cpf;

    private String rg;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    public abstract Role getRole();

}
