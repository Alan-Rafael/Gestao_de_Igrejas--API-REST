package com.agenda.agendaLagoinha.requests;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMinistryRequest {
    private String name;
    private String leader;
    private List<String> members;



}
