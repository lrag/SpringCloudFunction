package com.curso.configuracion;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.curso.modelo.persistencia.ClientesRepo;

@Configuration
public class ConfiguracionFunciones {

	@Autowired
	private ClientesRepo clientesRepo;	
	
	//supplier - GET /supplier
	@Bean
	Supplier<String> supplier() {
		return () -> "Entregado por un supplier";
	}

	//Si recibimos un string lo que recibimos es el body en POST y PUT y 'valor' en DELETE y GET
	//consumer - POST /consumer | PUT /consumer | DELETE /consumer/valor | GET /consumer/valor
	@Bean
	Consumer<String> consumer() {
		return (texto) -> System.out.println("Recibido en el consumer: "+texto);
	}
	
	//Si recibimos un Message<String> el valor viene en 'payload' (el body en POST y PUT y 'valor' en DELETE y GET)
	Consumer<Message<String>> consumer_bis() {
		return (mensaje) -> System.out.println("Recibido en el consumer: "+mensaje.getPayload());
	}	

	//POST /function | PUT /function | GET /function/valor
	@Bean
	Function<String, String> function() {
		return (String texto) -> texto.toUpperCase();
	}

	//Message
	@Bean
	Function<Message<String>, Message<String>> insertar(){
		return (mensaje) -> {	
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				//Headers
				System.out.println(mensaje.getHeaders());
				//Body
				System.out.println(mensaje.getPayload());
				//Query parameters
				System.out.println(mensaje.getHeaders().get("http_request_param"));
				
				return MessageBuilder.withPayload("Hola Radiola").build();
			};
	}
	
	//Query parameters
	
	
}
