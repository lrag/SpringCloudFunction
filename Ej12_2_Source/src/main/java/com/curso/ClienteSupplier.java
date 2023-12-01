package com.curso;

import java.util.function.Supplier;
import org.springframework.stereotype.Component;
import com.curso.modelo.entidad.Cliente;

@Component
//spring.cloud.stream.bindings.clienteSupplier-out-0.destination=topic-clientes
public class ClienteSupplier implements Supplier<Cliente> {

	private int contador=1;
	private boolean wait = true;
	
	@Override
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

}
