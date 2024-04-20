package com.agenda.agendaLagoinha.requests;

import com.agenda.agendaLagoinha.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateMinistryRequest {

    private String name;
    private String leader;
    private List<String>members;

}
