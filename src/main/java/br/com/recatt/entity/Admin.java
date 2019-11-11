package br.com.recatt.entity;

import br.com.recatt.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;

@AllArgsConstructor
@Entity
@Getter
@Setter
public class Admin extends User implements Serializable {

    private static final long serialVersionUID = -8989275724351545255L;

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}
