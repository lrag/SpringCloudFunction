package com.curso.funcion;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.GestorClientes;
import com.curso.modelo.persistencia.ClienteRepositorio;

@Configuration
public class FuncionesCliente {

	@Bean
	//https://stackoverflow.com/questions/75721418/headers-in-get-request-using-supplier-in-spring-cloud-function
	Function<Message<String>, Message<List<Cliente>>> getClientes(ClienteRepositorio clienteRepo){
		return (mensaje) -> {
			//Obtenemos el filtro de clientes (lo ignorareos en el ejemplo)
			Object headerValue = mensaje.getHeaders().get("http_request_param");
			String filtro = null;
			if(headerValue != null) {
				filtro = headerValue.toString();
			}
			System.out.println("--------------------------------");
			System.out.println("Listando clientes");
			System.out.println("Filtro: "+filtro);
			return MessageBuilder.withPayload(clienteRepo.findAll()).build();
		};
	}
	
	@Bean
	Function<Message<String>, Message<?>> getCliente(ClienteRepositorio clienteRepo){
		return (mensaje) -> {
			System.out.println("--------------------------------");
			System.out.println("Buscando cliente");
			System.out.println(mensaje.getHeaders());
			System.out.println("Payload: "+mensaje.getPayload());
			Integer id = Integer.valueOf(mensaje.getPayload());
			Optional<Cliente> clienteOp = clienteRepo.findById(id);
			
			if(clienteOp.isPresent()) {
				return MessageBuilder.withPayload(clienteOp.get()).build();
			} else {
				return MessageBuilder.withPayload("El cliente no existe").build();
			}
		};
	}
	
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
			return MessageBuilder.withPayload(clienteInsertado).build();
		};
	}	
	
	@Bean
	Consumer<Message<String>> bajaCliente(GestorClientes gestorClientes){
		return (mensaje) -> {
			System.out.println("--------------------------------");
			System.out.println("Baja cliente");
			System.out.println(mensaje.getHeaders());
			System.out.println("Payload: "+mensaje.getPayload());

			Integer id = Integer.valueOf(mensaje.getPayload());
			gestorClientes.borrar(id);
		};
	}		
	

}
