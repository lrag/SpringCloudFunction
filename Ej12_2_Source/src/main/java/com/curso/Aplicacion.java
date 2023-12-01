package com.curso;

import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import com.curso.modelo.entidad.Cliente;

@SpringBootApplication
public class Aplicacion {

	public static void main(String[] args) {
		SpringApplication.run(Aplicacion.class, args);
	}

	/*
	@Bean
	//spring.cloud.stream.bindings.clienteSupplier-out-0.destination=topic-clientes
	Supplier<Cliente> clienteSupplier(){
		
		return new Supplier<Cliente>() {
			private int contador=1;
			private boolean wait = true;
			
			public Cliente get() {
				if(wait) {
					System.out.println("Esperando 10s");
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					wait = false;
				}
				
				System.out.println("Enviando cliente "+contador);
				Cliente c = new Cliente();
				c.setNombre("nombre"+contador);
				c.setDireccion("direccion"+contador);
				c.setTelefono("telefono"+contador);
				contador++;
				return c;
			}
		};		
	}
	*/
	
}




