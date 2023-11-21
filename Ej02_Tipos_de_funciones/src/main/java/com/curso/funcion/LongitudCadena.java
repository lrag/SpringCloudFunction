package com.curso.funcion;

import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class LongitudCadena implements Function<String, Integer>{

	@Override
	public Integer apply(String cadena) {
		return cadena.length();
	}

}
