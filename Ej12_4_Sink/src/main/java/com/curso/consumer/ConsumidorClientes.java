package com.curso.consumer;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.GestorClientes;

//Según la configuración en application.properties ese tonsumer será ignorado por spring.cloud.config
@Component
//spring.cloud.stream.bindings.consumidorClientes-in-0.destination=topic-clientesProcesados
public class ConsumidorClientes implements Consumer<Cliente> {
	
	@Autowired
	private GestorClientes gestorClientes;

	@Override
	public void accept(Cliente cliente) {		
		gestorClientes.insertarCliente(cliente);		
	}

}
