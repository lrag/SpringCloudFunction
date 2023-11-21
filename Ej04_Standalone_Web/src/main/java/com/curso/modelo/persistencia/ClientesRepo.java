package com.curso.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.curso.modelo.entidad.Cliente;

@Repository
public class ClientesRepo {

	private static List<Cliente> clientes;
	
	static {
		clientes = new ArrayList<>();
		clientes.add(new Cliente(1l,"Nombre1","Direccion1","Teléfono1"));
		clientes.add(new Cliente(2l,"Nombre2","Direccion2","Teléfono2"));
		clientes.add(new Cliente(3l,"Nombre3","Direccion3","Teléfono3"));
		clientes.add(new Cliente(4l,"Nombre4","Direccion4","Teléfono4"));
		clientes.add(new Cliente(5l,"Nombre5","Direccion5","Teléfono5"));
	}
	
	public Cliente save(Cliente cliente) {
		if(cliente.getId()==null) {
			insertarCliente(cliente);
		} else {
			modificarCliente(cliente);
		}
		return cliente;
	}
	
	private void insertarCliente(Cliente cliente) {
		cliente.setId(System.currentTimeMillis());
		clientes.add(cliente);
	}
	
	//Lo dejamos public por necesidades del guión
	public void modificarCliente(Cliente cliente) {
		for(int a=0; a<clientes.size(); a++) {
			if(clientes.get(a).getId()==cliente.getId()) {
				clientes.set(a, cliente);
				return;
			}
		}
		clientes.add(cliente);
	}
	
	public void delete(Cliente cliente) {
		clientes.removeIf( c -> c.getId().equals(cliente.getId()));
	}
	
	public Cliente find(Long idCliente) {
		for(Cliente cliente: clientes) {
			if(cliente.getId().equals(idCliente)) {
				return cliente;
			}
		}
		return null;
	}
	
	public List<Cliente> findAll(){
		return clientes;
	}
	
}

