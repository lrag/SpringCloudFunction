package com.curso.funcion;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.support.MessageBuilder;

import com.curso.funcion.conversor.ConversorXML;
import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.GestorClientes;

@Configuration
public class FuncionesCliente {
	
	@Bean
    public MessageConverter conversorXML() {
        return new ConversorXML();
    }	
	
	
	/*
	POST /altaCliente
	ContentType: application/xml
	----------------------------
	<cliente>...</cliente>	
	*/
	
	//Aqui no hay nada que indique qué conversor debe utilizarse
	@Bean
	Function<Message<Cliente>, Message<Cliente>> altaCliente(GestorClientes gestorClientes){
		return (mensaje) -> {
			System.out.println("--------------------------------");
			System.out.println("Insertando cliente");
			System.out.println(mensaje.getHeaders());
			System.out.println("Payload: "+mensaje.getPayload());

			Cliente cliente = mensaje.getPayload();
			//Podemos validar aqui (a mano)
			//El tratamiento de errores tambien se hace aquí a mano
			Cliente clienteInsertado = gestorClientes.insertar(cliente);
			return MessageBuilder
				.withPayload(clienteInsertado)
				.build();
		};
	}	

}
