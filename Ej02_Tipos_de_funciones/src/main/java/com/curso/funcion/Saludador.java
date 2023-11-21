package com.curso.funcion;

import java.util.function.Supplier;
import org.springframework.stereotype.Component;

@Component
public class Saludador implements Supplier<String>{

	@Override
	public String get() {
		return "Hola que tal";
	}

}
