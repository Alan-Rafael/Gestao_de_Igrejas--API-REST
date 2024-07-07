package com.agenda.agendaLagoinha.event.models;


import com.agenda.agendaLagoinha.View.ViewEvent;
import com.agenda.agendaLagoinha.member.Member;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewEvent.Base.class)
    private Long id;

    @Column(name = "eventName")
    @JsonView(ViewEvent.Base.class)
    private String eventName;

    @Column(name = "admin_id")
    private UUID adminId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "evento_membro",
            joinColumns =  @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    @JsonIgnoreProperties("events")
    @JsonView({ViewEvent.Admin.class})
    private Set<Member> eventMembers;

}
