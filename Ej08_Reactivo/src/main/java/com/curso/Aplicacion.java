package com.curso;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@Configuration
public class Aplicacion implements CommandLineRunner{

	@Autowired
	private FunctionCatalog functionCatalog;	
	
	public static void main(String[] args) {
		SpringApplication.run(Aplicacion.class, args);
	}

	@Bean
	Supplier<Mono<String>> saludos() {
		return () -> Mono.just("Hola mundo");
	}

	@Bean
	//@PollableBean
	Supplier<Flux<String>> palabrasReactivo() {
		return () -> Flux.just(
				//"HELLO", "DOCTOR", "NAME", "CONTINUE", "YESTERDAY", "TOMORROW",
				//"HELLO", "DOCTOR", "NAME", "CONTINUE", "YESTERDAY", "TOMORROW",
				//"HELLO", "DOCTOR", "NAME", "CONTINUE", "YESTERDAY", "TOMORROW",
				//"HELLO", "DOCTOR", "NAME", "CONTINUE", "YESTERDAY", "TOMORROW",
				//"HELLO", "DOCTOR", "NAME", "CONTINUE", "YESTERDAY", "TOMORROW",
				"HELLO", "DOCTOR", "NAME", "CONTINUE", "YESTERDAY", "TOMORROW"
			).delayElements(Duration.ofMillis(1000));
	}
	
	@Bean
	Supplier<List<String>> palabrasImperativo() {
		return () ->  Arrays.asList(
				"HELLO", "DOCTOR", "NAME", "CONTINUE", "YESTERDAY", "TOMORROW",
				"HELLO", "DOCTOR", "NAME", "CONTINUE", "YESTERDAY", "TOMORROW",
				"HELLO", "DOCTOR", "NAME", "CONTINUE", "YESTERDAY", "TOMORROW",
				"HELLO", "DOCTOR", "NAME", "CONTINUE", "YESTERDAY", "TOMORROW",
				"HELLO", "DOCTOR", "NAME", "CONTINUE", "YESTERDAY", "TOMORROW",
				"HELLO", "DOCTOR", "NAME", "CONTINUE", "YESTERDAY", "TOMORROW"
			);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("=================================================");
		
		Supplier<Flux<String>> palabrasReactivo = functionCatalog.lookup("palabrasReactivo");
		
		palabrasReactivo
			.get()
			.subscribe( p -> System.out.println(p));
		
	}

}