package com.agenda.agendaLagoinha.domain;


import com.agenda.agendaLagoinha.View.ViewEvent;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewEvent.Base.class)
    private Long id;

    private String login;

    private String password;

    @Column(name = "eventName")
    @JsonView(ViewEvent.Base.class)
    private String eventName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "evento_membro",
            joinColumns =  @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    @JsonIgnoreProperties("events")
    @JsonView({ViewEvent.Admin.class})
    private Set<Member> eventMembers;

    public void addMember(final  Member member){
        this.eventMembers.add(member);
    }


}
