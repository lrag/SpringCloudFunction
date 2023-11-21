package com.curso.configuracion;

import java.time.Instant;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.cloud.function.context.MessageRoutingCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

@Configuration
public class ConfiguracionFunciones {

	@Bean
	Supplier<String> hora() {
		return () -> Instant.now().toString();
	}

	@Bean
	Consumer<String> guardar() {
		return valor -> System.out.println(valor + " guardado en la BB.DD.");
	}

	@Bean
	Function<String, String> mayusculas() {
		return texto -> texto.toUpperCase();
	}	
	
	@Bean
	MessageRoutingCallback messageRoutingCallback() {
		return new MessageRoutingCallback() {
			@Override
			public String routingResult(Message<?> message) {				
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				System.out.println(message.getHeaders());
				String uri = (String) message.getHeaders().get("uri");
				System.out.println(uri);
				switch(uri) {
					case "/funcion/hora"       : return "hora";
					case "/funcion/mayusculas" : return "mayusculas";
					case "/funcion/guardar"    : return "guardar";
					default: return null;
				}
			}
		};
	}


}


/*

POST /peticion
function: insertar




*/






