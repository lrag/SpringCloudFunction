package com.curso;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.context.ApplicationContext;

import com.curso.modelo.entidad.Cliente;

@SpringBootApplication
public class Aplicacion implements CommandLineRunner {

	@Autowired
	private FunctionCatalog functionCatalog;
	@Autowired
	private ApplicationContext appCtx;
	
	public static void main(String[] args) {
		SpringApplication.run(Aplicacion.class, args);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void run(String... args) throws Exception {
		
		System.out.println("---------------------------");
		
		Function<String,String> mayusculas = (Function<String,String>) appCtx.getBean("mayusculas");
		String texto = "rod johnson";
		String textoMayusculas = mayusculas.apply(texto);
		System.out.println(mayusculas.getClass().getName());
		System.out.println(textoMayusculas);
	
		Function<String,String> mayusculas2 = functionCatalog.lookup("mayusculas");
		String textoMayusculas2 = mayusculas2.apply(texto);
		System.out.println(mayusculas2.getClass().getName());
		System.out.println(textoMayusculas2);
		
		
		//Saludador saludador = (Saludador) functionCatalog.lookup("saludador");
		Supplier<String> saludador = (Supplier<String>) functionCatalog.lookup("saludador");
		System.out.println(saludador.get());	
		
		Function<Cliente, Cliente> registroClientes = (Function<Cliente, Cliente>) functionCatalog.lookup("registroClientes");
		Cliente cliente = new Cliente(0, "Bart J Simpson", "C/Evergreen Terraze", "555 123 456");
		Cliente clienteInsertado = registroClientes.apply(cliente);
		System.out.println(clienteInsertado);
		
		Function registroClientes2 = functionCatalog.lookup("registroClientes");
		System.out.println(registroClientes2.apply(
				"""
				{
						"nombre"    : "Ringo Starr",
						"direccion" : "C/Tal",
						"telefono"  : "555654321"
				}		
				"""
				)
		);
		
		
		
	}
	
}
