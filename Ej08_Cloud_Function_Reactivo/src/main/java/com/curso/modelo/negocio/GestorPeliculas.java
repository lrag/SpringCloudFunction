package com.curso.modelo.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.persistencia.PeliculaRepositorio;

import reactor.core.publisher.Mono;

@Service
public class GestorPeliculas {

	@Autowired private PeliculaRepositorio peliculaRepo;

	public Mono<Pelicula> insertar(Pelicula pelicula) {
		//Aqui no pondremos código que esté fuera del stream que hemos de construir
		return peliculaRepo.save(pelicula).map(p -> {
			//Cualquier LN va en los eslabones de la cadena
			return p;
		});
	}
	
	public Mono<Pelicula> modificar(Pelicula pelicula) {
		return peliculaRepo.save(pelicula);
	}	
	
	public Mono<Void> borrar(Integer idPelicula) {
		return peliculaRepo.deleteById(idPelicula); 
	}	
	
}
