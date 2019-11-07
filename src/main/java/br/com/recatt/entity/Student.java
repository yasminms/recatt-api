package br.com.recatt.entity;

import br.com.recatt.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
public class Student extends User implements Serializable {

    private static final long serialVersionUID = 5195593822055340037L;

    @ManyToMany(mappedBy = "students")
    private List<Diary> diaries;

    @OneToMany(mappedBy = "student")
    private List<Presence> presences;

    @OneToMany(mappedBy = "student")
    private List<TempPresence> tempPresences;

    @Override
    public Role getRole() {
        return Role.STUDENT;
    }

}
