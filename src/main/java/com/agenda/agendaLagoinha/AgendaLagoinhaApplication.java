package com.agenda.agendaLagoinha;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Church Management", description = "API responsável por gerenciar igrejas"))
public class AgendaLagoinhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaLagoinhaApplication.class, args);
	}

}
