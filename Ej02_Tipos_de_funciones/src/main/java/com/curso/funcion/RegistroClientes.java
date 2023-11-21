package com.curso.funcion;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.curso.modelo.entidad.Cliente;

@Component
public class RegistroClientes implements Function<Cliente, Cliente>{

	//@Autowired
	//ClienteRepo clienteRepo;	
	
	@Override
	public Cliente apply(Cliente cliente) {		
		cliente.setId(Long.valueOf(Math.round(Math.random()*10000)).intValue());
		return cliente;
	}

}
