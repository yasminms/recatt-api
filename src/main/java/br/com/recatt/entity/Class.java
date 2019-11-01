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
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
public class Class implements Serializable {

    private static final long serialVersionUID = -3440948316090171779L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @OneToMany(mappedBy = "actualClass")
    private List<Presence> presences;

    @OneToMany(mappedBy = "actualClass")
    private List<TempPresence> tempPresences;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer period;

    private Integer tolerance;

    private boolean active;

}
