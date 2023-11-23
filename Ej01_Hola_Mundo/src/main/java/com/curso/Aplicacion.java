package com.curso;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class Aplicacion {

	public static void main(String[] args) {
		SpringApplication.run(Aplicacion.class, args);
	}

	@Bean
	Supplier<String> saludos() {
		return () -> "Hola mundo";
	}

}

////////////////////////

@FunctionalInterface
interface Movida {
	void metodo();
}

class A implements Supplier<String>{
	@Override
	public String get() {
		return "HOLA";
	}
}

class B implements Consumer<String>{
	@Override
	public void accept(String t) {
		
	}
}

class C implements Function<String, String> {
	@Override
	public String apply(String t) {
		return t.toUpperCase();
	}	
}





