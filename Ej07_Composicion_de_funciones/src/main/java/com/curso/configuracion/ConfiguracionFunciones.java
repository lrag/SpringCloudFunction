package com.curso.configuracion;

import java.time.Instant;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.cloud.function.context.FunctionCatalog;
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
	
	/*
	@Bean
	@SuppressWarnings({ "rawtypes", "unchecked" })
	Function<String, String> toJunto(FunctionCatalog functionCatalog){
		return texto -> {
			Function f1 = functionCatalog.lookup("mayusculas");
			String textoMayusculas = (String) f1.apply(texto);
			Function f2 = functionCatalog.lookup("inversor");
			String textoInvertido = (String) f1.apply(textoMayusculas);
			Function f3 = functionCatalog.lookup("corchetes");
			String textoCorchetes = (String) f1.apply(textoInvertido);
			return textoCorchetes;
		};
	}
	*/
	
	
	/*
	public String pasarAMayusculas(String texto) {
		return texto.toUpperCase();
	}
	
	public String invertir(String texto) {
		return new StringBuilder(texto).reverse().toString();
	}
	
	public String encorchetar(String texto) {
		return "["+texto+"]";
	}
	
	@Bean
	Function<String, String> toJunto(FunctionCatalog functionCatalog){
		return texto -> {
			String textoMayusculas = pasarAMayusculas(texto);
			String textoInvertido = invertir(textoMayusculas);
			String textoCorchetes = encorchetar(textoInvertido);
			return textoCorchetes;
		};
	}	
	*/
	
}
