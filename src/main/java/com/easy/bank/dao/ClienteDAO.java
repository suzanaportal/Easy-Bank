package com.easy.bank.dao;

import org.springframework.data.repository.CrudRepository;

import com.easy.bank.model.Cliente;

public interface ClienteDAO extends CrudRepository<Cliente, Integer> {
	

}
