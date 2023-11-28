package com.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class Aplicacion implements CommandLineRunner{

	@Autowired
	private WebClient webClient;	
	
	public static void main(String[] args) {
		SpringApplication.run(Aplicacion.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
		System.out.println("=====================================");
		
		/*
		webClient
			.get()
			.uri("/listarPeliculas")
			.retrieve()
			.bodyToFlux(Pelicula.class)
			.subscribe( p -> {
					System.out.println(p);
				});
		*/
		
		webClient
		.get()
		.uri("/palabrasReactivo")
		.retrieve()
		.bodyToFlux(String.class)
		.subscribe( p -> {
				System.out.println(p);
			});	
		
		Thread.sleep(60_000);
		
		
	}

}
