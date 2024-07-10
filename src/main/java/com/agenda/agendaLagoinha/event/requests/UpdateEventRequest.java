package com.agenda.agendaLagoinha.event.requests;

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
