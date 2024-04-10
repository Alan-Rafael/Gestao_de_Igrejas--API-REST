package com.agenda.agendaLagoinha.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class CreateMinistryRequest {


    private String name;
    private Long leaderId;

}
