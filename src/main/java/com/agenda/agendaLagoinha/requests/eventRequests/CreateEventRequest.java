package com.agenda.agendaLagoinha.requests.eventRequests;

import lombok.*;
import java.util.List;
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequest {

    private String name;
    private List<String> eventMembers;

}
