package com.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.persistencia.ClienteRepositorio;

@SpringBootApplication
public class Aplicacion implements CommandLineRunner {

	@Autowired
	private ClienteRepositorio clienteRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Aplicacion.class, args);
	}
	
	public void run(String...args) {
		System.out.println("=============================");		
		if(clienteRepo.count() == 0) {
			clienteRepo.save(new Cliente(1,"lew","Lew Archer","C/Tal,123","555","lew@archer.com","banco 1"));
			clienteRepo.save(new Cliente(2,"philip","Philip Marlowe","C/Pascual,123","555","philip@marlowe.com","banco 2"));
			clienteRepo.save(new Cliente(3,"sam","Sam Spade","C/Tal y Pascual,123","555","sam@spade.com","banco 3"));
			clienteRepo.save(new Cliente(4,"jesus","Jesus Witness","C/Falsa,123","555","jotero@greatfarandulaindustries.es","banco 4"));
			clienteRepo.save(new Cliente(5,"agente","El agente de la Continental","C/Pascualillo,123","555","agente@continental.com","banco 5"));
		}
	}	

}
