package com.curso.funcion;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.GestorClientes;
import com.curso.modelo.persistencia.ClienteRepositorio;

@Configuration
public class FuncionesCliente {
	
	
	/*
	GET    /clientes
	GET    /clientes/{id}
	POST   /clientes
	PUT    /clientes/{id}
	DELETE /clientes/{id}
	*/
	
	//GET /clientes?ciudad=Santa Pola
	
	@Bean
	//https://stackoverflow.com/questions/75721418/headers-in-get-request-using-supplier-in-spring-cloud-function
	Function<Message<String>, Message<List<Cliente>>> listarClientes(ClienteRepositorio clienteRepo){
		return (mensaje) -> {
			//Obtenemos el filtro de clientes (lo ignoraremos en el ejemplo)
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
		
	//GET /buscarCliente/5
	@Bean
	Function<Message<Integer>, Message<?>> buscarCliente(ClienteRepositorio clienteRepo){
		return (mensaje) -> {
			System.out.println("--------------------------------");
			System.out.println("Buscando cliente");
			System.out.println(mensaje.getHeaders());
			System.out.println("Payload: "+mensaje.getPayload());
			Integer id = mensaje.getPayload();
			
			/*
			return clienteRepo.findById(id)
					.map(c -> MessageBuilder.withPayload(c).build())
					.orElse(MessageBuilder.withPayload("El cliente no existe").build());
					*/
			
			Optional<Cliente> clienteOp = clienteRepo.findById(id);
			if(clienteOp.isPresent()) {
				return MessageBuilder.withPayload(clienteOp.get()).build();
			} else {				
				//No podemos decidir aqui el status de la respuesta http asi que va a ser un 200 OK :(
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
	Consumer<Message<Integer>> bajaCliente(GestorClientes gestorClientes){
		return (mensaje) -> {
			System.out.println("--------------------------------");
			System.out.println("Baja cliente");
			System.out.println(mensaje.getHeaders());
			System.out.println("Payload: "+mensaje.getPayload());

			Integer id = mensaje.getPayload();
			gestorClientes.borrar(id);
		};
	}	

}
