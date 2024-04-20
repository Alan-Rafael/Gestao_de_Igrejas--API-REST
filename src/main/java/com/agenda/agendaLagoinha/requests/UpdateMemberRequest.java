package com.agenda.agendaLagoinha.requests;


import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateMemberRequest {
    private String name;
    private Long age;

}
