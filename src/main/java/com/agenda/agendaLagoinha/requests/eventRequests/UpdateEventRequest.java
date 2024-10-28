package com.agenda.agendaLagoinha.requests.eventRequests;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateEventRequest {
    private String name;
    private List<String>members;
}
