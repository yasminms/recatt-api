package br.com.recatt.entity;

import br.com.recatt.domain.PresenceRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
public class PresenceRequest implements Serializable {

    private static final long serialVersionUID = -3943194356672344837L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "presence_id")
    private Presence presence;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private PresenceRequestStatus status;
}
