package com.agenda.agendaLagoinha.requests.MemberRequests;


import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateMemberRequest {
    private String name;
    private Long age;

}
