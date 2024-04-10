package com.agenda.agendaLagoinha.domain;

import com.agenda.agendaLagoinha.View.ViewMember;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JsonView({ViewMember.Base.class})
    private Long id;

    @Column(name = "memberName", nullable = false)
    @JsonView({ViewMember.Base.class})
    private String memberName;

    @JsonView({ViewMember.Base.class})
    @Column(name = "memberAge", nullable = false)
    private Long memberAge;

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

    public Member(int i, String alan, int i1, Set<Event> events, Set<Ministry> ministries, Object liderando) {
    }


    public void addMinisterioQueSouLider(final Ministry ministry){
        this.getLiderando().add(ministry);

    }



}


