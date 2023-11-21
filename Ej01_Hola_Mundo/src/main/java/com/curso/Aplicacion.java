package com.curso;

import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Aplicacion {

	public static void main(String[] args) {
		SpringApplication.run(Aplicacion.class, args);
	}

	@Bean
	public Supplier<String> saludos() {
		return () -> "Hola mundo";
	}

}

/*
GET /clientes
GET /clientes/{id}
POST /clientes
PUT /clientes/{id}
DELETE /clientes/{id}
*/