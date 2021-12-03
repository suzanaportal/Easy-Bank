package com.easy.bank.dao;

import org.springframework.data.repository.CrudRepository;

import com.easy.bank.model.Conta;

public interface ContaDAO extends CrudRepository<Conta, Integer> {

}
