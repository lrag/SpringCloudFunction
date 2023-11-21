package com.curso.configuracion;

import java.time.Instant;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionFunciones {

	@Bean
	Supplier<String> hora(){
		return () -> Instant.now().toString();
	}

	@Bean
	Consumer<String> guardar(){
		return valor -> System.out.println(valor+" guardado en la BB.DD.");
	}
	
	@Bean
	Function<String, String> mayusculas(){
		return texto -> texto.toUpperCase();
	}
	
	@Bean
	Function<String, String> inversor(){
		return texto -> new StringBuilder(texto).reverse().toString();
	}
	
	@Bean
	Function<String, String> corchetes(){
		return texto -> "["+texto+"]";
	}		
	
}
