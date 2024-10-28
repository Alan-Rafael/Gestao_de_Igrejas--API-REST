package com.agenda.agendaLagoinha.member;

import com.agenda.agendaLagoinha.View.ViewMember;
import com.agenda.agendaLagoinha.event.Event;
import com.agenda.agendaLagoinha.ministerios.Ministry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
@Builder
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberID")
    @JsonView({ViewMember.Admin.class})
    private Long id;

//    @CPF(message = "Digite um CPF Válido")
    @Column(name = "cpf_member", nullable = false)
    @JsonView({ViewMember.Base.class})
    private String cpf;

    @Column(name = "memberName", nullable = false)
    @JsonView({ViewMember.Base.class})
    private String name;

    @Email(message = "Insira um e-mail válido")
    @Column(name = "email", nullable = false)
    @JsonView(ViewMember.Base.class)
    private String email;

    @Column(name = "password", nullable = false)
    @Length(min = 8, message = "Sua senha deve conter no minimo 8 caracteres")
    private String password;

    @JsonView({ViewMember.Base.class})
    @Column(name = "memberAge", nullable = false)
    private Long age;

    @JsonView({ViewMember.Base.class})
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @ManyToMany(mappedBy = "eventMembers")
    @JsonIgnoreProperties("eventMembers")
    @JsonView(ViewMember.Admin.class)
    private Set<Event>events;

    @ManyToMany(mappedBy = "ministryMembers")
    @JsonIgnoreProperties("ministryMembers")
    @JsonView(ViewMember.Admin.class)
    private Set<Ministry> ministries;

    @JsonView(ViewMember.Admin.class)
    @OneToMany(mappedBy = "leader")
    private Set<Ministry> liderando = new HashSet<>();

    private boolean isAdmin;


    public Member(Long id, String name, String cpf,String email, Long age, Sexo sexo, String password) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.age = age;
        this.sexo = sexo;
        this.password=password;
    }

    public void addMinisterioQueSouLider(Ministry ministry){
        this.getLiderando().add(ministry);

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.isAdmin){
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else{
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}



