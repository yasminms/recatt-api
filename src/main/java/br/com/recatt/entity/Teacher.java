package br.com.recatt.entity;

import br.com.recatt.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
public class Teacher extends User implements Serializable {

    private static final long serialVersionUID = -6603039176337681439L;

    @OneToMany(mappedBy = "teacher")
    private List<Diary> diaries;

    @Override
    public Role getRole() {
        return Role.TEACHER;
    }

}
