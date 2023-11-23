package com.curso.funcion;

import java.time.Instant;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

@Component
public class Logger implements Consumer<String>{
	
	@Override
	public void accept(String mensaje) {
		System.out.println(Instant.now()+":"+mensaje);
	}
	
}
