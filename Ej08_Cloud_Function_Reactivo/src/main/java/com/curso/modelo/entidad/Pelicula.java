package com.curso.modelo.entidad;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


//No son anotaciones de JPA
@Table
public class Pelicula {

	@Id
	private Integer id;
	private String titulo;
	private String director;
	private String genero;

	public Pelicula() {
		super();
	}

	public Pelicula(Integer id, String titulo, String director, String genero) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.director = director;
		this.genero = genero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}


	
	
}
