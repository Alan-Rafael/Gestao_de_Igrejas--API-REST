package com.agenda.agendaLagoinha.member;

import com.agenda.agendaLagoinha.View.ViewMember;
import com.agenda.agendaLagoinha.event.Event;
import com.agenda.agendaLagoinha.ministerios.Ministry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberID")
    @JsonView({ViewMember.Admin.class})
    private Long id;

    @CPF(message = "Digite um CPF Válido")
    @Column(name = "cpf_member", nullable = false)
    @JsonView({ViewMember.Base.class})
    private String cpf;

    @Column(name = "memberName", nullable = false)
    @JsonView({ViewMember.Base.class})
    private String name;

    @JsonView({ViewMember.Base.class})
    @Column(name = "memberAge", nullable = false)

    private Long age;

    @JsonView({ViewMember.Base.class})
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @ManyToMany(mappedBy = "eventMembers")
    @JsonIgnoreProperties("eventMembers")
    @JsonView(ViewMember.Admin.class)
    private Set<Event>events;

    @ManyToMany(mappedBy = "ministryMembers")
    @JsonIgnoreProperties("ministryMembers")
    @JsonView(ViewMember.Admin.class)
    private Set<Ministry> ministries;

    @JsonView(ViewMember.Admin.class)
    @OneToMany(mappedBy = "leader")
    private Set<Ministry> liderando;

    public Member(Long id, String name, String cpf, Long age, Sexo sexo) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.age = age;
        this.sexo = sexo;
    }

    public void addMinisterioQueSouLider(final Ministry ministry){
        this.getLiderando().add(ministry);

    }


}



