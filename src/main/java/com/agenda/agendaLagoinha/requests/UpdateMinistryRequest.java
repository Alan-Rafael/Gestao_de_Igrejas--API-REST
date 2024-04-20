package com.agenda.agendaLagoinha.requests;

import com.agenda.agendaLagoinha.domain.Member;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMinistryRequest {
    private String name;
    private String leader;
    private List<String> members;



}
