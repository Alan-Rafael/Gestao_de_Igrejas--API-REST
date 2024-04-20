package com.agenda.agendaLagoinha.requests;


import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequest {

    private String name;
    private List<String> eventMembers;

}
