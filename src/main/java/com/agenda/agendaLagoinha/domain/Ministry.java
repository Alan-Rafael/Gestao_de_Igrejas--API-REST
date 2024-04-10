package com.agenda.agendaLagoinha.domain;


import com.agenda.agendaLagoinha.View.ViewMinistry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Ministry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ViewMinistry.Base.class})
    private Long id;

    @Column
    @JsonView(ViewMinistry.Base.class)
    private String name;


    @ManyToOne
    @JsonView(ViewMinistry.Admin.class)
    @JoinColumn(name = "lider_id", nullable = true)
    private  Member leader;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ministry_members",
            joinColumns =  @JoinColumn(name = "ministry_id"),
            inverseJoinColumns = @JoinColumn(name = "Ministrymember_id"))
    @JsonIgnoreProperties("ministries")
    @JsonView(ViewMinistry.Admin.class)
    private Set<Member> ministryMembers;


    public void addMinistryMember(final  Member member){
        this.ministryMembers.add(member);
    }



}
