package com.easy.bank.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.easy.bank.dao.ClienteDAO;
import com.easy.bank.model.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteDAO clienteDAO;

	//Listar todos os clientes
	@GetMapping
	public ArrayList<Cliente> listarClientes() {
		ArrayList<Cliente> listaDeClientes;
		listaDeClientes = (ArrayList<Cliente>) clienteDAO.findAll();

		return listaDeClientes;
	}

	//Buscar cliente pelo ID
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> recuperarConta(@PathVariable int clienteId) {

		Optional<Cliente> cliente = clienteDAO.findById(clienteId);

		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}

		return ResponseEntity.notFound().build();
	}

	//Adicionar cliente
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionarCliente(@RequestBody Cliente cliente) {
		return clienteDAO.save(cliente);
	}
	
	//Atualizar cliente
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable int clienteId, @RequestBody Cliente cliente){
		
		if(!clienteDAO.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = clienteDAO.save(cliente);
		
		return ResponseEntity.ok(cliente);
	}

}
