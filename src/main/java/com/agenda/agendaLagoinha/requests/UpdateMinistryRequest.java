package com.agenda.agendaLagoinha.requests;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class UpdateMinistryRequest {
    private String name;
    private Set<Long> lider;





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getLider() {
        return lider;
    }

    public void setLider(Set<Long> lider) {
        this.lider = lider;
    }
}
