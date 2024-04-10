package com.agenda.agendaLagoinha.requests;


import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberRequest {
    private String name;
    private Long age;
    private Set<Long> eventsId;
    private Set<Long>ministryId;

}
