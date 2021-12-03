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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.easy.bank.dao.ContaDAO;
import com.easy.bank.model.Cliente;
import com.easy.bank.model.Conta;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaDAO contaDAO;

	// Lista todas as contas
	@GetMapping
	public ArrayList<Conta> listarContas() {
		ArrayList<Conta> lista;
		lista = (ArrayList<Conta>) contaDAO.findAll();

		return lista;
	}

	// Busca conta pelo número
	@GetMapping("/{contaId}")
	public ResponseEntity<Conta> recuperarConta(@PathVariable int contaId) {

		Optional<Conta> conta = contaDAO.findById(contaId);

		if (conta.isPresent()) {
			return ResponseEntity.ok(conta.get());
		}

		return ResponseEntity.notFound().build();
	}

	// Adicionar conta
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Conta adicionarConta(@RequestBody Conta conta) {
		return contaDAO.save(conta);
	}

	// Atualizar conta
	@PutMapping("/{contaId}")
	public ResponseEntity<Conta> atualizarConta(@PathVariable int contaId, @RequestBody Conta conta) {

		if (!contaDAO.existsById(contaId)) {
			return ResponseEntity.notFound().build();
		}

		conta.setNumero(contaId);
		conta = contaDAO.save(conta);

		return ResponseEntity.ok(conta);
	}

}
