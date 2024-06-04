package com.agenda.agendaLagoinha.requests;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateMinistryRequest {

    private String name;
    private String leader;
    private List<String>members;

}
