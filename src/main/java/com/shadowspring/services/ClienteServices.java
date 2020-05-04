package com.shadowspring.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.shadowspring.entity.Cliente;

public interface ClienteServices {

	Cliente save(Cliente cliente);
	Cliente findById(Long id);
	List<Cliente> findAll();
	Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
	Cliente update(Cliente cliente);


}
