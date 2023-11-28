package com.curso.cfg;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.GestorPeliculas;
import com.curso.modelo.persistencia.PeliculaRepositorio;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class ConfiguracionFunciones {

	@Bean
	Function<String, Message<Flux<Pelicula>>> listarPeliculas(PeliculaRepositorio peliculaRepo){
		return (filtro) -> {
			return MessageBuilder
				.withPayload(peliculaRepo.findAll())
				.setHeader(MessageHeaders.CONTENT_TYPE, MediaType.TEXT_EVENT_STREAM_VALUE)
				.build();
		};
	}
	
	@Bean
	Function<Integer, Message<Mono<Pelicula>>> buscarPelicula(PeliculaRepositorio peliculaRepo){
		return (id) -> {
			return MessageBuilder
					.withPayload(peliculaRepo.findById(id))
					.setHeader(MessageHeaders.CONTENT_TYPE, MediaType.TEXT_EVENT_STREAM_VALUE)
					.build();
		};
	}
	
	@Bean
	Function<Pelicula, Message<Mono<Pelicula>>> insertarPelicula(GestorPeliculas gestorPeliculas){
		return (pelicula) -> {
			//Validar la película
			//...
			return MessageBuilder
					.withPayload(gestorPeliculas.insertar(pelicula))
					.setHeader(MessageHeaders.CONTENT_TYPE, MediaType.TEXT_EVENT_STREAM_VALUE)
					.build();
		};
	}
	
}
