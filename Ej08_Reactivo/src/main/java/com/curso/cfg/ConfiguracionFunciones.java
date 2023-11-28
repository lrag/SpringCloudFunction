package com.curso.cfg;

import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.persistencia.PeliculaRepositorio;

import reactor.core.publisher.Flux;

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
	
}
